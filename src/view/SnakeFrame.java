package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import control.PlayerController;
import control.SnakeCanvas;
import database.Database;

public class SnakeFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public JLabel score;
	public JLabel name;

	public SnakeCanvas snake;
	
	private Database db;
	private PlayerController pc;
	public MenuWindow mw;
	private boolean barrier;
	private int speed;

	public SnakeFrame(Database db, PlayerController pc, MenuWindow mw, boolean barrier, int speed){
		this.db = db;
		this.pc = pc;
		this.mw = mw;
		this.barrier = barrier;
		this.speed = speed;
		
		setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		ImageIcon icon = new ImageIcon("assets/GameImages/iconGame.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Snake Game");

		snake = new SnakeCanvas();
		snake.setBarrierMode(this.barrier);
		snake.setMode(this.speed);
		snake.setJframe(this);
		snake.setDatabase(this.db);
		snake.setPlayerController(this.pc);
		snake.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		snake.setBounds(35, 29, 765, 525);
		snake.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		snake.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(snake);	
		snake.setFocusable(true);

		JLabel snakeTitle = new JLabel();
		snakeTitle.setBounds(285, 572, 256, 63);
		snakeTitle.setIcon(new ImageIcon("assets/GameImages/SnakeTitle.png"));
		getContentPane().add(snakeTitle);
		
		name = new JLabel();
		name.setText("Account: ");
		name.setFont(new Font("Showcard Gothic", Font.PLAIN, 22));
		name.setBounds(570, 585, 248, 48);
		name.setForeground(new Color(255, 255, 255));
		getContentPane().add(name);

		score = new JLabel("Score: 0");
		score.setFont(new Font("Showcard Gothic", Font.PLAIN, 33));
		score.setForeground(new Color(255, 255, 255));
		score.setBounds(49, 580, 250, 55);
		getContentPane().add(score);

		JLabel background = new JLabel();
		background.setBounds(0, 0, 828, 646);
		background.setIcon(new ImageIcon("assets/GameImages/backgroundLabel.jpg"));
		getContentPane().add(background);

		this.setResizable(false);
		//this.setVisible(true);
		this.setSize(834, 675);
		this.setLocationRelativeTo(null);

	} 

	public void updateScore(){
		this.score.setText("Score: " + this.snake.score);
	}
	
	public void updateName(){
		this.name.setText("Account: " + this.pc.player.getName());
	}
	
	public int getScore(){
		return this.snake.score;
	}

}
