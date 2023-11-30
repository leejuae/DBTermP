package GUI;

import Dao.UserDao;
import Dto.user.UserDto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class Setting extends JFrame {

	private JFrame frame;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	String nickname = null;
	String profileDir = null;
	String backgroundDir = null;
	String email = "Email not saved. Please enter e-mail!";
	private JTextField textField_6;
	private JTextField textField_7;
	public Setting(String id){

		JPanel contentPane;
		JTextField textField;
		JTextField textField_1;
		
		//Connection connection =  null;
		
		setBounds(0, 0, 478, 763);
		setLocationRelativeTo(null);
		setTitle("Setting");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel(); //메인
		JPanel panel_1 = new JPanel(); //닉네임 변경
		JPanel panel_2 = new JPanel(); //이메일 변경
		JPanel panel_3 = new JPanel(); //내 정보
		JPanel panel_4 = new JPanel(); //프로필 이미지 및 배경사진 변경
		JPanel panel_5 = new JPanel(); //비밀번호 변경
		JPanel panel_6 = new JPanel(); //한줄소개 변경
		
		panel.setVisible(true);
		panel_1.setVisible(false);
		panel_2.setVisible(false);
		panel_3.setVisible(false);
		panel_4.setVisible(false);
		panel_5.setVisible(false);
		panel_6.setVisible(false);
		
		panel.setBounds(0, 0, 478, 763);
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		
		JTextArea txtrSetting = new JTextArea();
		txtrSetting.setEditable(false);
		txtrSetting.setFont(new Font("Tahoma", Font.BOLD, 33));
		//txtrSetting.setFont(new Font("맑은 고딕", Font.BOLD, 27));
		txtrSetting.setText("Setting");
		txtrSetting.setBackground(new Color(255, 255, 255));
		txtrSetting.setBounds(12, 26, 185, 53);
		panel.add(txtrSetting);
		
		JTextArea txtrEditProfile = new JTextArea();
		txtrEditProfile.setEditable(false);
		txtrEditProfile.setFont(new Font("Tahoma", Font.BOLD, 23));
		txtrEditProfile.setText("Edit profile");
		txtrEditProfile.setBackground(new Color(255, 255, 255));
		txtrEditProfile.setBounds(50, 136, 212, 31);
		panel.add(txtrEditProfile);
		
		JButton btnNewButton = new JButton(ImageManager.GetImageUsingFileSystem("src/assets/UI/change_nickname.png",305, 58));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnNewButton.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 505);
				setLocationRelativeTo(null);
				panel_1.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnNewButton.setBounds(0, 187, 447, 53);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(ImageManager.GetImageUsingFileSystem("src/assets/UI/change_email.png",305, 58));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_1.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnNewButton_1.setBounds(0, 327, 447, 53);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 505);
				setLocationRelativeTo(null);
				panel_2.setVisible(true);
				panel.setVisible(false);
			}
		});
		JTextArea txtrCheakProfile = new JTextArea();
		txtrCheakProfile.setEditable(false);
		txtrCheakProfile.setText("Check profile");
		txtrCheakProfile.setFont(new Font("Tahoma", Font.BOLD, 23));
		txtrCheakProfile.setBackground(new Color(255, 255, 255));
		txtrCheakProfile.setBounds(50, 561, 212, 31);
		panel.add(txtrCheakProfile);
		
		JButton btnNewButton_2 = new JButton(ImageManager.GetImageUsingFileSystem("src/assets/UI/my_profile_center.png",305, 58));
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnNewButton_2.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnNewButton_2.setBounds(0, 612, 447, 53);
		panel.add(btnNewButton_2);
		
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 550);
				setLocationRelativeTo(null);
				panel_3.setVisible(true);
				panel.setVisible(false);
			}
		});
		
		ImageIcon img = ImageManager.GetImageUsingFileSystem("src/assets/UI/back.png", 50, 50);
		
		JButton btnNewButton_3_1 = new JButton(img);
		btnNewButton_3_1.setBounds(340, -20, 150, 150);
		btnNewButton_3_1.setBorderPainted(false);
		btnNewButton_3_1.setFocusPainted(false);
		btnNewButton_3_1.setOpaque(false);
		btnNewButton_3_1.setContentAreaFilled(false);
		btnNewButton_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//프로필 화면으로 돌아감
				dispose();
			}
		});
		panel.add(btnNewButton_3_1);
		
		//이미지 바꾸기
		JButton btnChangeImage = new JButton(ImageManager.GetImageUsingFileSystem("src/assets/UI/change_image.png",305, 58));
		btnChangeImage.setBorderPainted(false);
		btnChangeImage.setFocusPainted(false);
		btnChangeImage.setOpaque(false);
		btnChangeImage.setContentAreaFilled(false);
		btnChangeImage.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnChangeImage.setBackground(Color.WHITE);
		btnChangeImage.setBounds(0, 397, 447, 53);
		btnChangeImage.setHorizontalAlignment(SwingConstants.CENTER);
		btnChangeImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 505);
				setLocationRelativeTo(null);
				panel_4.setVisible(true);
				panel.setVisible(false);
			}
		});
		panel.add(btnChangeImage);
		
		//비밀번호 바꾸기
		JButton btnChangePassword = new JButton(ImageManager.GetImageUsingFileSystem("src/assets/UI/change_password.png",305, 58));
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 505);
				setLocationRelativeTo(null);
				panel.setVisible(false);
				panel_5.setVisible(true);
			}
		});
		btnChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
		btnChangePassword.setBorderPainted(false);
		btnChangePassword.setFocusPainted(false);
		btnChangePassword.setOpaque(false);
		btnChangePassword.setContentAreaFilled(false);
		btnChangePassword.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnChangePassword.setBackground(Color.WHITE);
		btnChangePassword.setBounds(0, 257, 447, 53);
		panel.add(btnChangePassword);
		
		//
		JButton btnChangeMessage = new JButton(ImageManager.GetImageUsingFileSystem("src/assets/UI/change_intro.png",305, 58));
		btnChangeMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 505);
				setLocationRelativeTo(null);
				panel_6.setVisible(true);
				panel.setVisible(false);
			}
		});
		btnChangeMessage.setHorizontalAlignment(SwingConstants.CENTER);
		btnChangeMessage.setBorderPainted(false);
		btnChangeMessage.setFocusPainted(false);
		btnChangeMessage.setOpaque(false);
		btnChangeMessage.setContentAreaFilled(false);
		btnChangeMessage.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 20));
		btnChangeMessage.setBackground(Color.WHITE);
		btnChangeMessage.setBounds(0, 467, 447, 53);
		panel.add(btnChangeMessage);
		
		


		////////////////패널 1/////////////////
		panel_1.setBounds(0, 0, 473, 505);
		panel_1.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextArea txtrSetting_1 = new JTextArea();
		txtrSetting_1.setEditable(false);
		txtrSetting_1.setText("Setting");
		txtrSetting_1.setFont(new Font("Tahoma", Font.BOLD, 33));
		txtrSetting_1.setBackground(Color.WHITE);
		txtrSetting_1.setBounds(12, 26, 185, 53);
		panel_1.add(txtrSetting_1);
		
		JTextArea txtrYourNickname = new JTextArea();
		txtrYourNickname.setEditable(false);
		txtrYourNickname.setText("Your nickname");
		txtrYourNickname.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtrYourNickname.setBackground(Color.WHITE);
		txtrYourNickname.setBounds(12, 108, 212, 31);
		panel_1.add(txtrYourNickname);
		
		JTextArea txtrChangeNickname = new JTextArea();
		txtrChangeNickname.setEditable(false);
		txtrChangeNickname.setText("Change nickname");
		txtrChangeNickname.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtrChangeNickname.setBackground(Color.WHITE);
		txtrChangeNickname.setBounds(12, 238, 212, 36);
		panel_1.add(txtrChangeNickname);

		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();

		// 현재 닉네임
		userDto = userDao.selectUser(id);
		nickname = userDto.getName();
		textField = new JTextField(nickname);
		textField.setForeground(Color.black);
		textField.setEditable(false);
		textField.setBounds(12, 154, 449, 53);
		panel_1.add(textField);
		textField.setColumns(10);

		//바꿀 닉네임
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(12, 284, 449, 53);
		panel_1.add(textField_1);
		String text = textField_1.getText();
		textField_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String p1 = "update user set name = \""+text+"\"where user_id= \"" + id + "\";";
				if(text.compareTo(nickname) == 0) {
					new CustomDialog("Setting", "Cannot change to same nickname!");
				}
				else if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "Nickname Changed!");
				}
			}
		});

		JButton btnNewButton_3_3 = new JButton(img);
		//JButton btnNewButton_3_3 = new JButton("New button");
		btnNewButton_3_3.setBounds(396, 10, 65, 62);
		btnNewButton_3_3.setBorderPainted(false);
		btnNewButton_3_3.setFocusPainted(false);
		btnNewButton_3_3.setOpaque(false);
		btnNewButton_3_3.setContentAreaFilled(false);
		panel_1.add(btnNewButton_3_3);
		btnNewButton_3_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 763);
				setLocationRelativeTo(null);
				panel.setVisible(true);
				panel_1.setVisible(false);
			}
		});

//		panel_2.setBackground(new Color(255, 255, 255));
//		panel_2.setBounds(0, 0, 473, 505);
//		getContentPane().add(panel_2);
//		panel_2.setLayout(null);
//
//		JTextArea txtrSetting_2 = new JTextArea();
//		txtrSetting_2.setEditable(false);
//		txtrSetting_2.setText("Setting");
//		txtrSetting_2.setFont(new Font("Tahoma", Font.BOLD, 33));
//		txtrSetting_2.setBackground(Color.WHITE);
//		txtrSetting_2.setBounds(12, 26, 185, 53);
//		panel_2.add(txtrSetting_2);

//		JTextArea txtrYourIntro = new JTextArea("Introduction not saved. Please enter introduction!!");
//		txtrYourIntro.setEditable(false);
//		txtrYourIntro.setText("Your introduction");
//		txtrYourIntro.setFont(new Font("Tahoma", Font.PLAIN, 23));
//		txtrYourIntro.setBackground(Color.WHITE);
//		txtrYourIntro.setBounds(12, 108, 212, 31);
//		panel_2.add(txtrYourIntro);
//
//		email = userDto.getIntroduction();
//		textField = new JTextField(email);
//		textField.setForeground(Color.black);
//		textField.setEditable(false);
//		textField.setBounds(12, 154, 449, 53);
//		panel_2.add(textField);
//		textField.setColumns(10);


//		JTextArea txtrChangeEmail = new JTextArea();
//		txtrChangeEmail.setEditable(false);
//		txtrChangeEmail.setText("Change introduction");
//		txtrChangeEmail.setFont(new Font("Tahoma", Font.PLAIN, 23));
//		txtrChangeEmail.setBackground(Color.WHITE);
//		txtrChangeEmail.setBounds(12, 238, 212, 36);
//		panel_2.add(txtrChangeEmail);

//		textField_2 = new JTextField();
//		textField_2.setColumns(10);
//		textField_2.setBounds(12, 284, 449, 53);
//		panel_2.add(textField_2);
//		textField_2.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String text = textField_2.getText();
//				String p1 = "update user set introduction = \""+text+"\"where user_id= \"" + id + "\";";
//				if(text.compareTo(email) == 0) {
//					new CustomDialog("Setting", "Cannot change to same E-mail!");
//				}
//				else if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
//					new CustomDialog("Setting", "E-mail Changed!");
//				}
//			}
//		});

//		JButton btnNewButton_3 = new JButton(img);
//		btnNewButton_3.setBounds(396, 10, 65, 62);
//		btnNewButton_3.setBorderPainted(false);
//		btnNewButton_3.setFocusPainted(false);
//		btnNewButton_3.setOpaque(false);
//		btnNewButton_3.setContentAreaFilled(false);
//		panel_2.add(btnNewButton_3);
//		btnNewButton_3.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				setBounds(0, 0, 478, 763);
//				setLocationRelativeTo(null);
//				panel.setVisible(true);
//				panel_2.setVisible(false);
//			}
//		});



		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(0, 0, 473, 550);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JTextArea txtrSetting_3 = new JTextArea();
		txtrSetting_3.setEditable(false);
		txtrSetting_3.setText("Setting");
		txtrSetting_3.setFont(new Font("Tahoma", Font.BOLD, 33));
		txtrSetting_3.setBackground(Color.WHITE);
		txtrSetting_3.setBounds(12, 26, 185, 53);
		panel_3.add(txtrSetting_3);
		
		JTextArea txtrNickname = new JTextArea();
		txtrNickname.setEditable(false);
		txtrNickname.setText("ID");
		txtrNickname.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtrNickname.setBackground(Color.WHITE);
		txtrNickname.setBounds(12, 129, 212, 31);
		panel_3.add(txtrNickname);

		JTextArea txtrNickname_2 = new JTextArea();
		txtrNickname_2.setEditable(false);
		txtrNickname_2.setText("Nickname");
		txtrNickname_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtrNickname_2.setBackground(Color.WHITE);
		txtrNickname_2.setBounds(12, 247, 212, 31);
		panel_3.add(txtrNickname_2);
		
		JTextArea txtrNickname_2_1 = new JTextArea();
		txtrNickname_2_1.setEditable(false);
		txtrNickname_2_1.setText("e-mail");
		txtrNickname_2_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtrNickname_2_1.setBackground(Color.WHITE);
		txtrNickname_2_1.setBounds(12, 366, 212, 31);
		panel_3.add(txtrNickname_2_1);
		
		
		textField_3 = new JTextField(id);
		textField_3.setColumns(10);
		textField_3.setBounds(12, 174, 449, 53);
		textField_3.setForeground(Color.black);
		textField_3.setEditable(false);	
		panel_3.add(textField_3);
		
		textField_4 = new JTextField(nickname);
		textField_4.setColumns(10);
		textField_4.setBounds(12, 290, 449, 53);
		textField_4.setForeground(Color.black);
		textField_4.setEditable(false);	
		panel_3.add(textField_4);
		
		textField_5 = new JTextField(email);
		textField_5.setColumns(10);
		textField_5.setBounds(12, 409, 449, 53);
		textField_5.setForeground(Color.black);
		textField_5.setEditable(false);
		panel_3.add(textField_5);
		
		JButton btnNewButton_3_2 = new JButton(img);
		btnNewButton_3_2.setBounds(396, 10, 65, 62);
		btnNewButton_3_2.setBorderPainted(false);
		btnNewButton_3_2.setFocusPainted(false);
		btnNewButton_3_2.setOpaque(false);
		btnNewButton_3_2.setContentAreaFilled(false);
		panel_3.add(btnNewButton_3_2);
		btnNewButton_3_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 763);
				setLocationRelativeTo(null);
				panel.setVisible(true);
				panel_3.setVisible(false);
			}
		});
		
		////////////////패널 4/////////////////
		panel_4.setBounds(0, 0, 473, 505);
		panel_4.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnNewButton_3_4 = new JButton(img);
		btnNewButton_3_4.setBounds(394, 10, 65, 62);
		btnNewButton_3_4.setBorderPainted(false);
		btnNewButton_3_4.setFocusPainted(false);
		btnNewButton_3_4.setOpaque(false);
		btnNewButton_3_4.setContentAreaFilled(false);
		btnNewButton_3_4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 763);
				setLocationRelativeTo(null);
				panel.setVisible(true);
				panel_4.setVisible(false);
			}
		});
		panel_4.add(btnNewButton_3_4);
		
		JTextArea txtrSetting_2_1_1 = new JTextArea();
		txtrSetting_2_1_1.setEditable(false);
		txtrSetting_2_1_1.setText("Setting");
		txtrSetting_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 33));
		txtrSetting_2_1_1.setBackground(Color.WHITE);
		txtrSetting_2_1_1.setBounds(12, 26, 185, 53);
		panel_4.add(txtrSetting_2_1_1);
		
		JTextArea txtrChangeProfileImage = new JTextArea("Change profile image");
		txtrChangeProfileImage.setEditable(false);
		txtrChangeProfileImage.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtrChangeProfileImage.setBackground(Color.WHITE);
		txtrChangeProfileImage.setBounds(12, 108, 249, 35);
		panel_4.add(txtrChangeProfileImage);
		
		JTextArea txtrChangeBackgroundImage = new JTextArea();
		txtrChangeBackgroundImage.setEditable(false);
		txtrChangeBackgroundImage.setText("Change background image");
		txtrChangeBackgroundImage.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtrChangeBackgroundImage.setBackground(Color.WHITE);
		txtrChangeBackgroundImage.setBounds(12, 238, 311, 40);
		panel_4.add(txtrChangeBackgroundImage);
		
		try {
			//자신의 프로필 사진 경로
				String q1 = "select profile_pic from user where user_id = \"" + id + "\";";
				ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
				if(rs.next()) {
					profileDir = rs.getString(1);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		try {
			//자신의 배경 사진 경로
				String q1 = "select background_pic from user where user_id = \"" + id + "\";";
				ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
				if(rs.next()) {
					backgroundDir = rs.getString(1);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		textField_6 = new JTextField();
		textField_6.setToolTipText("Enter profile image path");
		textField_6.setColumns(10);
		textField_6.setBounds(12, 153, 449, 53);
		textField_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField_6.getText();
				String p1 = "update user set profile_pic = \""+text+"\"where user_id= \"" + id + "\";";
				if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "profile image Changed!");
				}
			}
		});
		panel_4.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(12, 284, 449, 53);
		//TextHint hint = new TextHint(textField_7, "Enter background image path");
		textField_7.setToolTipText("Enter background image path");
		textField_7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField_7.getText();
				String p1 = "update user set bacground_pic = \""+text+"\"where user_id= \"" + id + "\";";
				if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "background image Changed!");
				}
			}
		});
		panel_4.add(textField_7);
		//panel_4.add(hint);

		//비밀번호 변경 부분 시간 되면
		panel_5.setBounds(0, 0, 473, 505);
		panel_5.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_5);
		panel_5.setLayout(null);
		
		JButton btnNewButton_5_1 = new JButton(img);
		btnNewButton_5_1.setBounds(396, 10, 65, 62);
		btnNewButton_5_1.setBorderPainted(false);
		btnNewButton_5_1.setFocusPainted(false);
		btnNewButton_5_1.setOpaque(false);
		btnNewButton_5_1.setContentAreaFilled(false);
		panel_5.add(btnNewButton_5_1);
		btnNewButton_5_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 763);
				setLocationRelativeTo(null);
				panel.setVisible(true);
				panel_5.setVisible(false);
			}
		});
		
		
		JTextArea txtrSetting_5 = new JTextArea();
		txtrSetting_5.setEditable(false);
		txtrSetting_5.setText("Setting");
		txtrSetting_5.setFont(new Font("Tahoma", Font.BOLD, 33));
		txtrSetting_5.setBackground(Color.WHITE);
		txtrSetting_5.setBounds(12, 26, 185, 53);
		panel_5.add(txtrSetting_5);
		
		JTextArea txt = new JTextArea();
		txt.setEditable(false);
		txt.setText("Setting");
		txt.setFont(new Font("Thoma", Font.BOLD, 33));
		txt.setBackground(Color.WHITE);
		txt.setBounds(12, 26, 130, 46);
		panel_5.add(txt);
		
		JTextArea txtYourPassword = new JTextArea();
		txtYourPassword.setEditable(false);
		//txtrYourNickname.setEnabled(false);
		txtYourPassword.setText("Check Your password");
		txtYourPassword.setFont(new Font("Thoma", Font.PLAIN, 23));
		txtYourPassword.setBackground(Color.WHITE);
		txtYourPassword.setBounds(12, 108, 250, 31);
		panel_5.add(txtYourPassword);
		
		JTextArea txtrChangePassword = new JTextArea();
		txtrChangePassword.setEditable(false);
		txtrChangePassword.setText("Change password");
		txtrChangePassword.setFont(new Font("Thoma", Font.PLAIN, 23));
		txtrChangePassword.setBackground(Color.WHITE);
		txtrChangePassword.setBounds(12, 238, 220, 36);
		panel_5.add(txtrChangePassword);
		txtrChangePassword.setVisible(false);
		
		
		
		JPasswordField textInPassword = new JPasswordField();
		textInPassword.setEchoChar('*');
		textInPassword.setColumns(10);
		textInPassword.setBounds(12, 284, 449, 53);
		panel_5.add(textInPassword);
		textInPassword.setVisible(false);
		
		
		String pwd = null;
		
		//자신의 비밀번호 확인
		JPasswordField textCheckPwd = new JPasswordField();
		textCheckPwd.setEchoChar('*');
		textCheckPwd.setForeground(Color.black);
		textCheckPwd.setBounds(12, 154, 449, 53);
		panel_5.add(textCheckPwd);
		textCheckPwd.setColumns(10);
		textCheckPwd.setVisible(true);
		
		
		try {
		//자신의 비밀번호
			String q1 = "select pw from user where user_id = \"" + ClientInformation.Logined_id + "\";";
			ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
			if(rs.next()) {
				pwd = rs.getString(1);
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		String copyPwd = pwd;
		textCheckPwd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(copyPwd.equals(textCheckPwd.getText())) {
					txtrChangePassword.setVisible(true);
					textInPassword.setVisible(true);
				}
				else {
					new CustomDialog("Setting", "Incorrect password");
				}
			}
		});
		textInPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inPwd = textInPassword.getText();
				String p1 = "update user set pw = \""+inPwd+"\"where user_id= \"" + ClientInformation.Logined_id + "\";";
				if(inPwd.equals(copyPwd)) {
					new CustomDialog("Setting", "Cannot change to same password!");
				}
				else if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "password Changed!");
				}
			}
		});
		
		JButton btnNewButton_5 = new JButton(img);
		btnNewButton_5.setBounds(396, 10, 65, 62);
		btnNewButton_5.setBorderPainted(false);
		btnNewButton_5.setFocusPainted(false);
		btnNewButton_5.setOpaque(false);
		btnNewButton_5.setContentAreaFilled(false);
		panel_3.add(btnNewButton_5);
		btnNewButton_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 763);
				setLocationRelativeTo(null);
				panel.setVisible(true);
				panel_5.setVisible(false);
			}
		});
		
		/////////////////////한줄소개 변경//////////////
		panel_6.setBounds(0, 0, 473, 505);
		panel_6.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel_6);
		panel_6.setLayout(null);
		
		JTextArea txt2 = new JTextArea();
		txt2.setEditable(false);
		txt2.setText("Setting");
		txt2.setFont(new Font("Tahoma", Font.BOLD, 33));
		txt2.setBackground(Color.WHITE);
		txt2.setBounds(12, 26, 185, 53);
		panel_6.add(txt2);
		
		JTextArea txtYourIntroduce = new JTextArea();
		txtYourIntroduce.setEditable(false);
		//txtrYourNickname.setEnabled(false);
		txtYourIntroduce.setText("Your introduce");
		txtYourIntroduce.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtYourIntroduce.setBackground(Color.WHITE);
		txtYourIntroduce.setBounds(12, 108, 212, 31);
		panel_6.add(txtYourIntroduce);
		
		JTextArea txtrChangeIntroduce = new JTextArea();
		txtrChangeIntroduce.setEditable(false);
		txtrChangeIntroduce.setText("Change introduce");
		txtrChangeIntroduce.setFont(new Font("Tahoma", Font.PLAIN, 23));
		txtrChangeIntroduce.setBackground(Color.WHITE);
		txtrChangeIntroduce.setBounds(12, 238, 212, 36);
		panel_6.add(txtrChangeIntroduce);

		String introduce = null;
		try {
		//자신의 한줄소개
			String q1 = "select introduction from user where user_id = \"" + id + "\";";
			ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(),q1);
			if(rs.next()) {
				introduce = rs.getString(1);
				textField = new JTextField(introduce);
				textField.setForeground(Color.black);
				textField.setEditable(false);
				textField.setBounds(12, 154, 449, 53);
				panel_6.add(textField);
				textField.setColumns(10);	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//바꿀 한줄소개
		JTextField textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(12, 284, 449, 53);
		panel_6.add(textField_8);
		
		JButton btnNewButton_3_5 = new JButton(img);
		btnNewButton_3_5.setBounds(394, 10, 65, 62);
		btnNewButton_3_5.setBorderPainted(false);
		btnNewButton_3_5.setFocusPainted(false);
		btnNewButton_3_5.setOpaque(false);
		btnNewButton_3_5.setContentAreaFilled(false);
		btnNewButton_3_5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setBounds(0, 0, 478, 763);
				setLocationRelativeTo(null);
				panel.setVisible(true);
				panel_6.setVisible(false);
			}
		});
		panel_6.add(btnNewButton_3_5);
		textField_8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = textField_8.getText();
				String p1 = "update user set introduction = \""+text+"\"where user_id= \"" + id + "\";";
				if(SQLMethods.ExecuteUpdate(SQLMethods.GetCon(), p1) != 0) {
					new CustomDialog("Setting", "Introduce Changed!");
				}
			}
		});
		//contentPane = new JPanel();
		setVisible(true);
	}
}

