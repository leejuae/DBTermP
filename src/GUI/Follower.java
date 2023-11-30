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
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;

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

// Follower 클래스는 JFrame을 상속하여 팔로워를 보여주는 창을 생성합니다.
public class Follower extends JFrame{
	
	private JPanel panel;
	private ImageAvatar imageAvatar;

	// 생성자
	public Follower(String follow_id) {
		// JFrame 설정
		setBackground(new Color(255, 255, 255));
		setBounds(100,100,480,800);

		// Font 설정
		Font font = new Font("DialogInput", Font.BOLD, 20);

		// Connection 객체 생성 및 팔로워 목록 조회
		FollowDao followDao = new FollowDao();
		List<String> follower = followDao.selectfollowerList(follow_id);
		int num_of_follow = follower.size();

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
				new MainFeed();
			}
		});
		Logo.setBounds(12, 5, 50, 50);
		Logo.setBackground(new Color(255, 255,255));
		Logo.setBounds(200, 5, 50, 50);

		
		appbar.add(Logo);

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

		// JLayeredPane과 JScrollPane을 사용하여 스크롤 가능한 팔로워 목록 구성
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

		UserDao userDao = new UserDao();
		// 팔로워 목록을 UserPanel로 구성하여 스크롤 가능한 패널에 추가
		for(int i=0;i<num_of_follow;i++) {
			UserDto followerDto = new UserDto();
			followerDto = userDao.selectUser(follower.get(i));
			UserDto u= followerDto;
			UserPanel p1=new UserPanel(u);
			p1.setBackground(new Color(255, 255, 255));
			panel_3.add(p1);
		}			

		// 레이아웃 설정 및 창 표시
		layeredpanel.add(scrollpane);
		setVisible(true);
	}
}