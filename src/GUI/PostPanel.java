package GUI;

import Dao.LikeDao;
import Dao.PostDao;
import Dao.UserDao;
import Dto.post.PostDto;
import Dto.user.UserDto;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.GroupLayout.Alignment;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class PostPanel extends JPanel{
	// PostPanel 클래스 생성 - JPanel 상속

	UserDto user;
	JPanel panel;
	private ImageAvatar imageAvatar;
	// 필드 선언

	public Dimension getMaximumSize() {
		// 최대 크기 반환 메서드 재정의
		Dimension d = getPreferredSize();
		d.width = Integer.MAX_VALUE;
		return d;
	}
	
	public PostPanel(PostDto post) {
		// PostPanel 생성자 - post 인스턴스를 받아 초기화하는 생성자
		setPreferredSize(new Dimension(464,200));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBackground(new Color(255, 255, 255));		
		setLayout(null);

		// 여러 요소를 렌더링하고 있으며 각 요소의 상태에 따라 데이터를 로드하고 화면에 표시하는 역할을 하고 있는 것 같아요.
		// 이미지를 클릭하면 사용자의 프로필로 이동하고, 이미지 스크롤 영역이 있고, 좋아요 관련 기능도 있는 것으로 보입니다.
		// 하지만 정확한 기능과 동작을 확신하기 위해서는 코드의 맥락을 전체적으로 파악하는 것이 필요해 보입니다.

		UserDao userDao = new UserDao();
		UserDto user = new UserDto();
		String user_id = post.getUser_id();
		user = userDao.selectUser(user_id);

		panel = new JPanel();
		panel.setBounds(0, 0, 464, 200);
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		add(panel);

//		PostDao postDao = new PostDao();
//		String post_id = post.getPost_id();
//		user_id = postDao.selectOnePost(post_id);
//
//		String q1 = "select user_id from posts where post_id = \"" + post.post_id + "\";";
//		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
//		try {
//			if(rs.next()) {
//				user_id = rs.getString(1);
//			}
//		} catch (SQLException e3) {
//			// TODO Auto-generated catch block
//			e3.printStackTrace();
//		}
		
		ImageIcon userImage = ImageManager.GetUserProfile(user.getUser_id(), 50, 50);
		imageAvatar = initComponents(userImage);
		imageAvatar.setBounds(5, 0, 60, 60);
		imageAvatar.setBorderColor(new Color(255,255,255));
		imageAvatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(post.getUser_id());
			}
		});
		panel.add(imageAvatar);

		JPanel postInfo = new JPanel();
		postInfo.setBounds(61, 5, 282, 20);
		postInfo.setBackground(new Color(255, 255, 255));
		panel.add(postInfo);
		postInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel Name = new JLabel(user.getName());
		Name.setBackground(new Color(192, 192, 192));
		Name.setBounds(74, 10, 88, 15);
		postInfo.add(Name);
		
		JLabel margin1 = new JLabel("•");
		margin1.setFont(new Font("굴림", Font.PLAIN, 8));
		margin1.setForeground(new Color(175, 175, 175));
		postInfo.add(margin1);
		
		JLabel ID = new JLabel(user.getUser_id());
		ID.setForeground(new Color(175, 175, 175));
		ID.setBounds(154, 10, 74, 15);
		postInfo.add(ID);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		
		JLabel maring2 = new JLabel("•");
		maring2.setFont(new Font("굴림", Font.PLAIN, 8));
		maring2.setForeground(new Color(175, 175, 175));
		postInfo.add(maring2);
		
		JLabel date = new JLabel(post.getTimeStamp().toString());
		date.setBounds(240, 10, 104, 15);
		postInfo.add(date);
		
		JPanel liekPanel = new JPanel();
		liekPanel.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) liekPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		liekPanel.setBounds(355, 22, 84, 20);
		panel.add(liekPanel);

		LikeDao likeDao = new LikeDao();
		String post_id = post.getPost_id();
		int cnt = likeDao.selectNumPostLike(post_id);
		
		JLabel likeCnt = new JLabel("" + cnt);
		liekPanel.add(likeCnt);

		String rs = likeDao.selectOnePostLike(post_id, ClientInformation.Logined_id);
		
		
		String imgURL ="";
		if(rs != null) {
			if(rs.compareTo("0000") == 0)
				imgURL = "src/assets/UI/emptyHeart.png";
			else
				imgURL = "src/assets/UI/fullHeart.png";

		}
		else {
			imgURL = "src/assets/UI/emptyHeart.png";
		}
		
		ImageIcon likeImage = ImageManager.GetImageUsingFileSystem(imgURL, 20, 20);
		
		JLabel likeIcon = new JLabel(likeImage);
		likeIcon.setPreferredSize(new Dimension(20,20));
		liekPanel.add(likeIcon);
		
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 65, 428, 100);
		panel.add(scroll);
		
		JTextPane text = new JTextPane();
		text.setText(post.getContent());
		scroll.setViewportView(text);
		
		liekPanel.addMouseListener(new MouseAdapter() {
			
			ImageIcon likeImage_1=new ImageIcon();
			@Override
			public void mouseClicked(MouseEvent e) {
				int cnt = likeDao.selectNumPostLike(post_id);
				
				String heartURL = "";
				
				System.out.println(post.getPost_id() + "'s liekPanel Clicked");


				int like= likeDao.postLike(ClientInformation.Logined_id, post_id);
				System.out.println(like);
				if(like==1) {
					heartURL = "src/assets/UI/fullHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt=cnt+1;
				}
				else if(like==0) {
					heartURL = "src/assets/UI/emptyHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt=cnt-1;
				}
				likeIcon.setIcon(likeImage_1);
				likeCnt.setText("" + cnt);
				
			}
		});
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public PostPanel(PostDto post, List<String> imgs) {
		// PostPanel 생성자 - post와 이미지 목록을 받아 초기화하는 생성자
		setBounds(0, 0, 464, 300);
		setPreferredSize(new Dimension(464,300));
		setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setBackground(new Color(255, 255, 255));		
		setLayout(null);

		String user_id = post.getUser_id();
		UserDao userDao = new UserDao();
		user = userDao.selectUser(user_id);

		// PostPanel의 다른 형태로 보이며, 이미지 목록을 추가로 표시하는 기능이 있는 것으로 보입니다.
		// 이미지 목록을 패널에 추가하고, 이미지를 클릭하면 해당 이미지에 대한 프로필을 보여주는 것으로 보입니다.
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new LineBorder(Color.WHITE, 0), ""));
		panel.setBounds(0, 0, 464, 300);
		panel.setLayout(null);
		add(panel);
		
		ImageIcon userImage = ImageManager.GetUserProfile(user.getUser_id(), 50, 50);
		imageAvatar = initComponents(userImage);
		imageAvatar.setBounds(5, 0, 60, 60);
		imageAvatar.setBorderColor(new Color(255,255,255));
		imageAvatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Profile(post.getUser_id());
			}
		});
		panel.add(imageAvatar);

		JPanel postInfo = new JPanel();
		postInfo.setBounds(61, 5, 282, 20);
		postInfo.setBackground(new Color(255, 255, 255));
		panel.add(postInfo);
		postInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel Name = new JLabel(user.getName());
		Name.setBackground(new Color(192, 192, 192));
		Name.setBounds(74, 10, 88, 15);
		postInfo.add(Name);
		
		JLabel margin1 = new JLabel("•");
		margin1.setFont(new Font("굴림", Font.PLAIN, 8));
		margin1.setForeground(new Color(175, 175, 175));
		postInfo.add(margin1);
		
		JLabel ID = new JLabel(user.getUser_id());
		ID.setForeground(new Color(175, 175, 175));
		ID.setBounds(154, 10, 74, 15);
		postInfo.add(ID);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		
		JLabel maring2 = new JLabel("•");
		maring2.setFont(new Font("굴림", Font.PLAIN, 8));
		maring2.setForeground(new Color(175, 175, 175));
		postInfo.add(maring2);
		
		JLabel date = new JLabel(post.getTimeStamp().toString());
		date.setBounds(240, 10, 104, 15);
		postInfo.add(date);
		
		JPanel liekPanel = new JPanel();
		liekPanel.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) liekPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		liekPanel.setBounds(355, 22, 84, 20);
		panel.add(liekPanel);

		LikeDao likeDao = new LikeDao();
		int cnt = likeDao.selectNumPostLike(post.getPost_id());
		
		JLabel likeCnt = new JLabel("" + cnt);
		liekPanel.add(likeCnt);

		String post_id = likeDao.selectOnePostLike(post.getPost_id(), ClientInformation.Logined_id);
		String imgURL ="";
		if(post_id != null) {
			if(post_id.equals("0000"))
				imgURL = "src/assets/UI/emptyHeart.png";
			else
				imgURL = "src/assets/UI/fullHeart.png";

		}
		else {
			imgURL = "src/assets/UI/emptyHeart.png";
		}
		
		ImageIcon likeImage = ImageManager.GetImageUsingFileSystem(imgURL, 20, 20);
		
		JLabel likeIcon = new JLabel(likeImage);
		likeIcon.setPreferredSize(new Dimension(20,20));
		liekPanel.add(likeIcon);
		
		//----
		
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(12, 65, 428, 100);
		panel.add(scroll);
		
		JTextPane text = new JTextPane();
		text.setText(post.getContent());
		scroll.setViewportView(text);
		
		JScrollPane imageScroll = new JScrollPane();
		imageScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		imageScroll.setBounds(12, 175, 428, 115);
		panel.add(imageScroll);

		JPanel images = new JPanel();
		images.setBackground(new Color(255, 255, 255));
		images.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		imageScroll.add(images);
		imageScroll.setViewportView(images);

		
		for(int i =0;i<imgs.size(); i++) {
			ImageIcon img = ImageManager.GetImageUsingURL(imgs.get(i), 100,100);
			JLabel imgLabel = new JLabel(img);
			imgLabel.setSize(100,100);
			images.add(imgLabel);
			
		}	
		
		liekPanel.addMouseListener(new MouseAdapter() {
			
			ImageIcon likeImage_1=new ImageIcon();
			@Override
			public void mouseClicked(MouseEvent e) {
				int cnt = likeDao.selectNumPostLike(post_id);
				String heartURL = "";
				System.out.println(post.getPost_id() + "'s liekPanel Clicked");

				int like= likeDao.postLike(ClientInformation.Logined_id, post_id);
				System.out.println(like);
				if(like==1) {
					heartURL = "src/assets/UI/fullHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt=cnt+1;
				}
				else if(like==0) {
					heartURL = "src/assets/UI/emptyHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt=cnt-1;
				}
				likeIcon.setIcon(likeImage_1);
				likeCnt.setText("" + cnt);
				
			}
		});
	}
private ImageAvatar initComponents(ImageIcon icon) {
		// 컴포넌트를 초기화하는 메서드
		ImageAvatar imageAvatar1 = new ImageAvatar();

		// 이미지를 스케일링하고, 이미지 아바타를 생성하는 메서드로 보입니다.
		// 이미지가 사용자 프로필 이미지인 것 같아요.

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

        //pack();
        setBounds(0, 0, 478, 763);
        //setLocationRelativeTo(null);
        return imageAvatar1;
    }
}