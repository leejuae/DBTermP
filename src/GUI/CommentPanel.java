package GUI;

import Dao.CommentDao;
import Dao.LikeDao;
import Dao.UserDao;
import Dto.comment.CommentDto;
import Dto.like.CommentLikeDto;
import Dto.user.UserDto;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// 다양한 import 구문은 여기에 위치할 수 있습니다.

// CommentPanel 클래스는 JPanel을 상속하여 댓글을 표시하는 패널을 나타냅니다.
public class CommentPanel extends JPanel {
	// 생성자
	public Dimension getMaximumSize() {
		Dimension d = getPreferredSize();
		d.width = Integer.MAX_VALUE;
		
		return d;
	}
	/**
	 * Create the panel.
	 */
	public CommentPanel(CommentDto comment) {
		// JPanel 속성 설정
		setLayout(null);
		setBackground(new Color(255,255,255));
		setPreferredSize(new Dimension(450,100));
		setBounds(0, 0, 450, 100);

		// 사용자 프로필 이미지 설정
		UserDao userDao = new UserDao();
		UserDto userDto = new UserDto();
		String user_id = comment.getUser_id();
		userDto = userDao.selectUser(user_id);

		String imgUrl = "";
		imgUrl = userDto.getProfile_pic();

		// 프로필 이미지 생성 및 설정
		ImageIcon pImage = ImageManager.GetUserProfile(imgUrl, 50, 50);
		JLabel profileIcon = new JLabel(pImage);
		profileIcon.setBounds(5, 5, 50, 50);
		profileIcon.setBackground(new Color(128, 255, 128));
		profileIcon.setPreferredSize(new Dimension(50,50));
		add(profileIcon);

		// 댓글 정보 패널 생성
		JPanel commentInfo = new JPanel();
		commentInfo.setBackground(new Color(255, 255, 255));
		commentInfo.setBounds(60, 5, 300, 20);
		add(commentInfo);
		commentInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel userID = new JLabel(comment.getUser_id());
		commentInfo.add(userID);
		
		JLabel margin1 = new JLabel("•");
		margin1.setFont(new Font("굴림", Font.PLAIN, 8));
		margin1.setForeground(new Color(175, 175, 175));
		commentInfo.add(margin1);
		
		JLabel date = new JLabel(comment.getCommented_at().toString());
		commentInfo.add(date);

		// 댓글 내용 스크롤 가능한 패널 설정
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 26, 371, 64);
		add(scrollPane);

		// 댓글 내용 JTextPane 설정
		JTextPane commentContent = new JTextPane();
		commentContent.setText(comment.getContent());
		commentContent.setEditable(false);
		scrollPane.setViewportView(commentContent);
		
		// 댓글 좋아요와 좋아요 수를 표시하는 패널 생성
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setBounds(360, 5, 71, 23);
		add(panel);
		
		// 데이터베이스 조회를 통해 댓그 좋아요 수 확인
		LikeDao likeDao = new LikeDao();
		CommentLikeDto commentLikeDto = new CommentLikeDto();
		int cnt = likeDao.selectNumCommentkLike(comment.getComment_id());
		

		// 사용자가 해당 댓글에 좋아요를 눌렀는지 확인하여 하트 이미지 설정
		
		
		String imgURL ="";
		if(likeDao.selectOneCommentLike(comment.getComment_id(), ClientInformation.Logined_id) != null) {
			imgURL = "src/assets/UI/fullHeart.png";
		}
		else {
			imgURL = "src/assets/UI/emptyHeart.png";
		}


		ImageIcon likeImage = ImageManager.GetImageUsingFileSystem(imgURL, 20, 20);

		// 좋아요 수와 하트 이미지를 라벨에 추가
		JLabel likes = new JLabel(""+cnt);
		panel.add(likes);
		
		JLabel heart = new JLabel(likeImage);

		// 하트 이미지 클릭 이벤트 처리
		heart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("click like");
							
				String heartURL = "";
				
				ImageIcon likeImage_1 = null;

				int cnt1 = likeDao.selectCommentLike(comment.getComment_id()).size();
				
				System.out.println(comment.getComment_id() + "'s liekPanel Clicked");

				int like= LikeDao.CommentLike(SQLMethods.GetCon(), ClientInformation.Logined_id , comment.getComment_id());
				System.out.println(like);
				if(like==1) {
					heartURL = "src/assets/UI/fullHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt1=cnt1+1;
				}
				else if(like==0) {
					heartURL = "src/assets/UI/emptyHeart.png";
					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
					cnt1=cnt1-1;
				}
				// 좋아요 이벤트 처리 후 하트 이미지 및 좋아요 수 갱신
				heart.setIcon(likeImage_1);
				likes.setText("" + cnt1);
			}
		});
		heart.setPreferredSize(new Dimension(20,20));
		panel.add(heart);

	}
}
