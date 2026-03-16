package cybersecurity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Random;

public class LoginInterface {

    static HashMap<String, String> users = new HashMap<>();

    public static void main(String[] args) {
    	try {
    	    for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
    	        if("Nimbus".equals(info.getName())) {
    	            UIManager.setLookAndFeel(info.getClassName());
    	            break;
    	        }
    	    }
    	} catch(Exception e) {
    	    e.printStackTrace();
    	}
    
        JFrame frame = new JFrame("Cybersecurity Sign In");
        frame.setSize(500,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(34,34,34));
        frame.setLayout(new GridBagLayout());
        frame.setLocationRelativeTo(null);
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0,0,new Color(60,60,60), 0,getHeight(), new Color(30,30,30));
                g2d.setPaint(gp);
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),20,20);
            }
        };
        panel.setLayout(new GridBagLayout());
        frame.add(panel);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Sign In");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        frame.add(titleLabel, gbc);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx=0; gbc.gridy=1; gbc.gridwidth=1;
        frame.add(userLabel, gbc);

        JTextField userField = new JTextField();
        userField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx=1; gbc.gridy=1;
        frame.add(userField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx=0; gbc.gridy=2;
        frame.add(passLabel, gbc);

        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx=1; gbc.gridy=2;
        frame.add(passField, gbc);

        JButton signInButton = new JButton("Sign In");
        signInButton.setBackground(new Color(0,120,215));
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false);
        gbc.gridx=0; gbc.gridy=3;
        frame.add(signInButton, gbc);

        JButton generateButton = new JButton("Generate Password");
        generateButton.setBackground(new Color(0,150,0));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        gbc.gridx=1; gbc.gridy=3;
        frame.add(generateButton, gbc);

        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                if(username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame,"Please enter username and password");
                    return;
                }
                String analysis = analyzePassword(password);
                JOptionPane.showMessageDialog(frame, analysis, "Password Analysis", JOptionPane.INFORMATION_MESSAGE);
                users.put(username, password);
                JOptionPane.showMessageDialog(frame,"Account created for: " + username);
                userField.setText("");
                passField.setText("");
            }
        });
        signInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signInButton.setBackground(new Color(0,150,255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signInButton.setBackground(new Color(0,120,215));
            }
        });
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = generatePassword();
                String analysis = analyzePassword(password);
                JOptionPane.showMessageDialog(frame, "Generated password:\n" + password + "\n\n" + analysis);
            }
        });

        frame.setVisible(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 26));
    }
    
    public static String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        Random random = new Random();
        String password = "";
        for(int i=0;i<12;i++) {
            int index = random.nextInt(chars.length());
            password += chars.charAt(index);
        }
        return password;
    }

    public static String analyzePassword(String password) {
        int score = 0;
        boolean hasDigit = false;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasSpecial = false;
        StringBuilder result = new StringBuilder();
        if(password.length() < 8) result.append("Password too short\n");
        for(char c : password.toCharArray()) {
            if(Character.isDigit(c)) hasDigit=true;
            if(Character.isUpperCase(c)) hasUppercase=true;
            if(Character.isLowerCase(c)) hasLowercase=true;
            if(!Character.isLetterOrDigit(c)) hasSpecial=true;
        }
        if(!hasDigit) result.append("Password should contain a number\n"); else score+=20;
        if(!hasUppercase) result.append("Password should contain an uppercase letter\n"); else score+=20;
        if(!hasLowercase) result.append("Password should contain a lowercase letter\n"); else score+=20;
        if(!hasSpecial) result.append("Password should contain a special character\n"); else score+=20;
        result.append("Password score: ").append(score).append("/100\n");
        if(score<40) result.append("Strength: Weak"); else if(score<80) result.append("Strength: Medium"); else result.append("Strength: Strong");
        return result.toString();
    }
}