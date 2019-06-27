package view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import control.PlayerController;
import database.Database;
import listener.EndListener;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class EndMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Database db;
	private PlayerController pc;
	private SnakeFrame sf;
	private MenuWindow mw;
	
	public JButton exit;
	public JButton restart;
	public JButton backToMenu;
	private JLabel lblNewLabel;

	public EndMenu(SnakeFrame sf, Database db, PlayerController pc, MenuWindow mw) {
		this.db = db;
		this.pc = pc;
		this.sf = sf;
		this.mw = mw;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon("assets/GameImages/iconGame.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Snake Game");
		
		restart = new JButton("Restart");
		restart.setBounds(10, 60, 151, 70);
		restart.addActionListener(new EndListener(this, this.sf, this.db, this.pc, this.mw));
		restart.setIcon(new ImageIcon("assets/gameImages/restartButton.png"));
		getContentPane().add(restart);
		
		exit = new JButton("Exit");
		exit.setBounds(332, 60, 151, 70);
		exit.addActionListener(new EndListener(this, this.sf, this.db, this.pc, this.mw));
		exit.setIcon(new ImageIcon("assets/gameImages/exitButton.png"));
		getContentPane().add(exit);
		
		backToMenu = new JButton("Back to Menu");
		backToMenu.setBounds(171, 60, 151, 70);
		backToMenu.addActionListener(new EndListener(this, this.sf, this.db, this.pc, this.mw));
		
		JLabel score = new JLabel();
		score.setText("Score: " + this.sf.snake.score);
		score.setForeground(Color.WHITE);
		score.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		score.setBounds(61, 11, 151, 38);
		getContentPane().add(score);
		
		JLabel highscore = new JLabel();
		highscore.setText("Highscore: " + this.pc.player.getHighscore());
		highscore.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		highscore.setForeground(Color.WHITE);
		highscore.setBounds(301, 11, 182, 38);
		getContentPane().add(highscore);
		backToMenu.setIcon(new ImageIcon("assets/gameImages/backToMenuButton.png"));
		getContentPane().add(backToMenu);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("assets/gameImages/endImage.jpg"));
		lblNewLabel.setBounds(0, 0, 494, 141);
		getContentPane().add(lblNewLabel);


		this.setResizable(false);
		this.setSize(500, 170);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
	
}
