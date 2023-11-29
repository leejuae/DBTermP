package GUI;

import Dao.UserDao;
import Dto.user.UserDto;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Timestamp;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SignIn extends JFrame {

    private JPanel contentPane;
    private JTextField IDTextField;
    private JPasswordField PWTextField;
    private JTextField NameTextField;
    private JTextField IntroductionTextField;

    /**
     * Create the frame.
     */
    public SignIn() {
        setBounds(150, 150, 300, 480);
        setLocationRelativeTo(null);
        setTitle("Sign in");
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon logoImage = ImageManager.GetImageUsingFileSystem("src/assets/logo.png", 100, 100);
        JLabel Logo = new JLabel(logoImage);
        Logo.setBackground(new Color(133, 251, 254));
        Logo.setBounds(92, 32, 100, 100);
        contentPane.add(Logo);

        JLabel IDText = new JLabel("ID:");
        IDText.setFont(new Font("Arial", Font.PLAIN, 12));
        IDText.setBounds(39, 163, 28, 19);
        contentPane.add(IDText);

        IDTextField = new JTextField();
        IDTextField.setColumns(10);
        IDTextField.setBounds(65, 155, 206, 38);
        contentPane.add(IDTextField);

        PWTextField = new JPasswordField();
        PWTextField.setColumns(10);
        PWTextField.setEchoChar('*');
        PWTextField.setBounds(65, 203, 206, 38);
        contentPane.add(PWTextField);

        NameTextField = new JTextField();
        NameTextField.setColumns(10);
        NameTextField.setBounds(65, 251, 206, 38);
        contentPane.add(NameTextField);

        JLabel PWText = new JLabel("PW:");
        PWText.setFont(new Font("Arial", Font.PLAIN, 12));
        PWText.setBounds(28, 214, 39, 19);
        contentPane.add(PWText);

        JLabel NameText = new JLabel("Name:");
        NameText.setFont(new Font("Arial", Font.PLAIN, 12));
        NameText.setBounds(12, 262, 55, 19);
        contentPane.add(NameText);

        IntroductionTextField = new JTextField();
        IntroductionTextField.setColumns(10);
        IntroductionTextField.setBounds(65, 310, 206, 60);
        contentPane.add(IntroductionTextField);

        JLabel lblIntroduction = new JLabel("Introduction:");
        lblIntroduction.setFont(new Font("Arial", Font.PLAIN, 12));
        lblIntroduction.setBounds(12, 304, 110, 19);
        contentPane.add(lblIntroduction);

        ImageIcon signinImage = ImageManager.GetImageUsingFileSystem("src/assets/UI/sign_up.png",190,36);
        JButton SigninBtn = new JButton(signinImage);
        SigninBtn.setBounds(45, 383, 190, 36);
        SigninBtn.setContentAreaFilled(false);
        SigninBtn.setOpaque(false);
        SigninBtn.setBorder(null);
        SigninBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user_id;
                String pw = null;
                String name = null;
                String profile_pic = "src/assets/UI/sign_up.png";
                String introduction = "introduction";
                Timestamp created_at = new Timestamp(System.currentTimeMillis());


                user_id = IDTextField.getText();
                pw = PWTextField.getText();
                name = NameTextField.getText();
//                email = EMailText.getText();
//                Connection con = SQLMethods.GetCon();

                UserDto userDto = new UserDto();
                UserDao userDao = new UserDao();

                userDto.setUser_id(user_id);
                userDto.setPw(pw);
                userDto.setName(name);
                userDto.setProfile_pic(profile_pic);
                userDto.setIntroduction(introduction);
                userDto.setCreated_at(created_at);

                boolean result = userDao.regUser(userDto);

//                int result = SQLMethods.Signin(con,id,pw,name,email);

                if(user_id == null || user_id.equals("")) {
                    new CustomDialog("Dialog", "Please Enter ID!");
                }
                else if(pw == null || pw.equals("")) {
                    new CustomDialog("Dialog", "Please Enter Password!");
                }
                else if(name == null || name.equals("")) {
                    new CustomDialog("Dialog", "Please Enter name!");
                }
                else {
                    if(!result)
                    {
                        new CustomDialog("Dialog", "ID is already exists!");
                    }
                    else if(result){
                        new CustomDialog("Dialog", "Sign in Success!");
                        dispose();
                    }
                    else
                        new CustomDialog("Dialog", "Error!");

                }
            }
        });

        contentPane.add(SigninBtn);


        setVisible(true);
    }
}
