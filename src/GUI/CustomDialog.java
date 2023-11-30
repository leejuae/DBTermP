package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

// CustomDialog 클래스는 JFrame을 상속하여 사용자 지정 다이얼로그 창을 생성합니다.
public class CustomDialog extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	public CustomDialog(String dialogTitle,String content) {
		// 다이얼로그 창의 위치 및 크기 설정
		int x = 100;
		int y = 100;
		int w = 300;
		int h = 180;
	
		setBounds(x, y, w, h);
		setLocationRelativeTo(null);
		setTitle(dialogTitle);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 확인 버튼 생성 및 액션 리스너 추가
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds((int)(w / 2) - 55, (int)(h * 0.5) + 10, 90, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			} // 다이얼로그 창 닫기
		});
		contentPane.add(btnNewButton);
		
		JLabel ContentLabel = new JLabel(content);
		ContentLabel.setBounds(10, 10, w - 35, (int)(h * 0.4));
		contentPane.add(ContentLabel);
		
		setVisible(true); // 다이얼로그 창 보이기
		
	}
}
