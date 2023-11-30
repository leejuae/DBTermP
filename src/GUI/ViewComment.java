package GUI;

import Dao.CommentDao;
import Dto.comment.CommentDto;
import Dto.comment.CommentOnDto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ViewComment extends JFrame {

	private JPanel contentPane;
	public JTextField commentText;
	public String comment_id;

	// 댓글을 보여주는 창을 생성하는 생성자
	public ViewComment(String c_id) {
		comment_id = c_id;

		// 프레임 설정
		setBackground(new Color(255, 255, 255));
		setBounds(150, 150, 480, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		// 상단 부분의 패널 설정
		JPanel top = new JPanel();
		FlowLayout flowLayout = (FlowLayout) top.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		contentPane.add(top, BorderLayout.NORTH);

		// 중앙 부분의 패널 설정
		JPanel center = new JPanel();
		center.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout_1 = (FlowLayout) center.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		contentPane.add(center, BorderLayout.CENTER);

		// 하단 부분의 패널 설정
		JPanel bottom = new JPanel();
		contentPane.add(bottom, BorderLayout.SOUTH);

		// 상단 바 설정
		JPanel appbar = new JPanel();
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
			}
		});
		Logo.setBounds(12, 5, 50, 50);
		Logo.setBackground(new Color(255, 255,255));
		Logo.setBounds(200, 5, 50, 50);

		
		appbar.add(Logo);
		
		
		ImageIcon userImage = ImageManager.GetUserProfile(ClientInformation.Logined_id, 50, 50);
		JLabel UserBtn = new JLabel(userImage);
		UserBtn.setBounds(12, 5, 50, 50);
		UserBtn.setBackground(new Color(255, 255,255));
		UserBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		
		appbar.add(UserBtn);
		
		ImageIcon searchIcon = ImageManager.GetImageUsingFileSystem("src/assets/UI/search.png",30,30);
		JLabel SearchBtn = new JLabel(searchIcon);
		SearchBtn.setBounds(402, 5, 50, 50);
		SearchBtn.setBackground(new Color(255, 255,255));
		SearchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		appbar.add(SearchBtn);
		
		commentText = new JTextField();
		commentText.setBounds(12, 10, 367, 48);
		commentText.setColumns(10);
		
		
		CommentDto p = new CommentDto(c_id, new Timestamp(System.currentTimeMillis()), "0000", "0000", "0000", "0000");
		CommentPanel comment = new CommentPanel(p);
		center.add(comment);


		CommentDao commentDao = new CommentDao();
//		List<CommentOnDto> list = commentDao.selectAllcommentOn(c_id);

		// 댓글 내용을 담을 패널 설정
		JPanel comments = new JPanel();
		comments.setBackground(new Color(255, 255, 255));
		comments.setLayout(new BoxLayout(comments, BoxLayout.Y_AXIS));
		
//		for(int i =0;i<list.size();i++) {
//			ChildCommentPanel c = new ChildCommentPanel(list.get(i));
//			comments.add(c);
//		}

		// 스크롤 가능한 패널로 설정
		JScrollPane scrollPane = new JScrollPane(comments);
		scrollPane.setPreferredSize(new Dimension(464, 550));
		
		
		center.add(scrollPane);

		// 댓글 입력창과 전송 버튼 설정
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
	
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setPreferredSize(new Dimension(464,60));
		panel.setBackground(new Color(255, 255, 255));
		bottom.add(panel);
		panel.setLayout(null);
		
		
		panel.add(commentText);
		
		
		ImageIcon enter = ImageManager.GetImageUsingFileSystem("src/assets/UI/enter button.png",82,32);
		JButton enterBtn = new JButton(enter);
		enterBtn.setBounds(391, 10, 61, 48);
		enterBtn.setBorderPainted(false);
		enterBtn.setFocusPainted(false);
		enterBtn.setOpaque(false);
		enterBtn.setContentAreaFilled(false);

		// 전송 버튼의 액션 설정
		enterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = commentText.getText();

				byte[] array = new byte[7]; // length is bounded by 7
				new Random().nextBytes(array);
				String generatedString = new String(array, Charset.forName("UTF-8"));

				commentDao.WriteChildComment(generatedString, ClientInformation.Logined_id, c_id,text);

				comments.invalidate();

				
				String q2 = "select max(comment_id) from comments_on;";
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

				CommentOnDto c1 = commentDao.getCommentOn(c_id);
				ChildCommentPanel cP1 = new ChildCommentPanel(c1);
				comments.add(cP1);
				
				comments.validate();
			}
		});
		panel.add(enterBtn);

		// 전체 레이아웃 설정
		int h = 800 - 20 - top.getPreferredSize().height - bottom.getPreferredSize().height - comment.getPreferredSize().height;
		scrollPane.setPreferredSize(new Dimension(464,h));
		
		setVisible(true);
	}

}
