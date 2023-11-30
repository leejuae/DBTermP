package GUI;

import Dto.comment.CommentDto;
import Dto.comment.CommentOnDto;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// 다양한 import 구문은 여기에 위치할 수 있습니다.

// ChildCommentPanel 클래스는 JPanel을 확장합니다.
public class ChildCommentPanel extends JPanel {
	JPanel panel; // 내부 패널을 정의합니다.
	private JTextField textField; // 텍스트 입력 필드
	private ImageAvatar imageAvatar; // 이미지 아바타를 위한 필드

	// 최대 크기를 설정하는 메서드
	public Dimension getMaximumSize() {
		Dimension d = getPreferredSize();
		d.width = Integer.MAX_VALUE;
		return d;
	}
	/**
	 * Create the panel.
	 */

	// 생성자
	public ChildCommentPanel(CommentOnDto comment) {
		// JPanel 속성 설정
		setLayout(null);
		setPreferredSize(new Dimension(450,60));
		setBounds(0,0,450,60);

		// 내부 패널 구성
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 450, 60);
		add(panel);
		panel.setLayout(null);
		
		// 이미지 ㅣ아바타 설정
		ImageIcon pImage = ImageManager.GetUserProfile(comment.getUser_id(), 41, 40);
		
		imageAvatar = initComponents(pImage);
		imageAvatar.setBounds(45, 10, 41, 40);
        imageAvatar.setBorderColor(new Color(255,255,255));
        panel.add(imageAvatar);
        /*
		JLabel profileIcon = new JLabel(pImage);
		profileIcon.setBounds(45, 10, 41, 40);
		panel.add(profileIcon);
		*/

		// 사용자 코멘트 표시를 위한 텍스트 필드
		textField = new JTextField(comment.getContent());
		// 다양한 속성 설정
		textField.setBackground(new Color(255, 255, 255));
		textField.setBounds(89, 20, 290, 35);
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);

		// 좋아요 패널 설정
		JPanel LikePanel = new JPanel();
		LikePanel.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout = (FlowLayout) LikePanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		LikePanel.setBounds(397, 20, 41, 20);
		panel.add(LikePanel);
		
//		// 데이터베이스 연결
//		Connection con = SQLMethods.GetCon();
//		int cnt = SQLMethods.ChildCommentLikers(con, comment.comment_id).size();
//
//
//		String q1 = "select * from comment_like where comment_id = \"" + comment.comment_id + "\" and  user_id = \"" + ClientInformation.Logined_id + "\";";
//		ResultSet rs = SQLMethods.ExecuteQuery(con, q1);
//
//		// 사용자에 따른 하트 이미지 설정
//		String imgURL ="";
//		try {
//			if(rs.next()) {
//				if(rs.getString(1).compareTo("") == 0)
//				{
//					imgURL = "src/assets/UI/emptyHeart.png";
//				}
//				else {
//					imgURL = "src/assets/UI/fullHeart.png";
//
//				}
//
//			}
//			else {
//				imgURL = "src/assets/UI/emptyHeart.png";
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// 좋아요 수를 표시하는 라벨과 이미지 아이콘 추가
//		ImageIcon likeImage = ImageManager.GetImageUsingFileSystem(imgURL, 20, 20);
//
//		JLabel likes = new JLabel(""+cnt);
//		LikePanel.add(likes);
//
//
//		JLabel heart = new JLabel(likeImage);
//		heart.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("like click");
//
//
//				String heartURL = "";
//
//				ImageIcon likeImage_1 = null;
//				int cnt1 = SQLMethods.ChildCommentLikers(SQLMethods.GetCon(), comment.comment_id).size();
//
//				System.out.println(comment.comment_id + "'s liekPanel Clicked");
//				int like= SQLMethods.ChildCommentLike(SQLMethods.GetCon(), ClientInformation.Logined_id , comment.comment_id);
//				System.out.println(like);
//				if(like==1) {
//					heartURL = "src/assets/UI/fullHeart.png";
//					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
//					cnt1=cnt1+1;
//				}
//				else if(like==0) {
//					heartURL = "src/assets/UI/emptyHeart.png";
//					likeImage_1 = ImageManager.GetImageUsingFileSystem(heartURL, 20, 20);
//					cnt1=cnt1-1;
//				}
//				heart.setIcon(likeImage_1);
//				likes.setText("" + cnt1);
//
//			}
//		});
//		heart.setPreferredSize(new Dimension(20,20));
//		LikePanel.add(heart);

		// 사용자 ID와 날짜를 표시하는 패널 설정
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(89, 0, 290, 20);
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		JLabel idLabel = new JLabel(comment.getUser_id());
		panel_1.add(idLabel);
		
		JLabel margin1 = new JLabel("•");
		margin1.setFont(new Font("굴림", Font.PLAIN, 8));
		margin1.setForeground(new Color(175, 175, 175));
		panel_1.add(margin1);
		
		JLabel dateLabel = new JLabel(comment.getDate().toString());
		panel_1.add(dateLabel);

		

	}


	// 이미지 아바타를 초기화하는 메소드
	private ImageAvatar initComponents(ImageIcon icon) {
		// ImageAvatar 객체 생성
		ImageAvatar imageAvatar1 = new ImageAvatar();

        //ImageIcon profileIcon = ImageManager.GetImageUsingFileSystem("src/assets/profile_image.png",50,50);

		// 이미지 조정 및 설정
        Image img = icon.getImage();
		Image updateImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		
		// 이미지 아바타에 이미지 설정
        imageAvatar1.setImage(updateIcon); // NOI18N
		// GroupLayout을 사용하여 이미지 아바타의 레이아웃을 설정하는 부분
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

       // pack();
        setBounds(0, 0, 478, 763);
        //setLocationRelativeTo(null);


        return imageAvatar1;
    }
}
