package com.cogemutil.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import org.springframework.web.multipart.MultipartFile;

import com.cogemutil.dto.Coregemstone;


public class CogemUtil {
	private final static String UTILIMAGEPATH = "src/main/resources/utilImage/";
	public static List<Coregemstone> getCogemList(MultipartFile[] files) throws IOException{
		BufferedImage image = ImageIO.read(files[0].getInputStream());
		searchPoint(image);
		
		return null;
	}
	
	
	//코어위치를 찾기위한 특정 이미지를 기준으로 좌표파악
	public static void searchPoint(BufferedImage user_img) throws IOException {
		BufferedImage zero_img = ImageIO.read(new File(UTILIMAGEPATH+"use_search/"+"zero_target.png"));
		int limit_height = user_img.getHeight()-zero_img.getHeight();
		int limit_width = user_img.getWidth()-zero_img.getWidth();
		for(int r = 0 ; r < limit_height;r++) {
			for(int c = 0; c < limit_width;c++) {
				if(matchImage(user_img.getSubimage(c, r, zero_img.getWidth(), zero_img.getHeight()),zero_img)) {
					System.out.println(r+","+c);
					File target = new File(UTILIMAGEPATH+"use_search/"+(r+","+c)+".png");
					System.out.println(target.getAbsolutePath());
					ImageIO.write(
							user_img.getSubimage(c-6, r+9, 32, 32),
							"png", target);
				}
				
			}
		}
	}
	
	//user_img와 src_img 이미지 비교 같으면 true 아니면 false
	public static boolean matchImage(BufferedImage user_img,BufferedImage src_img) {
		//int cnt = 0;
		//int total = src_img.getHeight()*src_img.getWidth();
		for(int r = 0 ; r < src_img.getHeight();r++) {
			for(int c = 0; c < src_img.getWidth();c++) {
				if(Math.abs(user_img.getRGB(c, r)-src_img.getRGB(c, r))!=0) {
					return false;
				}
			}
		}
		return true;
	}
	
	
}
