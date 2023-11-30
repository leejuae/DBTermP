package GUI;

import Dao.PostDao;
import Dao.UserDao;
import Dto.post.PostDto;
import Dto.user.UserDto;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

public class posting extends JFrame {
	// 필드 선언
	private JPanel contentPane;
	private JTextField imurl;
	private JTextField hashTextField;

	public posting(String user_id) {
		// JFrame 설정 및 컨텐트 패널 초기화
		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 480, 500);
		setTitle("Twitter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JPanel appbar = new JPanel();
		appbar.setBackground(new Color(255, 255, 255));
		appbar.setBounds(0,0,464,41);
		appbar.setLayout(null);
		contentPane.add(appbar);

		// 텍스트 입력을 위한 JTextArea 및 JScrollPane 설정
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(247, 247, 247));
		textArea.setBounds(10, 90, 445, 210);
		textArea.setLineWrap(true);
		
		JScrollPane scrollPane=new JScrollPane(textArea);
		scrollPane.setBounds(66, 40, 398, 215);
		//scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane);

		// "뒤로 가기" 버튼 설정
		ImageIcon back = ImageManager.GetImageUsingFileSystem("src/assets/UI/back.png",25,25);
		JLabel backBtn = new JLabel(back);
		// 마우스 클릭 이벤트 처리
		backBtn.setBounds(12, 9, 25, 25);
		backBtn.setBackground(new Color(255, 255,255));
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFeed();
				dispose();
			}
		});
		appbar.add(backBtn);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBorder(null);
		scrollPane_1.setBounds(0, 254, 452, 118);
		contentPane.add(scrollPane_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 255));
		scrollPane_1.setViewportView(panel);


		// 이미지 및 해시태그 추가를 위한 입력 필드 및 버튼 설정
		imurl = new JTextField();
		imurl.setBounds(10, 382, 333, 32);
		contentPane.add(imurl);
		
		imurl.setColumns(10);
		
		
		ImageIcon addImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/image_button.png",81,32);
		JButton btnadd = new JButton(addImage);
		btnadd.setBounds(371, 382, 81, 32);
		btnadd.setContentAreaFilled(false);
		btnadd.setOpaque(false);
		contentPane.add(btnadd);
		
		hashTextField = new JTextField();
		hashTextField.setBounds(10, 417, 333, 32);
		contentPane.add(hashTextField);
		hashTextField.setColumns(10);
		
		ImageIcon tagImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/tag.png",81,32);
		Set<String> tags = new HashSet<String>();
		JButton hashTagBtn = new JButton(tagImage);
		hashTagBtn.setContentAreaFilled(false);
		hashTagBtn.setOpaque(false);
		
		
		
		hashTagBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.invalidate();

				UserDao userDao = new UserDao();
				UserDto rs = userDao.selectUser(hashTextField.getText());

				if(rs != null) {
					if(rs.equals("0000"))
						return;
				}
				else
					return;

				
				tags.add(hashTextField.getText());
			}
		});
		hashTagBtn.setBounds(371, 417, 81, 32);;
		contentPane.add(hashTagBtn);

		List<String> urls = new ArrayList<String>();
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.invalidate(); 
				scrollPane_1.invalidate();
				
				ImageIcon img = ImageManager.GetImageUsingURL(imurl.getText(), 110,110);
				JLabel lb = new JLabel(img);				
				panel.add(lb);
				panel.validate();
				scrollPane_1.validate();
				
				
				urls.add(imurl.getText());
		    }
		    
		});

		// "Twit" 버튼(게시 버튼) 설정
		ImageIcon twit = ImageManager.GetImageUsingFileSystem("src/assets/UI/twit.png",81,32);
		JButton btnpost = new JButton(twit);
		// 마우스 클릭 이벤트 처리

		btnpost.setBounds(375, 5, 81, 32);
		btnpost.setContentAreaFilled(false);
		btnpost.setOpaque(false);
		appbar.add(btnpost);

		// 프로필 아이콘 및 이미지 아이콘 설정
		ImageIcon userImage = ImageManager.GetUserProfile(ClientInformation.Logined_id, 50, 50);
		JLabel profileIcon = new JLabel(userImage);
		profileIcon.setBounds(10, 45, 50, 50);
		contentPane.add(profileIcon);
		
		ImageIcon iIcon = ImageManager.GetImageUsingFileSystem("src/assets/UI/image.png", 35, 35);
		JLabel imageIcon = new JLabel(iIcon);
		imageIcon.setBounds(15, 209, 35, 35);
		contentPane.add(imageIcon);


		// "Twit" 버튼 클릭 시 게시물 작성 및 MainFeed로 이동하는 로직 설정
		btnpost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	String content = textArea.getText();
            	Connection con = SQLMethods.GetCon();
            	List<String> tagList = new ArrayList<String>(tags);

				// 게시물 작성 메서드 호출
				SQLMethods.WritePost(con,ClientInformation.Logined_id,content, tagList.toArray(new String[0]));
				new MainFeed();
				dispose();
		}
	
		
	});
		
		
		setVisible(true);
	}	
}