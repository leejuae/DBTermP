package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageManager {
	//�����
	//ImageIcon img = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 100, 100);
    public static ImageIcon GetImageUsingFileSystem(String url, int w, int h){
        ImageIcon result = null;

        try {
            BufferedImage img = ImageIO.read(new File(url));

            result = new ImageIcon(img);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Image temp = result.getImage();
        Image changeTemp = temp.getScaledInstance(w,h, Image.SCALE_SMOOTH);
        result = new ImageIcon(changeTemp);

        return result;
    }
    
    //�����
    //ImageIcon img = ImageManager.GetImageUsingURL("https://pbs.twimg.com/profile_images/1374979417915547648/vKspl9Et_400x400.jpg", 100, 100);
    public static ImageIcon GetImageUsingURL(String url, int w, int h){
        ImageIcon result = null;

        try {
            BufferedImage img = ImageIO.read(new URL(url));

            result = new ImageIcon(img);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Image temp = result.getImage();
        Image changeTemp = temp.getScaledInstance(w,h, Image.SCALE_SMOOTH);
        result = new ImageIcon(changeTemp);

        return result;
    }
 
//    public static ImageIcon GetUserProfile(String user_id, int w, int h) {
//    	String q1 = "select profile_Image_dir from user where user_id = \"" + user_id + "\";";
//		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
//		ImageIcon result = null;
//		try {
//			if(rs.next()) {
//				if(rs.getString(1).compareTo("") == 0)
//					result = ImageManager.GetImageUsingFileSystem("src/assets/userImages/user.png", w, h);
//				else
//					result = ImageManager.GetImageUsingURL(rs.getString(1), w, h);
//
//			}
//			else {
//				result = ImageManager.GetImageUsingFileSystem("src/assets/userImages/user.png", w, h);
//
//			}
//
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return result;
//    }
//
//    public static ImageIcon GetUserBackground(String user_id, int w, int h) {
//    	String q1 = "select background_Image_dir from user where user_id = \"" + user_id + "\";";
//		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
//		ImageIcon result = null;
//		try {
//			if(rs.next()) {
//				if(rs.getString(1).compareTo("") == 0)
//					result = ImageManager.GetImageUsingFileSystem("src/assets/cloud.jpg", w, h);
//				else
//					result = ImageManager.GetImageUsingURL(rs.getString(1), w, h);
//
//			}
//			else {
//				result = ImageManager.GetImageUsingFileSystem("src/assets/cloud.jpg", w, h);
//
//			}
//
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		return result;

    }


//}

