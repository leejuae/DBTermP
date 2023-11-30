package GUI;

import Dao.LikeDao;
import Dao.UserDao;
import Dto.like.PostLikeDto;
import Dto.user.UserDto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class PostLike extends JFrame{
private JPanel panel;
	
	public PostLike(String post_id) {
		setBackground(new Color(255, 255, 255));
		setBounds(100,100,480,800);

		// 글꼴 설정
		Font font = new Font("DialogInput", Font.BOLD, 20);

		// DB 연결 및 좋아요를 누른 사용자 목록 가져오기
		LikeDao likeDao = new LikeDao();
		ArrayList<PostLikeDto> likers = likeDao.selectPostLike(post_id);
		int num_of_like = likers.size();

		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		panel.setBackground(new Color(255, 255, 255));
		setContentPane(panel);

		// 상단 패널 설정
		JPanel appbar = new JPanel();
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setPreferredSize(new Dimension(464, 65));
		appbar.setLayout(null);
		panel.add(appbar);

		// 로고 이미지 로드
		ImageIcon logo = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 50, 50);

		JLabel Logo = new JLabel(logo);
		// 로고 클릭 시 MainFeed 화면으로 이동하는 이벤트
		Logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFeed();
				dispose();
			}
		});
		Logo.setBounds(12, 5, 50, 50);
		Logo.setBackground(new Color(255, 255, 255));
		Logo.setBounds(200, 5, 50, 50);
		appbar.add(Logo);

		// 사용자의 프로필 이미지 가져오기
		UserDao userDao = new UserDao();
		UserDto rs = userDao.selectUser(ClientInformation.Logined_id);

		String imgUrl = rs.getProfile_pic();

		// 사용자 프로필 이미지 로드 및 클릭 시 Profile 화면으로 이동하는 이벤트
		ImageIcon userImage = ImageManager.GetUserProfile(imgUrl, 50, 50);
		JLabel UserBtn = new JLabel(userImage);
		UserBtn.setBounds(12, 5, 50, 50);
		UserBtn.setBackground(new Color(255, 255, 255));
		UserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(ClientInformation.Logined_id);
				dispose();
			}
		});

		// JLayeredPane과 JScrollPane을 이용한 좋아요를 누른 사용자 프로필 목록 표시 부분
		JLayeredPane layeredpanel = new JLayeredPane();
		layeredpanel.setPreferredSize(new Dimension(470, 700));
		panel.add(layeredpanel);

		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(470, 700));
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JScrollPane scrollpane = new JScrollPane(panel_3);
		scrollpane.setSize(470, 700);
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


		// 좋아요를 누른 사용자의 프로필을 UserPanel로 표시
		for (int i = 0; i < num_of_like; i++) {
			String id = likers.get(i).getUser_id();
			UserDto u = userDao.selectUser(id);
			UserPanel p1 = new UserPanel(u);
			panel_3.add(p1);
		}

		layeredpanel.add(scrollpane);
		setVisible(true);
	}
}
