package view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.Database;
import listener.RegisterListener;
import java.awt.Color;

public class RegisterWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField nameTf;
	private JPasswordField passwordTf;
	private Database db;

	public RegisterWindow(Database db) {
		this.db = db;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon("assets/GameImages/iconGame.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Snake register");

		nameTf = new JTextField();
		nameTf.setBounds(95, 11, 130, 30);
		getContentPane().add(nameTf);
		nameTf.setColumns(10);

		passwordTf = new JPasswordField();
		passwordTf.setBounds(95, 52, 130, 30);
		getContentPane().add(passwordTf);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Showcard Gothic", Font.PLAIN, 13));
		lblName.setBounds(51, 11, 45, 30);
		getContentPane().add(lblName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Showcard Gothic", Font.PLAIN, 13));
		lblPassword.setBounds(10, 54, 86, 30);
		getContentPane().add(lblPassword);

		JButton registerButton = new JButton("Register");
		registerButton.setBounds(95, 93, 130, 42);
		registerButton.addActionListener(new RegisterListener(this, this.db));
		getContentPane().add(registerButton);
		
		JLabel backGround = new JLabel("New label");
		backGround.setBounds(0, 0, 229, 140);
		backGround.setIcon(new ImageIcon("assets/gameImages/endImage.jpg"));
		getContentPane().add(backGround);

		this.setResizable(false);
		this.setSize(235, 169);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	public String getName(){
		return this.nameTf.getText();
	}

	public String getPassword(){	
		return String.valueOf(this.passwordTf.getPassword());
	}
}
