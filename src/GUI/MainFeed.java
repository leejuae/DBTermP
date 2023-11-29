//package GUI;
//
//import javax.swing.*;
//import javax.swing.GroupLayout.Alignment;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//public class MainFeed extends JFrame {
//
//	private JPanel contentPane;
//	private JPanel appbar;
//	private ImageAvatar imageAvatar;
//	public MainFeed() {
//		setBackground(new Color(255, 255, 255));
//		setBounds(100, 100, 480, 800);
//		setTitle("Twitter");
//
//		contentPane = new JPanel();
//		contentPane.setBackground(new Color(255, 255, 255));
//
//		setContentPane(contentPane);
//		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
//
//		appbar = new JPanel();
//		appbar.setBackground(new Color(255, 255, 255));
//		appbar.setPreferredSize(new Dimension(464,65));
//		appbar.setLayout(null);
//		contentPane.add(appbar);
//
//		ImageIcon logo = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 50,50);
//
//		JLabel Logo = new JLabel(logo);
//		Logo.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				new MainFeed();
//				dispose();
//			}
//		});
//		Logo.setBounds(12, 5, 50, 50);
//		Logo.setBackground(new Color(255, 255,255));
//		Logo.setBounds(200, 5, 50, 50);
//
//
//		appbar.add(Logo);
//
//		String q1 = "select profile_Image_dir from user where user_id = \"" + ClientInformation.Logined_id + "\";";
//		ResultSet rs = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q1);
//
//		String imgUrl = "";
//		try {
//			if(rs.next()) {
//				imgUrl = rs.getString(1);
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//
//		ImageIcon userImage = ImageManager.GetUserProfile(ClientInformation.Logined_id, 50, 50);
//
//		imageAvatar = initComponents(userImage);
//		imageAvatar.setBounds(7, 0, 60, 60);
//        imageAvatar.setBorderColor(new Color(255,255,255));
//        imageAvatar.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				new Profile(ClientInformation.Logined_id);
//				dispose();
//			}
//		});
//        appbar.add(imageAvatar);
//        /*
//		JLabel UserBtn = new JLabel(userImage);
//		UserBtn.setBounds(12, 5, 50, 50);
//		UserBtn.setBackground(new Color(255, 255,255));
//		UserBtn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				new Profile(ClientInformation.Logined_id);
//				dispose();
//			}
//		});
//        */
//
//		//appbar.add(UserBtn);
//
//		ImageIcon searchIcon = ImageManager.GetImageUsingFileSystem("src/assets/UI/search_2.png",30,30);
//		JLabel SearchBtn = new JLabel(searchIcon);
//		SearchBtn.setBounds(402, 5, 50, 50);
//		SearchBtn.setBackground(new Color(255, 255,255));
//		SearchBtn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				new UserSearch();
//				dispose();
//			}
//		});
//
//
//
//		appbar.add(SearchBtn);
//
//
//
//		//-------------
//
//		JLayeredPane layeredPane = new JLayeredPane();
//		layeredPane.setPreferredSize(new Dimension(464, 695));
//		contentPane.add(layeredPane);
//
//		JPanel posts = new JPanel();
//		posts.setBackground(new Color(255, 255, 255));
//		posts.setLayout(new BoxLayout(posts, BoxLayout.Y_AXIS));
//		//posts.setPreferredSize(new Dimension(464, 695));
//
//
//		//�����ʿ�(�α��� �� �ȷο� �κ� Ȱ��ȭ �Ǹ� �ȷο��� ������ �Խñ� �����ֱ�)
//		List<String> list = SQLMethods.Followings(SQLMethods.GetCon(), ClientInformation.Logined_id);
//		List<Post> postList = null;
//
//		postList = SQLMethods.GetPosts(list);
//
//		if(postList != null) {
//			for(int i =0;i<postList.size();i++) {
//				Post post = postList.get(i);
//				PostPanel p1 = null;
//				if(post.images.size() == 0)
//					p1 = new PostPanel(post);
//				else
//					p1 = new PostPanel(post,post.images);
//				p1.addMouseListener(new MouseAdapter() {
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						new ViewPost(post.post_id);
//					}
//				});
//
//				posts.add(p1);
//			}
//
//		}
//
//
//
//
//		JScrollPane scrollPane = new JScrollPane(posts);
//		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setSize(464, 695);
//		layeredPane.add(scrollPane);
//
//
//
//		JPanel btnPanel = new JPanel();
//		layeredPane.setLayer(btnPanel, 1);
//		btnPanel.setLocation(395, 635);
//		btnPanel.setBackground(new Color(255, 0,0,0));
//		btnPanel.setSize(50, 50);
//		btnPanel.setLayout(null);
//
//		//�۾��� ��ư
//		ImageIcon plusImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/plus.png", 50, 50);
//		JLabel writeBtn = new JLabel(plusImage);
//		writeBtn.setBounds(0, 0, 50, 50);
//		writeBtn.setBackground(new Color(255, 255,255));
//
//		writeBtn.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("posint click");
//				new posting(ClientInformation.Logined_id);
//				dispose();
//			}
//		});
//
//
//		btnPanel.add(writeBtn);
//		//btnPanel.add(WriteBtn);
//		layeredPane.add(btnPanel);
//
//
//
//		setVisible(true);
//	}
//
//private ImageAvatar initComponents(ImageIcon icon) {
//
//		ImageAvatar imageAvatar1 = new ImageAvatar();
//
//
//        //
//        //ImageIcon profileIcon = ImageManager.GetImageUsingFileSystem("src/assets/profile_image.png",50,50);
//
//        Image img = icon.getImage();
//		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
//		ImageIcon updateIcon = new ImageIcon(updateImg);
//
//
//        imageAvatar1.setImage(updateIcon); // NOI18N
//        GroupLayout layout = new GroupLayout(appbar);
//        layout.setHorizontalGroup(
//        	layout.createParallelGroup(Alignment.LEADING)
//        		.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
//        			.addComponent(imageAvatar1, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
//        			.addContainerGap(812, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//        	layout.createParallelGroup(Alignment.LEADING)
//        		.addGroup(layout.createSequentialGroup()
//        			.addGap(142)
//        			.addComponent(imageAvatar1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
//        			.addContainerGap(514, Short.MAX_VALUE))
//        );
//        //panel.setLayout(layout);
//
//        pack();
//        setBounds(0, 0, 480, 800);
//        setLocationRelativeTo(null);
//        return imageAvatar1;
//    }
//}
