package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import control.PlayerController;
import database.Database;
import listener.MenuListener;
import javax.swing.JComboBox;

public class MenuWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public JButton btnPlay;
	public JButton btnHighscore;
	public JButton btnExit;
	public JButton btnRegister;
	public JButton btnLogin;
	public JLabel loggedIn;
	public JButton btnEasy;
	public JButton btnHard;
	public JButton btnMedium;
	public JComboBox<String> snakeColor;
	public JButton btnBarrier;
	public JButton btnNoBarrier;
	
	private Database db;
	private PlayerController pc;
	public MenuListener ml;

	public MenuWindow(Database db, PlayerController pc) {
		this.db = db;
		this.pc = pc;
		this.ml = new MenuListener(this, this.db, this.pc);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon("assets/GameImages/iconGame.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Snake Menu");
		
		btnPlay = new JButton("Play");
		btnPlay.setBounds(10, 112, 179, 62);
		btnPlay.addActionListener(this.ml);
		btnPlay.setIcon(new ImageIcon("assets/gameImages/playButton.png"));
		getContentPane().add(btnPlay);
		
		loggedIn = new JLabel("");
		loggedIn.setVerticalAlignment(SwingConstants.TOP);
		loggedIn.setForeground(Color.white);
		loggedIn.setFont(new Font("Showcard Gothic", Font.PLAIN, 12));
		loggedIn.setBounds(10, 11, 179, 44);
		getContentPane().add(loggedIn);

		btnHighscore = new JButton("Highscore");
		btnHighscore.setBounds(10, 185, 179, 62);
		btnHighscore.addActionListener(this.ml);
		btnHighscore.setIcon(new ImageIcon("assets/gameImages/highscoreButton.png"));
		getContentPane().add(btnHighscore);

		btnExit = new JButton("Exit");
		btnExit.setBounds(10, 258, 179, 62);
		btnExit.addActionListener(this.ml);
		btnExit.setIcon(new ImageIcon("assets/gameImages/exitButton.png"));
		getContentPane().add(btnExit);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(104, 66, 84, 35);
		btnRegister.setIcon(new ImageIcon("assets/gameImages/registerButton.png"));
		btnRegister.addActionListener(this.ml);
		getContentPane().add(btnRegister);
		
		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon("assets/gameImages/loginButton.png"));
		btnLogin.setBounds(10, 66, 84, 35);
		btnLogin.addActionListener(this.ml);
		getContentPane().add(btnLogin);
		
		String[] colors = {"Green(Default)", "Red", "Blue", "Pink", "Gray"};
		snakeColor = new JComboBox<String>(colors);
		this.snakeColor.setEnabled(false);
		snakeColor.setBounds(464, 23, 120, 20);
		snakeColor.addActionListener(this.ml);
		getContentPane().add(snakeColor);
		
		btnEasy = new JButton("Easy");
		btnEasy.setBounds(312, 297, 84, 23);
		btnEasy.setVisible(false);
		btnEasy.addActionListener(this.ml);
		btnEasy.setEnabled(false);
		getContentPane().add(btnEasy);
		
		btnHard = new JButton("Hard");
		btnHard.setBounds(500, 297, 84, 23);
		btnHard.setVisible(false);
		btnHard.addActionListener(this.ml);
		getContentPane().add(btnHard);
		
		btnMedium = new JButton("Medium");
		btnMedium.setBounds(406, 297, 84, 23);
		btnMedium.setVisible(false);
		btnMedium.addActionListener(this.ml);
		getContentPane().add(btnMedium);
		
		btnBarrier = new JButton("Barrier");
		btnBarrier.setBounds(452, 263, 132, 23);
		btnBarrier.addActionListener(this.ml);
		btnBarrier.setVisible(false);
		getContentPane().add(btnBarrier);
		
		btnNoBarrier = new JButton("No barrier");
		btnNoBarrier.setBounds(312, 263, 130, 23);
		btnNoBarrier.addActionListener(this.ml);
		btnNoBarrier.setVisible(false);
		btnNoBarrier.setEnabled(false);
		getContentPane().add(btnNoBarrier);
		
		JLabel backGroundImage = new JLabel();
		backGroundImage.setIcon(new ImageIcon("assets/GameImages/mainImage.png"));
		backGroundImage.setBounds(0, 0, 594, 328);
		getContentPane().add(backGroundImage);


		this.setResizable(false);
		this.setSize(600, 357);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
	
	public void updateAccount(String name){	
		loggedIn.setText("Logged in: " + name);
	}
	
	public void updateColorButton(){
		this.snakeColor.setEnabled(true);
	}
	public void updateModeButton() {	
		btnEasy.setVisible(true);
		btnMedium.setVisible(true);
		btnHard.setVisible(true);
		btnBarrier.setVisible(true);
		btnNoBarrier.setVisible(true);
	}
}
