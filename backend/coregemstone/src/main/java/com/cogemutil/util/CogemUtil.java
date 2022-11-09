package com.cogemutil.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Coregemstone;
import com.cogemutil.dto.ResultGetCoreList;

public class CogemUtil {
	
	private final static String UTILIMAGEPATH = "src/main/resources/utilImage/";
	public static void getCogemList(MultipartFile[] files, String job,ResultGetCoreList result_get_core_list) throws IOException {
		List<Coregemstone> result = new ArrayList<>();
		int cnt = 0;
		for(int i = 0 ; i < files.length;i++) {
			BufferedImage image = ImageIO.read(files[i].getInputStream());
			cnt = searchPoint(image,job,result,cnt,result_get_core_list);
		}
		result_get_core_list.setCore_list(result);
	}

	
	
	
	
	
	// 코어위치를 찾기위한 특정 이미지를 기준으로 좌표파악
	public static int searchPoint(BufferedImage user_img,String job,List<Coregemstone> result,int cnt,ResultGetCoreList result_get_core_list) throws IOException {
		BufferedImage zero_img = ImageIO.read(new File(UTILIMAGEPATH + "zero_target.png"));
		Graphics2D g  = user_img.createGraphics();
		g.setFont(new Font(null,Font.BOLD,20));
		g.setStroke(new BasicStroke(4));
		g.setColor(Color.RED);
		int limit_height = user_img.getHeight() - zero_img.getHeight();
		int limit_width = user_img.getWidth() - zero_img.getWidth();
		for (int r = 0; r < limit_height; r++) {
			for (int c = 0; c < limit_width; c++) {
				BufferedImage sub_image = user_img.getSubimage(c, r, zero_img.getWidth(), zero_img.getHeight());
				if (matchImage(sub_image, zero_img)) {
					System.out.println(r + "," + c);
					Coregemstone coregemstone = new Coregemstone(0,0,new int[3]);
					coregemstone.setCore_id(cnt);
					coregemstone.setImg(user_img.getSubimage(c-6, r+9, 32, 32));
					g.drawString(Integer.toString(cnt), c-6, r+7);
					g.drawRect(c-6, r+9, 32, 32);
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

	public void setCogemListLabeling(String job, ResultGetCoreList result_get_core_list) {
		List<Coregemstone> list = result_get_core_list.getCore_list();
		for(int i = 0 ; i < list.size();i++ ) {
			list.get(i).changeSkill_data(0, 1);
		}
		
	}
	
	// user_img와 src_img 이미지 비교 같으면 true 아니면 false
	public static boolean matchImage(BufferedImage user_img, BufferedImage src_img) {
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
				// sum of differences of RGB values of the two images
				long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
				diff = diff + data;
			}
		}
		double avg = diff/(src_img.getHeight()*src_img.getWidth()*3);
        double percentage = (avg/255)*100;

        if(percentage<5) {
    		return true;
        }else {
        	return false;
        }
	}


}
