package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @wbp.parser.constructor
	 */
	
	//Ÿ��Ʋ�� ������ Ŀ���� ������ ���̾�α�(�˸�â)
	public CustomDialog(String dialogTitle, String content) {
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
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds((int)(w / 2) - 55, (int)(h * 0.5) + 10, 90, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel ContentLabel = new JLabel(content);
		ContentLabel.setBounds(10, 10, w - 35, (int)(h * 0.4));
		contentPane.add(ContentLabel);
		
		setVisible(true);
		
	}
}
