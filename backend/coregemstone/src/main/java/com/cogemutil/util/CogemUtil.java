package com.cogemutil.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import com.cogemutil.dto.Coregemstone;
import com.cogemutil.dto.ResultGetCoreList;

public class CogemUtil {

	private final static String UTILIMAGEPATH = "src/main/resources/utilImage/";

	public void getCogemList(MultipartFile[] files, String job, ResultGetCoreList result_get_core_list)
			throws IOException {
		List<Coregemstone> result = new ArrayList<>();
		int cnt = 0;
		for (int i = 0; i < files.length; i++) {
			BufferedImage image = ImageIO.read(files[i].getInputStream());
			cnt = searchPoint(image, job, result, cnt, result_get_core_list);
		}

		result_get_core_list.setCore_list(result);

	}

	// 코어위치를 찾기위한 특정 이미지를 기준으로 좌표파악
	public int searchPoint(BufferedImage user_img, String job, List<Coregemstone> result, int cnt,
			ResultGetCoreList result_get_core_list) throws IOException {
		BufferedImage zero_img = ImageIO.read(new File(UTILIMAGEPATH + "zero_target.png"));
		Graphics2D g = user_img.createGraphics();
		g.setFont(new Font(null, Font.BOLD, 30));
		g.setStroke(new BasicStroke(3));
		g.setColor(Color.RED);
		int limit_height = user_img.getHeight() - zero_img.getHeight();
		int limit_width = user_img.getWidth() - zero_img.getWidth();
		for (int r = 0; r < limit_height; r++) {
			for (int c = 0; c < limit_width; c++) {
				BufferedImage sub_image = user_img.getSubimage(c, r, zero_img.getWidth(), zero_img.getHeight());
				if (matchImage(sub_image, zero_img) < 5) {
					Coregemstone coregemstone = new Coregemstone(0, 0, new int[3]);
					coregemstone.setCore_id(cnt);
					coregemstone.setImg(user_img.getSubimage(c - 6, r - 3, 32, 44));
					g.drawString(Integer.toString(cnt), c + 28, r + 30);
					g.drawRect(c - 6, r + 9, 32, 32);
					cnt++;
					result.add(coregemstone);
				}

			}
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(user_img, "png", baos);
		String base64String = DatatypeConverter.printBase64Binary(baos.toByteArray());
		String dataUrlString = "data:image/png;base64," + base64String;
		result_get_core_list.addRequest_image_list(dataUrlString);

		return cnt;
	}

	// 코어이미지로 어떤 스킬인지 파악하는 함수
	public void setCogemListLabeling(String job, ResultGetCoreList result_get_core_list) {
		File[] left_file = new File(UTILIMAGEPATH + "skill_part/" + job + "/left").listFiles();
		File[] top_file = new File(UTILIMAGEPATH + "skill_part/" + job + "/top").listFiles();
		File[] right_file = new File(UTILIMAGEPATH + "skill_part/" + job + "/right").listFiles();
		File[] level_file = new File(UTILIMAGEPATH + "level").listFiles();
		Arrays.sort(left_file, new FileComparator());
		Arrays.sort(top_file, new FileComparator());
		Arrays.sort(right_file, new FileComparator());
		Arrays.sort(level_file, new FileComparator());
		List<Coregemstone> list = result_get_core_list.getCore_list();
		for (int i = 0; i < list.size(); i++) {
			int[] tmp_list = { -1, -1, -1 };
			double[] tmp_min_check = { 100, 100, 100 };
			BufferedImage target_img = list.get(i).getImg();

			BufferedImage target_part_img = target_img.getSubimage(2, 28, 14, 14);

			for (int j = 0; j < left_file.length; j++) {
				try {
					double result_match = matchImage(target_part_img, ImageIO.read(left_file[j]));
					if (result_match < tmp_min_check[0] && result_match < 5) {
						tmp_list[0] = j;
						tmp_min_check[0] = result_match;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			target_part_img = target_img.getSubimage(9, 14, 14, 9);

			for (int j = 0; j < top_file.length; j++) {
				try {
					double result_match = matchImage(target_part_img, ImageIO.read(top_file[j]));
					if (result_match < tmp_min_check[1] && result_match < 5) {
						tmp_list[1] = j;
						tmp_min_check[1] = result_match;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			target_part_img = target_img.getSubimage(17, 28, 13, 14);

			for (int j = 0; j < right_file.length; j++) {
				try {
					double result_match = matchImage(target_part_img, ImageIO.read(right_file[j]));
					if (result_match < tmp_min_check[2] && result_match < 5) {
						tmp_list[2] = j;
						tmp_min_check[2] = result_match;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			target_part_img = target_img.getSubimage(6, 0, 20, 10);
			int tmp_level = -1;
			double check_min_match_val = 100;
			for (int j = 0; j < level_file.length; j++) {
				try {
					double result_match = matchImage(target_part_img, ImageIO.read(level_file[j]));
					if (result_match < check_min_match_val) {
						tmp_level = j + 1;
						check_min_match_val = result_match;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			list.get(i).setLevel(tmp_level);
			list.get(i).setSkill_data(tmp_list);

		}
		list = list.stream().filter(a -> a.getSkill_data()[2] != -1).collect(Collectors.toList());
		result_get_core_list.setCore_list(list);

	}

	// 서버가 가지고 있지 않는 코어이미지라면 저장
	public void makeSkillMixImage(String job, ResultGetCoreList result_get_core_list) {
		for (Coregemstone core : result_get_core_list.getCore_list()) {
			int[] target_list = core.getSkill_data();
			File target_file = new File(UTILIMAGEPATH + "skill_mix/" + job + "/" + target_list[0] + "," + target_list[1]
					+ "," + target_list[2] + ".png");
			if (!target_file.exists() && target_list[0] != -1 && target_list[1] != -1 && target_list[2] != -1) {
				try {
					// System.out.println("make:
					// "+target_list[0]+","+target_list[1]+","+target_list[2]+".png");
					ImageIO.write(core.getImg().getSubimage(0, 12, 32, 32), "png", target_file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public Resource getResourceImage(String job, String skill_name) {
		String target_path = UTILIMAGEPATH + "skill_origin/" + job + "/" + skill_name;
		Resource resource = new FileSystemResource(target_path);
		if (resource.exists()) {
			return resource;
		} else {
			return null;
		}
	}

	public void setJobSkillName(String job, ResultGetCoreList result_get_core_list) {
		File[] file_list = new File(UTILIMAGEPATH + "skill_origin/" + job).listFiles();
		Arrays.sort(file_list, new FileComparator());
		for (File tmp_file : file_list) {
			result_get_core_list.addSkillName(tmp_file.getName());
		}
	}

	// 리눅스환경을 위해 이름순으로 파일을 담기위한 Comparator(이름순 정렬되어있는 것을 보장하지 않는다.)
	class FileComparator implements Comparator<File> {
		@Override
		public int compare(File f1, File f2) {
			return f1.getName().compareTo(f2.getName());
		}
	}

	// user_img와 src_img 이미지 비교 return값은 오차율, png, jpg차이 혹은 게임 패치에 의한 픽셀조정에 따라서 오차율
	// 5%는 감안해야 정확히 나옴
	public double matchImage(BufferedImage user_img, BufferedImage src_img) {
		long diff = 0;
		for (int r = 0; r < src_img.getHeight(); r++) {
			for (int c = 0; c < src_img.getWidth(); c++) {
				int pixel1 = user_img.getRGB(c, r);
				Color color1 = new Color(pixel1, true);
				int r1 = color1.getRed();
				int g1 = color1.getGreen();
				int b1 = color1.getBlue();
				int pixel2 = src_img.getRGB(c, r);
				Color color2 = new Color(pixel2, true);
				int r2 = color2.getRed();
				int g2 = color2.getGreen();
				int b2 = color2.getBlue();

				long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
				diff = diff + data;
			}
		}
		double avg = diff / (src_img.getHeight() * src_img.getWidth() * 3);
		double percentage = (avg / 255) * 100;
		return percentage;

	}

}
