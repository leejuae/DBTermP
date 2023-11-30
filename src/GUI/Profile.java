package GUI;

import Dao.FollowDao;
import Dao.PostDao;
import Dao.UserDao;
import Dto.follow.FollowDto;
import Dto.post.PostDto;
import Dto.user.UserDto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
public class Profile extends JFrame {

	private JPanel panel;
	private Image backImg;
	private ImageAvatar imageAvatar;
	private ImageAvatar imageAvatar2;
	String nickname = null;

	// 프로필 생성자
	public Profile(String id){
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();

		userDto = userDao.selectUser(id);
		if(userDto != null){
			nickname = userDto.getName();
		}

		// 기본 프레임 설정
		getContentPane().setLayout(null);
		getContentPane().setBounds(0,0,466,763);
		getContentPane().setVisible(true);
		setBounds(100, 100, 608, 630);
		setTitle("Profile");

		// 패널 설정
		panel = new JPanel();
		panel.setBounds(0, 0, 466, 763);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		panel.setLayout(null);

		// 앱바 설정
		JPanel appbar = new JPanel();
		appbar.setBounds(1, 5, 464, 65);
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setPreferredSize(new Dimension(464,65));
		appbar.setLayout(null);
		panel.add(appbar);


		// 로고 설정
		ImageIcon logo = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 50,50);
		JLabel Logo = new JLabel(logo);
		Logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new MainFeed();
			}
		});
		Logo.setBounds(12, 5, 50, 50);
		Logo.setBackground(new Color(255, 255,255));
		Logo.setBounds(200, 5, 50, 50);

		
		appbar.add(Logo);
		
		String myProfileImage = null;
		
		//현재 로그인 되어있는 유저의 프로필 사진 스트링 얻어오기

		//현재 로그인 되어있는 유저의 프로필 사진

		// 유저 프로필 이미지 설정
		ImageIcon myImage = ImageManager.GetUserProfile(ClientInformation.Logined_id, 50, 50);

		
		imageAvatar2 = initComponents(myImage);
		imageAvatar2.setBounds(7, 0, 60, 60);
        imageAvatar2.setBorderColor(new Color(255,255,255));
        imageAvatar2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Profile(ClientInformation.Logined_id);
			}
		});
        appbar.add(imageAvatar2);

		// 설정 아이콘 추가
		if(id.equals(ClientInformation.Logined_id)) {
			ImageIcon settingIcon = ImageManager.GetImageUsingFileSystem("src/assets/UI/setting2.png",30,30);
			JLabel settingBtn = new JLabel(settingIcon);
			settingBtn.setBounds(402, 5, 50, 50);
			//SearchBtn.setBackground(new Color(255, 255,255));
			settingBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new Setting(ClientInformation.Logined_id);
				}
			});
			appbar.add(settingBtn);
		}

		//배경 구현 안함!!
		String userBackgroundImage = null;

		if(userDto.getBackground_pic()!=null) {
			userBackgroundImage = userDto.getBackground_pic();
		}
		// 프로필 배경 이미지 및 유저 이미지 설정
		ImageIcon backIcon = ImageManager.GetUserBackground(id,464,200);
		ImageIcon profileIcon = ImageManager.GetUserProfile(id,100,200);

		
		//포스트 레이어부분
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1, 287, 465, 476);
		panel.add(panel_2);
		
		//프로필사진
		
		imageAvatar = initComponents(profileIcon);
		imageAvatar.setBounds(10, 120, 100, 100);
        imageAvatar.setBorderColor(new Color(255,255,255));
        panel.add(imageAvatar);
		
		//배경 이미지
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 464, 183);
		panel.add(panel_1);
		
		JLabel backImg_1 = new JLabel(backIcon);
		backImg_1.setHorizontalAlignment(SwingConstants.CENTER);
		backImg_1.setBounds(1, 62, 464, 119);
		panel_1.add(backImg_1);
		
		//프로필 옆 배경
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(12, 226, 466, 50);
		panel_3.setBackground(Color.WHITE);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(nickname);
		lblNewLabel.setFont(new Font("Thoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(0, -7, 299, 29);
		panel_3.add(lblNewLabel);
		
		String fId = "@" + id;
		JLabel lblNewLabel_1 = new JLabel(fId);
		lblNewLabel_1.setForeground(new Color(192, 192, 192));
		lblNewLabel_1.setFont(new Font("Thoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(2, 18, 100, 16);
		panel_3.add(lblNewLabel_1);

		// 사용자 소개 정보
		String introduce = null;

		if(userDto != null) {
			introduce = userDto.getIntroduction();
		}
		else {
			introduce = " ";
		}
		
		JLabel lblNewLabel_2 = new JLabel(introduce);
		lblNewLabel_2.setFont(new Font("Thoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(0, 36, 52, 15);
		panel_3.add(lblNewLabel_2);

//		List<String> follower = SQLMethods.Followers(SQLMethods.GetCon(), id);
		FollowDao followDao = new FollowDao();
		FollowDto followDto = new FollowDto();

		ArrayList<String> follower = followDao.selectfollowerList(id);
		int num_of_follow = follower.size();
		List<String> followings = followDao.selectfollowingList(id);
		int num_of_following = followings.size();
		
		String numOfFollower = Integer.toString(num_of_follow)+ " Follower";
		JTextArea txtFollower = new JTextArea(numOfFollower);
		txtFollower.setFont(new Font("Thoma", Font.PLAIN, 13));
		//txtFollow.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
		txtFollower.setEditable(false);
		txtFollower.setBounds(290, 28, 70, 100);
		txtFollower.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Follower(id);
			}
		});
		panel_3.add(txtFollower);
		
		String numOfFollowing = Integer.toString(num_of_following)+ " Following";
		JTextArea txtFollowing = new JTextArea(numOfFollowing);
		txtFollowing.setFont(new Font("Thoma", Font.PLAIN, 13));
		txtFollowing.setEditable(false);
		txtFollowing.setBounds(360, 28, 100, 100);
		txtFollowing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Following(id);
			}
		});
		panel_3.add(txtFollowing);

		
		//팔로우 버튼
		ImageIcon followImg = ImageManager.GetImageUsingFileSystem("src/assets/UI/follow_en.png",116,32);
		ImageIcon followingImg = ImageManager.GetImageUsingFileSystem("src/assets/UI/following.png",116,32);
		
		JLabel followButton = new JLabel(followImg);
		JLabel followingButton = new JLabel(followingImg);
		
		followButton.setBounds(335, 193, 116, 32);
		//followButton.setBackground(new Color(255, 255,255));
		followingButton.setBounds(335, 193, 116, 32);
		//followingButton.setBackground(new Color(255, 255,255));
		
		panel.add(followButton);
		panel.add(followingButton);

		// 현재 로그인 사용자와 주어진 id가 일치하는지 확인
		// 아이디가 현재 로그인 사용자와 같으면 follow, folling버튼 보이지 않게 함
		if(id.equals(ClientInformation.Logined_id)) {
			followButton.setVisible(false);
			followingButton.setVisible(false);
		}
		// 아이디가 현재 로그인한 사용자와 다르면 DB쿼리 실행으로 해당 사용자 팔로우하는지 여부 확인
		else {
			Statement stmt = null;
			ResultSet rs = null;

			String follower_id = followDao.checkIfFollowing(id, ClientInformation.Logined_id);
			if(follower_id==null) {
				followingButton.setVisible(true);
				followButton.setVisible(false);
			}
			else {
				followingButton.setVisible(false);
				followButton.setVisible(true);
			}
	
			followButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("클릭됨!");
					followDao.follow(ClientInformation.Logined_id, id);
					followingButton.setVisible(true);
					followButton.setVisible(false);
				}
			});
			followingButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("언팔 클릭됨!");
					followDao.unFollow(ClientInformation.Logined_id, id);
					followingButton.setVisible(false);
					followButton.setVisible(true);
				}
			});
		}
		panel_2.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(1, 0, 464, 695);
		layeredPane.setPreferredSize(new Dimension(464, 695));
		panel_2.add(layeredPane);
		
		JPanel posts = new JPanel();
		posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
		
//		//이미지 있는 경우
//		PostDao postDao = new PostDao();
//		ArrayList<PostDto> pList = postDao.selectAllSpecificUserPost(id);
//
//		for(int i =0;i<pList.size();i++) {
//			Post pTemp = pList.get(i);
//
//			PostPanel p1 = null;
//			if(pTemp.images.size() == 0)
//			{
//				p1 = new PostPanel(pTemp);
//			}
//			else
//			{
//				p1 = new PostPanel(pTemp, pTemp.images);
//			}
//
//			//게시글 누르면 포스트로 넘어가기
//			//메인피드 135
//			p1.addMouseListener(new MouseAdapter() {
//				@Override
//				public void mouseClicked(MouseEvent e) {
//					new ViewPost(pTemp.post_id);
//				}
//			});
//			posts.add(p1);
//		}

		JScrollPane scrollPane = new JScrollPane(posts);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(464, 450);
		layeredPane.add(scrollPane);
		
		JPanel btnPanel = new JPanel();
		layeredPane.setLayer(btnPanel, 1);
		btnPanel.setLocation(395, 635);
		btnPanel.setBackground(new Color(255, 0,0,0));
		btnPanel.setSize(50, 50);
		btnPanel.setLayout(null);
		
		ImageIcon plusImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/plus.png", 50, 50);
		JLabel writeBtn = new JLabel(plusImage);
		writeBtn.setBounds(0, 0, 50, 50);
		writeBtn.setBackground(new Color(255, 255,255));

		writeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		btnPanel.add(writeBtn);
		//btnPanel.add(WriteBtn);
		layeredPane.add(btnPanel);	
		
		panel_3.setVisible(true);
		panel.setVisible(true);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private ImageAvatar initComponents(ImageIcon icon) {
		
		ImageAvatar imageAvatar1 = new ImageAvatar();


        //
        //ImageIcon profileIcon = ImageManager.GetImageUsingFileSystem("src/assets/profile_image.png",50,50);

        Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		
        imageAvatar1.setImage(updateIcon); // NOI18N
        GroupLayout layout = new GroupLayout(panel);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        			.addComponent(imageAvatar1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(812, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(142)
        			.addComponent(imageAvatar1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(514, Short.MAX_VALUE))
        );
        //panel.setLayout(layout);

        pack();
        setBounds(0, 0, 478, 763);
        setLocationRelativeTo(null);
        return imageAvatar1;
    }
}
