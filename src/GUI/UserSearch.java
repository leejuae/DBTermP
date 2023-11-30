package GUI;

import Dao.UserDao;
import Dto.user.UserDto;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserSearch extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	// 사용자 검색 기능을 가진 UserSearch 생성자
	public UserSearch() {
		// 프레임 설정
		setBounds(150, 150, 480, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 검색 UI 구성을 위한 패널들을 생성하고 추가합니다.
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 464, 80);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(65, 10, 315, 60);
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));

		// 검색 필드, 검색 아이콘, 검색 버튼 등이 포함됩니다.
		JLabel icon = new JLabel(ImageManager.GetImageUsingFileSystem("src/assets/UI/search_2.png",10,10));
		icon.setPreferredSize(new Dimension(40, 40));
		panel_2.add(icon);
		
		textField = new JTextField();
		textField.setBackground(new Color(247, 247, 247));
		textField.setPreferredSize(new Dimension(274, 40));
		panel_2.add(textField);
		
		ImageIcon enterImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/enter button.png", 61, 24);
		JLabel enterBtn = new JLabel(enterImage);
		
		enterBtn.setHorizontalAlignment(SwingConstants.CENTER);
		enterBtn.setBackground(new Color(255, 255, 255));
		enterBtn.setBounds(392, 30, 61, 24);
		panel.add(enterBtn);
		
		ImageIcon backImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/back.png", 40, 40);
		JLabel backBtn = new JLabel(backImage);
		backBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFeed();
				dispose();
			}
		});
		backBtn.setBounds(12, 20, 41, 40);
		panel.add(backBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPane = new JScrollPane(panel_1);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 79, 464, 682);
		contentPane.add(scrollPane);

		UserDao userDao = new UserDao();
		UserDto[] users = userDao.selectUserList().toArray(new UserDto[0]);

//		UserDto[] users = SQLMethods.GetUsers(SQLMethods.GetCon());
		
		for(int i =0;i < users.length; i++) {
			if(users[i].getUser_id().compareTo(ClientInformation.Logined_id) == 0)
				continue;
			
			UserPanel up = new UserPanel(users[i]);
			panel_1.add(up);
		}

		// 검색 버튼 클릭 이벤트 처리
		enterBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 검색 버튼 클릭 시 동작
				System.out.println("Enter click");
				panel_1.invalidate();
				panel_1.removeAll();
				panel_1.revalidate();

				// 사용자 목록을 반복하며 입력한 검색어와 일치하는 경우 패널에 추가
				for(int i =0;i < users.length; i++) {
					if(!users[i].getUser_id().contains(textField.getText()))
						continue;
					
					if(users[i].getUser_id().compareTo(ClientInformation.Logined_id) == 0)
						continue;
					
					UserPanel up = new UserPanel(users[i]);
					panel_1.add(up);
				}
				
				panel_1.repaint();
			}
		});

		// 화면에 패널들을 추가하고 보이도록 설정합니다.
		setVisible(true);
	}
}
