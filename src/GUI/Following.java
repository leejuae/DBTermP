package GUI;

import Dao.FollowDao;
import Dao.UserDao;
import Dto.user.UserDto;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JScrollBar;

// 다양한 import 구문이 여기에 위치할 수 있습니다.

// 팔로잉 목록을 표시하는 JFrame 클래스
public class Following extends JFrame {
	private JPanel panel;

	// 생성자
	public Following(String follow_id) {
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 480, 800);

		Font font = new Font("DialogInput", Font.BOLD, 20);

		// Connection 객체 생성 및 팔로잉 목록 조회
		Connection con = SQLMethods.GetCon();
		FollowDao followDao = new FollowDao();
		List<String> following = followDao.selectfollowingList(follow_id);
		int num_of_follow = following.size();

		// JPanel 생성 및 설정
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel.setBackground(new Color(255, 255, 255));
		setContentPane(panel);

		// 앱 바 생성
		JPanel appbar = new JPanel();
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setPreferredSize(new Dimension(464,65));
		appbar.setLayout(null);
		panel.add(appbar);

		// 로고 이미지 생성 및 설정
		ImageIcon logo = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 50,50);
		
		JLabel Logo = new JLabel(logo);
		Logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		Logo.setBounds(12, 5, 50, 50);
		Logo.setBackground(new Color(255, 255,255));
		Logo.setBounds(200, 5, 50, 50);

		
		appbar.add(Logo);
		
		/*
		String q1 = "select profile_Image_dir from user where user_id = \"" + ClientInformation.Logined_id + "\";";
		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
		
		String imgUrl = "";
		try {
			if(rs.next()) {
				imgUrl = rs.getString(1);
			}
			if(imgUrl.compareTo("") == 0)  
				imgUrl = "https://play-lh.googleusercontent.com/38AGKCqmbjZ9OuWx4YjssAz3Y0DTWbiM5HB0ove1pNBq_o9mtWfGszjZNxZdwt_vgHo";
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/

		// 뒤로가기 이미지 생성 및 설정
		ImageIcon backImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/back.png",50,50);
		JLabel backBtn = new JLabel(backImage);
		backBtn.setBounds(12, 5, 50, 50);
		backBtn.setBackground(new Color(255, 255,255));
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});

		
		appbar.add(backBtn);

		// JLayeredPane과 JScrollPane을 사용하여 스크롤 가능한 팔로잉 목록 구성
		JLayeredPane layeredpanel = new JLayeredPane();
		layeredpanel.setPreferredSize(new Dimension(470, 700));
		layeredpanel.setBackground(new Color(255, 255, 255));
		panel.add(layeredpanel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(470, 700));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
			
		JScrollPane scrollpane=new JScrollPane(panel_3);
		scrollpane.setSize(470, 700);
		scrollpane.setBackground(new Color(255, 255, 255));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		// 팔로잉 목록을 UserPanel로 구성하여 스크롤 가능한 패널에 추가
		for(int i=0;i<num_of_follow;i++) {
			UserDao userDao = new UserDao();
			UserDto followingDto = new UserDto();
			followingDto = userDao.selectUser(following.get(i));
			UserDto u= followingDto;
			UserPanel p1=new UserPanel(u);
			panel_3.add(p1);
		}

		// 레이아웃 설정 및 창 표시
		layeredpanel.add(scrollpane);
		setVisible(true);
	}
}