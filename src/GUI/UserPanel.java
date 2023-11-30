package GUI;

import Dao.FollowDao;
import Dao.UserDao;
import Dto.user.UserDto;

import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class UserPanel extends JPanel {

	JPanel panel;
	private ImageAvatar imageAvatar;

	// 패널의 최대 크기를 설정하는 메서드
	public Dimension getMaximumSize() {
		Dimension d = getPreferredSize();
		d.width = Integer.MAX_VALUE;
		
		return d;
	}

	// 사용자 패널의 생성자
	public UserPanel(UserDto user) {
		// 패널 속성 설정
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setPreferredSize(new Dimension(464, 75));
		setBounds(0, 0, 464, 75);
		setLayout(null);

		// 패널 내의 panel 구성
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 464, 75);
		panel.setPreferredSize(new Dimension(464, 75));
		panel.setLayout(null);

		// 사용자 클릭 이벤트 처리
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 사용자 프로필 열기
				new Profile(user.getUser_id());
			}
		});
		add(panel); // panel을 UserPanel에 추가


		// 사용자 프로필 이미지를 가져와서 avatar로 설정
		ImageIcon uIcon = ImageManager.GetUserProfile(user.getUser_id(), 50, 50);
		imageAvatar = initComponents(uIcon); // 프로필 이미지 설정
		imageAvatar.setBounds(0, 5, 60, 60); // 이미지 배치 설정
		imageAvatar.setBorderColor(new Color(255,255,255));
		panel.add(imageAvatar); // imageAvatar를 panel에 추가

		// 사용자 정보 표시 패널 설정
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(80, 15, 158, 40);
		infoPanel.setBackground(new Color(255, 255, 255));
		panel.add(infoPanel); // infoPanel을 panel에 추가
		FlowLayout fl_infoPanel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		infoPanel.setLayout(fl_infoPanel); // infoPanel의 레이아웃 설정

		// 사용자 이름 표시
		JLabel userName = new JLabel(user.getName());
		userName.setFont(new Font("Thoma", Font.PLAIN, 20));
		userName.setForeground(Color.BLACK);
		infoPanel.add(userName); // userName을 infoPanel에 추가

		// 사용자 아이디 표시
		String gId = "@" + user.getUser_id();
		JLabel ID = new JLabel(gId);
		ID.setVerticalAlignment(SwingConstants.BOTTOM);
		ID.setFont(new Font("Thoma", Font.PLAIN, 15));
		ID.setForeground(Color.GRAY);
		infoPanel.add(ID); // ID를 infoPanel에 추가

		FollowDao followDao = new FollowDao();
		String rs = followDao.checkIfFollowing(ClientInformation.Logined_id, user.getUser_id());
		
		String followUrl = "src/assets/UI/follow_en.png";
		if(rs != null) {
			if(rs.compareTo("0000") == 0) {
				followUrl = "src/assets/UI/follow_en.png";
			}
			else {
				followUrl = "src/assets/UI/following.png";
			}

		}
		else
			followUrl = "src/assets/UI/follow_en.png";

		// 팔로우 아이콘 설정 및 팔로우 버튼 표시
		ImageIcon followIcon = ImageManager.GetImageUsingFileSystem(followUrl,116,32);
		JLabel follow = new JLabel(followIcon);
		if(user.getUser_id().equals(ClientInformation.Logined_id)) {
			follow.setVisible(false);
		}
		else
			follow.setVisible((true));
		/*팔로우에 추가하기 (프로필)*/

		// 팔로우 버튼 클릭 이벤트 처리
		follow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Follow Click");
				String rs = followDao.follow(ClientInformation.Logined_id, user.getUser_id());
				
				String imgUrl = "src/assets/UI/followIcon.png";
				if(rs != null) {
					if(rs.equals("0000")) {
						imgUrl = "src/assets/UI/follow_en.png";
					}
					else {
						imgUrl = "src/assets/UI/following.png";
					}

				}
				else
					imgUrl = "src/assets/UI/follow_en.png";
				ImageIcon fIcon = ImageManager.GetImageUsingFileSystem(imgUrl, 116, 32);
				
				follow.setIcon(fIcon);				
			}
			
			
			
		});
		follow.setBounds(336, 15, 116, 32);
		// 팔로우 버튼을 panel에 추가
		panel.add(follow);
		// 사용자 패널이 마지막까지 구성됩니다.
	}
		// Avatar 이미지를 설정하고 반환하는 메서드
		private ImageAvatar initComponents(ImageIcon icon) {
		// ImageAvatar 초기화 및 설정
		ImageAvatar imageAvatar1 = new ImageAvatar();

        //ImageIcon profileIcon = ImageManager.GetImageUsingFileSystem("src/assets/profile_image.png",50,50);
		
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
        setBounds(0, 0, 464, 75);
        //setLocationRelativeTo(null);

			// ImageAvatar를 설정한 후 반환합니다.
        return imageAvatar1;
    }
}
