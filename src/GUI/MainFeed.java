package GUI;

import Dao.FollowDao;
import Dao.PostDao;
import Dao.UserDao;
import Dto.post.PostDto;
import Dto.user.UserDto;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLayeredPane;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// JFrame을 확장한 MainFeed 클래스
public class MainFeed extends JFrame {

	// 클래스 변수
	private JPanel contentPane;
	private JPanel appbar;
	private ImageAvatar imageAvatar;
	public MainFeed() {

		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 480, 800);
		setTitle("Twitter");

		// 컨텐츠 패널 초기화
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// 앱바 초기화
		appbar = new JPanel();
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setPreferredSize(new Dimension(464,65));
		appbar.setLayout(null);
		contentPane.add(appbar);

		// 로고를 마우스 클릭으로 처리
		ImageIcon logo = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 50,50);
		JLabel Logo = new JLabel(logo);
		Logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFeed();
				dispose();
			}
		});
		Logo.setBounds(12, 5, 50, 50);
		Logo.setBackground(new Color(255, 255,255));
		Logo.setBounds(200, 5, 50, 50);
		appbar.add(Logo);

		// 데이터베이스에서 사용자 프로필 이미지 검색
		UserDao userDao = new UserDao();
		UserDto userDto = userDao.selectUser(ClientInformation.Logined_id);
//		String q1 = "select profile_Image_dir from user where user_id = \"" + ClientInformation.Logined_id + "\";";
//		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);

		String imgUrl = "";
		if(userDto != null) {
			imgUrl = userDto.getProfile_pic();
		}

		// 사용자 프로필 이미지 생성 및 처리
		ImageIcon userImage = ImageManager.GetUserProfile(ClientInformation.Logined_id, 50, 50);

		imageAvatar = initComponents(userImage);
		imageAvatar.setBounds(7, 0, 60, 60);
        imageAvatar.setBorderColor(new Color(255,255,255));
        imageAvatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(ClientInformation.Logined_id);
				dispose();
			}
		});
        appbar.add(imageAvatar);

		// 검색 버튼 마우스 클릭 처리
		ImageIcon searchIcon = ImageManager.GetImageUsingFileSystem("src/assets/UI/search_2.png",30,30);
		JLabel SearchBtn = new JLabel(searchIcon);
		SearchBtn.setBounds(402, 5, 50, 50);
		SearchBtn.setBackground(new Color(255, 255,255));
		SearchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new UserSearch();
				dispose();
			}
		});

		appbar.add(SearchBtn);

		// 레이어드 패널 생성
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(464, 695));
		contentPane.add(layeredPane);

		// 게시물 표시를 위한 패널 생성
		JPanel posts = new JPanel();
		posts.setBackground(new Color(255, 255, 255));
		posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
		//posts.setPreferredSize(new Dimension(464, 695));

		// 게시물 가져오기
		FollowDao followDao = new FollowDao();
		ArrayList<String> list = followDao.selectfollowingList(ClientInformation.Logined_id); //아이디정보
		ArrayList<PostDto> postList = new ArrayList<>();

		PostDao postDao = new PostDao();
		for (int i = 0; i < list.size(); i++) {
			// 팔로잉 하는 사람들의 아이디를 가져옴=>list
			// 지금 해야할 일, 해당 아이디를 이용해 그 사람들의 글을 다 가져와야 함.
			

			postList.addAll(postDao.selectAllSpecificUserPost(list.get(i)));
			
		}

		// 게시물을 패널에 추가하고 마우스 클릭으로 게시물 세부 정보 보기 처리
		if(postList != null) {
			for(int i = 0; i < postList.size(); i++) {
				PostDto post = postList.get(i);
				PostPanel p1 = null;
				p1 = new PostPanel(post);
				p1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						new ViewPost(post.getPost_id());
					}
				});
				posts.add(p1);
			}
		}


		// 게시물 패널을 스크롤 패널에 추가
		JScrollPane scrollPane = new JScrollPane(posts);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(464, 695);
		layeredPane.add(scrollPane);

		// 버튼을 위한 패널 생성
		JPanel btnPanel = new JPanel();
		layeredPane.setLayer(btnPanel, 1);
		btnPanel.setLocation(395, 635);
		btnPanel.setBackground(new Color(255, 0,0,0));
		btnPanel.setSize(50, 50);
		btnPanel.setLayout(null);

		// 새 게시물 작성 버튼 클릭 처리
		ImageIcon plusImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/plus.png", 50, 50);
		JLabel writeBtn = new JLabel(plusImage);
		writeBtn.setBounds(0, 0, 50, 50);
		writeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("게시물 클릭");
				new posting(ClientInformation.Logined_id);
				dispose();
			}
		});
		btnPanel.add(writeBtn);
		layeredPane.add(btnPanel);

		// 프레임을 표시합니다
		setVisible(true);
	}

	// ImageAvatar 구성 요소 초기화 메서드
	private ImageAvatar initComponents(ImageIcon icon) {

		// 주어진 아이콘으로 ImageAvatar 초기화
		ImageAvatar imageAvatar1 = new ImageAvatar();
		Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		imageAvatar1.setImage(updateIcon);
		// 구성 요소를 위한 레이아웃 및 경계 설정

		// (참고: 이 메서드 내의 레이아웃 설정은 의도한 구성 요소에 영향을 미치지 않을 수 있습니다)
        imageAvatar1.setImage(updateIcon); // NOI18N
        GroupLayout layout = new GroupLayout(appbar);
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
        pack();
        setBounds(0, 0, 480, 800);
        setLocationRelativeTo(null);
        return imageAvatar1;
    }
}

