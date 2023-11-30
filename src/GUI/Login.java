package GUI;

import Dao.UserDao;
import Dto.user.UserDto;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.sql.Connection;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField IDInput;
	private JPasswordField PWInput;
	/**
	 * Create the frame.
	 */
	public Login() {
		// 윈도우 프레임 설정
		setBounds(100, 100, 300, 480);
		setLocationRelativeTo(null);
		setTitle("Login");

		// 콘텐츠 패널 설정
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 로고 이미지 및 텍스트 설정
		ImageIcon logoImage = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 100, 100);
		ImageIcon logoText = ImageManager.GetImageUsingFileSystem("src/assets/UI/text_logo.png", 174, 39);

		JLabel Logo = new JLabel(logoImage);
		Logo.setBackground(new Color(133, 251, 254));
		Logo.setBounds(92, 32, 100, 100);
		contentPane.add(Logo);

		// ID 입력 필드
		IDInput = new JTextField();
		IDInput.setBackground(new Color(236, 236, 236));
		IDInput.setBounds(65, 233, 206, 38);
		contentPane.add(IDInput);
		IDInput.setColumns(10);

		// PW 입력 필드
		PWInput = new JPasswordField();
		PWInput.setEchoChar('*');
		PWInput.setBackground(new Color(236, 236, 236));
		PWInput.setColumns(10);
		PWInput.setBounds(65, 281, 206, 38);
		contentPane.add(PWInput);

		// 트위터 텍스트 로고
		JLabel TwitterTextLogo = new JLabel(logoText);
		TwitterTextLogo.setBounds(55, 142, 174, 39);
		contentPane.add(TwitterTextLogo);

		// ID, PW 레이블
		JLabel IDText = new JLabel("ID:");
		IDText.setFont(new Font("Arial", Font.PLAIN, 18));
		IDText.setBounds(12, 233, 57, 38);
		contentPane.add(IDText);

		JLabel PasswordText = new JLabel("PW:");
		PasswordText.setFont(new Font("Arial", Font.PLAIN, 18));
		PasswordText.setBounds(12, 281, 57, 38);
		contentPane.add(PasswordText);

		// 로그인 버튼
		ImageIcon LoginImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/login.png", 190, 36);
		JButton LoginBtn = new JButton(LoginImage);
		LoginBtn.setContentAreaFilled(false);
		LoginBtn.setOpaque(false);
		LoginBtn.setBorder(null);
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 로그인 버튼 클릭 시 수행되는 작업
				String id = IDInput.getText();
				String pw = PWInput.getText();

				UserDao userDao = new UserDao();
				UserDto loginUser = new UserDto();

				loginUser = userDao.selectUser(id);

				if (loginUser == null) {
					new CustomDialog("Dialog", "틀린 계정입니다.");
					return;
				} else if((loginUser.getPw()).equals(pw)){
					// 로그인 성공 시
					ClientInformation.Logined_id = loginUser.getUser_id();
					ClientInformation.Logined_pwd = loginUser.getPw();
					new MainFeed();
					dispose();
				} else{
					System.out.println("뭔가 단단히 잘못됨");
				}
			}
		});
		LoginBtn.setBounds(50, 349, 190, 36);
		contentPane.add(LoginBtn);

		// 회원가입 버튼
		ImageIcon signinImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/sign_up.png", 190, 36);
		JButton RegisterBtn = new JButton(signinImage);
		RegisterBtn.setContentAreaFilled(false);
		RegisterBtn.setOpaque(false);
		RegisterBtn.setBorder(null);
		RegisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 회원가입 버튼 클릭 시
				new Signin();
			}
		});
		RegisterBtn.setBounds(50, 395, 190, 36);
		contentPane.add(RegisterBtn);

		setVisible(true);
	}
}
