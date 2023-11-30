package GUI;

import Dao.UserDao;
import Dto.user.UserDto;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageManager {
	// 파일 시스템에서 이미지를 가져와서 크기를 조정한 후 ImageIcon으로 반환하는 메서드
	public static ImageIcon GetImageUsingFileSystem(String url, int w, int h){
		ImageIcon result = null;

		try {
			BufferedImage img = ImageIO.read(new File(url));

			result = new ImageIcon(img);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image temp = result.getImage();
		Image changeTemp = temp.getScaledInstance(w,h, Image.SCALE_SMOOTH);
		result = new ImageIcon(changeTemp);

		return result;
	}

	// URL에서 이미지를 가져와서 크기를 조정한 후 ImageIcon으로 반환하는 메서드
	public static ImageIcon GetImageUsingURL(String url, int w, int h){
		ImageIcon result = null;

		try {
			BufferedImage img = ImageIO.read(new URL(url));
			result = new ImageIcon(img);
		} catch (MalformedURLException e) {
			System.out.println("Malformed URL: " + url); // Print the URL causing the issue
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Image temp = result.getImage();
		Image changeTemp = temp.getScaledInstance(w,h, Image.SCALE_SMOOTH);
		result = new ImageIcon(changeTemp);

		return result;
	}

	// 사용자 프로필 이미지를 가져와서 처리하는 메서드
	public static ImageIcon GetUserProfile(String user_id, int w, int h) {
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();
		userDto = userDao.selectUser(user_id);
		ImageIcon result = null;

		if(userDto != null) {
			if((userDto.getProfile_pic()).equals("0000"))
				result = ImageManager.GetImageUsingFileSystem("src/assets/userImages/user.png", w, h);
			else
				result = ImageManager.GetImageUsingURL(userDto.getProfile_pic(), w, h);
		}
		else {
			result = ImageManager.GetImageUsingFileSystem("src/assets/userImages/user.png", w, h);
		}


		return result;
	}

	// 사용자 배경 이미지를 가져와서 처리하는 메서드
	public static ImageIcon GetUserBackground(String user_id, int w, int h) {
		ImageIcon result = null;

		result = ImageManager.GetImageUsingFileSystem("src/assets/cloud.jpg", w, h);

		return result;
	}
}