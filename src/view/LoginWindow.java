package view;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.PlayerController;
import database.Database;
import listener.LoginListener;
import java.awt.Color;

public class LoginWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField nameTf;
	private JPasswordField passwordTf;
	
	private Database db;
	private MenuWindow mw;
	private PlayerController pc;

	public LoginWindow(Database db, MenuWindow mw, PlayerController pc) {	
		this.db = db;
		this.mw = mw;
		this.pc = pc;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon("assets/GameImages/iconGame.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Snake Login");
		
		nameTf = new JTextField();
		nameTf.setBounds(103, 11, 130, 30);
		getContentPane().add(nameTf);
		nameTf.setColumns(10);

		passwordTf = new JPasswordField();
		passwordTf.setBounds(103, 54, 130, 30);
		getContentPane().add(passwordTf);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Showcard Gothic", Font.PLAIN, 13));
		lblName.setBounds(60, 11, 45, 30);
		getContentPane().add(lblName);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Showcard Gothic", Font.PLAIN, 13));
		lblPassword.setBounds(19, 54, 86, 30);
		getContentPane().add(lblPassword);


		JButton loginButton = new JButton("Login");
		loginButton.setBounds(103, 95, 130, 42);
		loginButton.addActionListener(new LoginListener(this, this.db, this.mw, this.pc));
		getContentPane().add(loginButton);
		
		JLabel backGround = new JLabel("New label");
		backGround.setIcon(new ImageIcon("assets/gameImages/endImage.jpg"));
		backGround.setBounds(0, 0, 241, 143);
		getContentPane().add(backGround);

		this.setResizable(false);
		this.setSize(247, 172);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
	
	public void reset(){
		this.nameTf.setText("");
		this.passwordTf.setText("");
	}
	
	public String getName(){
		return this.nameTf.getText();
	}

	public String getPassword(){	
		return String.valueOf(this.passwordTf.getPassword());
	}
}
