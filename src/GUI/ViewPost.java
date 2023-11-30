package GUI;

import Dao.CommentDao;
import Dao.LikeDao;
import Dao.PostDao;
import Dto.comment.CommentDto;
import Dto.comment.CommentOnDto;
import Dto.post.PostDto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class ViewPost extends JFrame {

	private JPanel contentPane;
	private ImageAvatar imageAvatar;
	public JTextField commentText;
	public String post_id;
	private JPanel appbar;
	public ViewPost(String p_id) {
		post_id = p_id;

		// 프레임 설정
		setBackground(new Color(255, 255, 255));
		setBounds(150, 150, 480, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// 상단, 중앙, 하단 패널 생성
		JPanel top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		contentPane.add(top, BorderLayout.NORTH);
		
		JPanel center = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) center.getLayout();
		center.setBackground(new Color(255, 255, 255));
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		contentPane.add(center, BorderLayout.CENTER);
		
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(255, 255, 255));
		contentPane.add(bottom, BorderLayout.SOUTH);

		// 상단 바 설정
		appbar = new JPanel();
		appbar.setBorder(new LineBorder(new Color(0, 0, 0)));
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setPreferredSize(new Dimension(464,65));
		appbar.setLayout(null);
		top.add(appbar);
	
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


		// 프로필 이미지 설정
		ImageIcon userImage = ImageManager.GetUserProfile(ClientInformation.Logined_id, 60, 60);
		
		imageAvatar = initComponents(userImage);
		imageAvatar.setBounds(7, 0, 60, 60);
        imageAvatar.setBorderColor(new Color(255,255,255));
        imageAvatar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Profile(ClientInformation.Logined_id);
			}
		});
        appbar.add(imageAvatar);
		
		//appbar.add(UserBtn);
		
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

		// 게시물 패널 생성
		PostPanel post = null;
		PostDao postDao = new PostDao();
		PostDto p = postDao.selectOnePost(p_id);
		post = new PostPanel(p);
		center.add(post);
//		if(p.images.size() == 0)
//		{
//			post = new PostPanel(p);
//			center.add(post);
//		}
//		else {
//			post = new PostPanel(p, p.images);
//			center.add(post);
//		}
		
		commentText = new JTextField();
		commentText.setBounds(12, 10, 367, 48);
		commentText.setColumns(10);

		// 댓글과 좋아요 수를 표시하는 패널 설정
		JPanel commentStatus = new JPanel();
		center.add(commentStatus);
		commentStatus.setBorder(new LineBorder(new Color(0, 0, 0)));
		commentStatus.setBackground(new Color(255, 255, 255));
		commentStatus.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		commentStatus.setPreferredSize(new Dimension(464,35));

		LikeDao likeDao = new LikeDao();
		int cnt = likeDao.selectNumPostLike(p_id);
		
		JLabel likeCnt = new JLabel(""+cnt);
		commentStatus.add(likeCnt);
		
		JButton likeBtn = new JButton("Likes");
		likeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new PostLike(p_id);
			}
		});
		likeBtn.setBackground(new Color(255, 255, 255));
		likeBtn.setToolTipText("");
		likeBtn.setBorderPainted(false);
		likeBtn.setFocusPainted(false);
		
		commentStatus.add(likeBtn);

		CommentDao commentDao = new CommentDao();
		List<CommentDto> list = commentDao.getAllComment(p_id);

		
		cnt = list.size();

		JLabel commentCnt = new JLabel(""+cnt);
		commentStatus.add(commentCnt);
			
		JLabel lblNewLabel = new JLabel("Comments");
		commentStatus.add(lblNewLabel);

		// 댓글 리스트 패널 설정
		JPanel comments = new JPanel();
		comments.setBackground(new Color(255, 255, 255));
		comments.setLayout(new BoxLayout(comments, BoxLayout.Y_AXIS));
		
		for(int i =0;i<list.size();i++) {
			CommentPanel c = new CommentPanel(list.get(i));
			c.setBackground(new Color(255, 255, 255));
			CommentDto temp = list.get(i);
			
			c.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					new ViewComment(temp.getComment_id());
				}
			});
			
			comments.add(c);
			ArrayList<CommentOnDto> cList = commentDao.selectAllcommentOn(list.get(i).getComment_id());
			
			for(int j =0;j< (cList.size() >= 2 ? 2 : cList.size()); j++) {
				ChildCommentPanel cC = new ChildCommentPanel(cList.get(j));
				cC.setBackground(new Color(255, 255, 255));
				comments.add(cC);				
			}
			
			
		}

		// 스크롤 가능한 댓글 리스트를 위한 JScrollPane 설정
		JScrollPane scrollPane = new JScrollPane(comments);
		scrollPane.setPreferredSize(new Dimension(464, 550));
		scrollPane.setBackground(new Color(255, 255, 255));
		
		center.add(scrollPane);
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		// 댓글 입력 창과 전송 버튼을 위한 패널 설정
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(464,60));
		panel.setBackground(new Color(255, 255, 255));
		bottom.add(panel);
		panel.setLayout(null);
		
		commentText.setBackground(new Color(255, 255, 255));
		panel.add(commentText);
		
		ImageIcon enter = ImageManager.GetImageUsingFileSystem("src/assets/UI/enter button.png",82,32);
		JButton enterBtn = new JButton(enter);
		enterBtn.setBounds(391, 10, 61, 48);
		enterBtn.setBorderPainted(false);
		enterBtn.setFocusPainted(false);
		enterBtn.setOpaque(false);
		enterBtn.setContentAreaFilled(false);

		// 댓글 입력 후 전송 버튼 액션 설정
		enterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = commentText.getText();
				commentDao.WriteComment(ClientInformation.Logined_id, p_id,text);

				// 댓글 추가 후 UI 갱신
				comments.setBackground(new Color(255, 255, 255));
				commentStatus.setBackground(new Color(255, 255, 255));
				comments.invalidate();
				commentStatus.invalidate();
				
				String q2 = "select max(comment_id) from comment;";
				ResultSet rs2 = SQLMethods.ExecuteQuery(SQLMethods.GetCon(), q2);
				String c_id= "";
				try {
					if(rs2.next())
					{
						c_id = rs2.getString(1);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				CommentDto c1 = commentDao.getComment(c_id);
				CommentPanel cP1 = new CommentPanel(c1);
				cP1.setBackground(new Color(255, 255, 255));
				comments.add(cP1);
				
				commentCnt.setText("" + (Integer.parseInt(commentCnt.getText())+ 1));
				
				comments.validate();
				commentStatus.validate();
			}
		});
		panel.add(enterBtn);

		// 프레임 높이 계산 및 설정
		int h = 800 - 20 - top.getPreferredSize().height - bottom.getPreferredSize().height - post.getPreferredSize().height - commentStatus.getPreferredSize().height;
		scrollPane.setPreferredSize(new Dimension(464,h));
		
		setVisible(true);
	}

	// 이미지 아바타를 초기화하고 생성하는 메서드
	private ImageAvatar initComponents(ImageIcon icon) {
		
		ImageAvatar imageAvatar1 = new ImageAvatar();
       

        //
        //ImageIcon profileIcon = ImageManager.GetImageUsingFileSystem("src/assets/profile_image.png",50,50);
		
        Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		
        imageAvatar1.setImage(updateIcon); // 이미지 설정
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
        //panel.setLayout(layout);

        pack();
        setBounds(0, 0, 480, 800);
        setLocationRelativeTo(null);
        return imageAvatar1;
    }
}
