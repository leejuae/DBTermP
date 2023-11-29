import Dao.UserDao;
import Dto.user.UserDto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JPanel contentPane;
    private JTextField IDInput;
    private JPasswordField PWInput;

    public LoginFrame() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        /*IDInput = new JTextField();
        IDInput.setBounds(140, 100, 200, 25);
        contentPane.add(IDInput);
        IDInput.setColumns(10);*/

        JLabel labelId = createLabel("ID");
        labelId.setBounds(50, 100, 80, 25);

        JTextField textId = createTextField();
        textId.setBounds(140, 100, 200, 25);

        JLabel labelPassword = createLabel("Password");
        labelPassword.setBounds(50, 140, 80, 25);

        JPasswordField fieldPassword = createPasswordField();
        fieldPassword.setBounds(140, 140, 200, 25);
        
        JLabel labelLogo = createLogo("Twitter");
        labelLogo.setBounds(140,25,400,40);

        JButton buttonLogin = createButton("Login");
        buttonLogin.setBounds(140, 180, 95, 25);
        JButton buttonSignUp = createButton("Sign up");
        buttonSignUp.setBounds(245, 180, 95, 25);
        buttonSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SignUpFrame();
                LoginFrame.this.setVisible(false);
            }
           
        });
        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = textId.getText();
                String pw = fieldPassword.getText();

                UserDto userDto = new UserDto();
                UserDao userDao = new UserDao();
                userDto = userDao.selectUser(id);

                if (userDto.getPw().equals(pw)) {
                    System.out.println("YES");
                    UserDto result = userDto;
                    new HomeFrame();
                    LoginFrame.this.setVisible(false);

//					new MainFeed();
//                    dispose();
                } else if (userDto == null) {
                    System.out.println("no - existing pw");
                    return;
                } else {
                    System.out.println("login failed");
                    return;
                }
                UserDto result = userDto;

//                new HomeFrame();
//                LoginFrame.this.setVisible(false);
            }
           
        });

        add(labelId);
        add(textId);
        add(labelLogo);
        add(labelPassword);
        add(fieldPassword);
        add(buttonLogin);
        add(buttonSignUp);

        setTitle("Twitter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private JLabel createLogo(String text) {
    	JLabel label = new JLabel (text);
    	Color skyBlue = new Color(135,206,250);
    	label.setForeground(skyBlue);
    	label.setFont(label.getFont().deriveFont(30.0f));
    	return label;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));
        textField.setBackground(Color.WHITE);
        return textField;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        passwordField.setBackground(Color.WHITE);
        return passwordField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}

class SignUpFrame extends JFrame {
    public SignUpFrame() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        add(createLogo("Twitter"),gbc);
        addLabeledField("ID", createTextField(), gbc);
        addLabeledField("Password", createPasswordField(), gbc);
        addLabeledField("E-mail", createTextField(), gbc);
        addLabeledField("Name", createTextField(), gbc);
        addLabeledField("Phone", createTextField(), gbc);
        addLabeledField("Address", createTextField(), gbc);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(createButton("Submit"));
        buttonPanel.add(createButton("Cancel"));
        add(buttonPanel, gbc);

        pack();
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addLabeledField(String labelText, JTextField textField, GridBagConstraints gbc) {
        JLabel label = createLabel(labelText);
        add(label, gbc);
        add(textField, gbc);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLACK);
        return label;
    }
    
    private JLabel createLogo(String text) {
    	JLabel label = new JLabel (text);
    	Color skyBlue = new Color(135,206,250);
    	label.setForeground(skyBlue);
    	label.setFont(label.getFont().deriveFont(30.0f));
    	return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 25));
        textField.setBackground(Color.WHITE);
        return textField;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        passwordField.setBackground(Color.WHITE);
        return passwordField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        return button;
    }
}

class HomeFrame extends JFrame {
 private JComboBox<String> comboBox;

 public HomeFrame() {
     getContentPane().setBackground(Color.WHITE);
     setLayout(new GridBagLayout());
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.gridwidth = GridBagConstraints.REMAINDER;
     gbc.fill = GridBagConstraints.HORIZONTAL;
     gbc.insets = new Insets(4, 4, 4, 4);

     JLabel labelTwitter = createLogo("Twitter");
     gbc.anchor = GridBagConstraints.NORTHWEST;
     gbc.gridx = 0;
     gbc.gridy = 0;
     gbc.anchor = GridBagConstraints.NORTHWEST;
     add(labelTwitter, gbc);

     String[] options = {"Home", "Following", "Follower", "Profile"};
     comboBox = new JComboBox<>(options);
     gbc.gridx = 0;
     gbc.gridy = 1;
     gbc.weightx = 1.0;
     gbc.weighty = 1.0;
     add(comboBox, gbc);
     
     JSeparator horizontalLine = new JSeparator(SwingConstants.HORIZONTAL);
     gbc.gridx = 0;
     gbc.gridy = 2;
     gbc.weightx = 1.0;
     gbc.weighty = 30;
     gbc.insets = new Insets(1, 1, 1, 1);
     add(horizontalLine, gbc);
     
     comboBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             String selectedOption = (String) comboBox.getSelectedItem();
             
             if("Profile".equals(selectedOption)) {
             	dispose();
             	new ProfileFrame();
             }
             else if("Following".equals(selectedOption)) {
             	dispose();
             	new FollowingFrame();
             }
             else if("Follower".equals(selectedOption)) {
             	dispose();
             	new FollowerFrame();
             }
         }
     });

     setSize(300, 500);
     setTitle("Home");
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     setLocationRelativeTo(null);
     setVisible(true);
 }

 private JLabel createLogo(String text) {
     JLabel label = new JLabel(text);
     Color skyBlue = new Color(135, 206, 250);
     label.setForeground(skyBlue);
     label.setFont(label.getFont().deriveFont(30.0f));
     return label;
 }

 public static void main(String[] args) {
     SwingUtilities.invokeLater(HomeFrame::new);
 }
}

class ProfileFrame extends JFrame {
 private JComboBox<String> comboBox;

 public ProfileFrame() {
     getContentPane().setBackground(Color.WHITE);
     setLayout(new GridBagLayout());
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.gridwidth = GridBagConstraints.REMAINDER;
     gbc.fill = GridBagConstraints.HORIZONTAL;
     gbc.insets = new Insets(4, 4, 4, 4);

     JLabel labelTwitter = createLogo("Twitter");
     gbc.anchor = GridBagConstraints.NORTHWEST;
     gbc.gridx = 0;
     gbc.gridy = 0;
     add(labelTwitter, gbc);

     String[] options = {"Profile", "Home", "Following", "Follower"};
     comboBox = new JComboBox<>(options);
     gbc.gridx = 0;
     gbc.gridy = 1;
     gbc.weightx = 1.0;
     gbc.weighty = 1.0;
     gbc.anchor = GridBagConstraints.NORTHWEST;
     add(comboBox, gbc);
     
     JSeparator horizontalLine = new JSeparator(SwingConstants.HORIZONTAL);
     gbc.gridx = 0;
     gbc.gridy = 2;
     gbc.weightx = 1.0;
     gbc.weighty = 30;
     gbc.insets = new Insets(1, 1, 1, 1);
     add(horizontalLine, gbc);

     comboBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             String selectedOption = (String) comboBox.getSelectedItem();
             
             if("Home".equals(selectedOption)) {
             	dispose();
             	new HomeFrame();
             }
             else if("Following".equals(selectedOption)) {
             	dispose();
             	new FollowingFrame();
             }
             else if("Follower".equals(selectedOption)) {
             	dispose();
             	new FollowerFrame();
             }
         }
     });

     setSize(300, 500);
     setTitle("Profile");
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     setLocationRelativeTo(null);
     setVisible(true);
 }

 private JLabel createLogo(String text) {
     JLabel label = new JLabel(text);
     Color skyBlue = new Color(135, 206, 250);
     label.setForeground(skyBlue);
     label.setFont(label.getFont().deriveFont(30.0f));
     return label;
 }

 public static void main(String[] args) {
     SwingUtilities.invokeLater(ProfileFrame::new);
 }
}

class FollowingFrame extends JFrame {
 private JComboBox<String> comboBox;

 public FollowingFrame() {
     getContentPane().setBackground(Color.WHITE);
     setLayout(new GridBagLayout());
     GridBagConstraints gbc = new GridBagConstraints();
     gbc.gridwidth = GridBagConstraints.REMAINDER;
     gbc.fill = GridBagConstraints.HORIZONTAL;
     gbc.insets = new Insets(4, 4, 4, 4);

     JLabel labelTwitter = createLogo("Twitter");
     gbc.anchor = GridBagConstraints.NORTHWEST;
     gbc.gridx = 0;
     gbc.gridy = 0;
     add(labelTwitter, gbc);

     String[] options = {"Following", "Home", "Follower", "Profile"};
     comboBox = new JComboBox<>(options);
     gbc.gridx = 0;
     gbc.gridy = 1;
     gbc.weightx = 1.0;
     gbc.weighty = 1.0;
     gbc.anchor = GridBagConstraints.NORTHWEST;
     add(comboBox, gbc);
     
     JSeparator horizontalLine = new JSeparator(SwingConstants.HORIZONTAL);
     gbc.gridx = 0;
     gbc.gridy = 2;
     gbc.weightx = 1.0;
     gbc.weighty = 30;
     gbc.insets = new Insets(1, 1, 1, 1);
     add(horizontalLine, gbc);

     comboBox.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             String selectedOption = (String) comboBox.getSelectedItem();
             
             if("Profile".equals(selectedOption)) {
             	dispose();
             	new ProfileFrame();
             }
             else if("Home".equals(selectedOption)) {
             	dispose();
             	new HomeFrame();
             }
             else if("Follower".equals(selectedOption)) {
             	dispose();
             	new FollowerFrame();
             }
         }
     });

     setSize(300, 500);
     setTitle("Following");
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     setLocationRelativeTo(null);
     setVisible(true);
 }

 private JLabel createLogo(String text) {
     JLabel label = new JLabel(text);
     Color skyBlue = new Color(135, 206, 250);
     label.setForeground(skyBlue);
     label.setFont(label.getFont().deriveFont(30.0f));
     return label;
 }

 public static void main(String[] args) {
     SwingUtilities.invokeLater(FollowingFrame::new);
 }
}


class FollowerFrame extends JFrame {
    private JComboBox<String> comboBox;

    public FollowerFrame() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        JLabel labelTwitter = createLogo("Twitter");
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelTwitter, gbc);

        String[] options = {"Follower", "Home", "Following", "Profile"};
        comboBox = new JComboBox<>(options);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(comboBox, gbc);
        
        JSeparator horizontalLine = new JSeparator(SwingConstants.HORIZONTAL);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 30;
        gbc.insets = new Insets(1, 1, 1, 1);
        add(horizontalLine, gbc);

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();

                if ("Profile".equals(selectedOption)) {
                    dispose();
                    new ProfileFrame();
                } else if ("Home".equals(selectedOption)) {
                    dispose();
                    new HomeFrame();
                } else if ("Following".equals(selectedOption)) {
                    dispose();
                    new FollowingFrame();
                }
            }
        });

        setSize(300, 500);
        setTitle("Follower");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLogo(String text) {
        JLabel label = new JLabel(text);
        Color skyBlue = new Color(135, 206, 250);
        label.setForeground(skyBlue);
        label.setFont(label.getFont().deriveFont(30.0f));
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FollowerFrame::new);
    }     
}

class PostFrame extends JFrame {

    public PostFrame() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        
        JPanel logoPanel = new JPanel();
        logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(Color.WHITE);

        JLabel labelTwitter = createLogo("Twitter");
        logoPanel.add(labelTwitter);

        JPanel homeButtonPanel = new JPanel();
        homeButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        homeButtonPanel.setBackground(Color.WHITE);

        JButton homeButton = createButton("Home");
        homeButtonPanel.add(homeButton);

        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(logoPanel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(homeButtonPanel, gbc);

        JSeparator horizontalLine = new JSeparator(SwingConstants.HORIZONTAL);
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 30;
        gbc.insets = new Insets(1, 1, 1, 1);
        add(horizontalLine, gbc);

        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HomeFrame();
                PostFrame.this.setVisible(false);
            }
        });

        setSize(300, 500);
        setTitle("Post");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createLogo(String text) {
        JLabel label = new JLabel(text);
        Color skyBlue = new Color(135, 206, 250);
        label.setForeground(skyBlue);
        label.setFont(label.getFont().deriveFont(30.0f));
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PostFrame::new);
    }
}

