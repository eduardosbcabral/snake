package control;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import database.Database;
import util.FileManager;
import view.EndMenu;
import view.SnakeFrame;

public class SnakeCanvas extends JComponent implements Runnable, KeyListener {

	static final long serialVersionUID = 1L;

	private final int boxHeight = 15;
	private final int boxWidth = 15;
	private final int gridWidth = 50;
	private final int gridHeight = 35;

	private LinkedList<Point> snake;
	private Point fruit;

	private Thread runThread;

	private int direction = Direction.noDirection;
	public int score;
	private int snakeColor;
	private int mode;
	private int fruitColor;

	private boolean won = false;
	private boolean keyDirection = true;
	private boolean move = true;
	private boolean barrier;
	private boolean goldenFruit = false;
	private boolean eatFruit = false;
	private boolean eatGFruit = false;
	private boolean music = true;
	private boolean stopM = false;
	private boolean gameOverM = false;
	private boolean eatFruitM = false;

	private SnakeFrame sf;
	private Database db;
	private PlayerController pc;

	BufferedImage barrierImage1 = null;
	BufferedImage barrierImage2 = null;

	File musicFile = null;
	AudioInputStream musicIn = null;
	Clip musicClip = null;

	File gameOverFile = null;
	AudioInputStream gameOverIn = null;
	Clip gameOverClip = null;

	File eatFile = null;
	AudioInputStream eatIn = null;
	Clip eatClip = null;

	public void paintComponent(Graphics g){

		this.createSnake();

		if(runThread == null){
			this.addKeyListener(this);
			runThread = new Thread(this);
			runThread.start();
			this.sf.updateName();
		}

		DrawGrid(g);
		DrawSnake(g);
		DrawFruit(g);	


		keyDirection = true;
	}	

	private void createSnake(){
		if(snake == null){
			snake = new LinkedList<Point>();
			GenerateDefaultSnake();		
			PlaceFruit();
			for(int i=0; i<this.db.getPlayers().size(); i++){
				if(pc.player.getName().equals(this.db.getPlayers().get(i).getName())){
					this.snakeColor = this.db.getPlayers().get(i).getSnakeColor();
				}
			}
		}
	}


	private void GenerateDefaultSnake(){
		score = 0;
		snake.clear();

		snake.add(new Point (25,19));
		snake.add(new Point (25,18));
		snake.add(new Point (25,17));

	}

	private void Move(){



		Point head = snake.peekFirst();
		Point newPoint = head;

		if(!won){
			switch(direction){
			case Direction.north:
				newPoint = new Point(head.x, head.y - 1);
				break;
			case Direction.south:
				newPoint = new Point(head.x, head.y + 1);
				break;
			case Direction.west:
				newPoint = new Point(head.x - 1, head.y);
				break;
			case Direction.east:
				newPoint = new Point(head.x + 1, head.y);
				break;
			}

		}

		if(newPoint.equals(fruit)){
			score+=10;
			snake.push(newPoint);
			eatFruitM = true;

			if(eatGFruit){
				eatGFruit = false;
				goldenFruit = false;
			}

			if(goldenFruit){
				score+=30;
				eatGFruit = true;
				snake.push(newPoint);
				snake.push(newPoint);
			}

			eatFruit = true;
			PlaceFruit();
			sf.updateScore();
		}else if((newPoint.x<0 || newPoint.x>=gridWidth) || (newPoint.y<0 || newPoint.y>=gridHeight) || (snake.contains(newPoint) && direction != Direction.noDirection)||(snake.size() == (gridWidth * gridHeight))){
			won = true;
		}else if(barrier){
			if(((newPoint.x==11)&&(newPoint.y>=5 && newPoint.y<=28)||(newPoint.x==38)&&(newPoint.y>=5 && newPoint.y<=28)))
				won = true;		
		}

		if(won){	
			FileManager fm = new FileManager();		
			move = false;
			eatGFruit = false;
			stopM = true;
			gameOverM = true;
			this.endGameMenu();
			int high = 0;
			int j=0;

			for(int i=0; i<this.db.getPlayers().size(); i++){
				if(this.pc.player.getName()==this.db.getPlayers().get(i).getName()){
					high = this.db.getPlayers().get(i).getHighscore();
					j = i;
				}
			}

			if(this.score>high){
				this.db.getPlayers().get(j).setHighscore(this.score);
				this.db.getPlayers().get(j).setSnakeColor(this.snakeColor);

				JOptionPane.showMessageDialog(null, "You set a new highscore to your account! Congratulations!");

				fm.delete("Players.txt");
				fm.createFile("Players.txt");
				for(int i=0; i<this.db.getPlayers().size(); i++){
					fm.write(this.db.getPlayers().get(i).toFileString(), "Players.txt");
				}
			}


		}else{
			snake.remove(snake.peekLast());
			snake.push(newPoint);
		}

		try {
			if(music){

				musicFile = new File("assets/music.wav");
				musicIn = AudioSystem.getAudioInputStream(musicFile);
				musicClip = AudioSystem.getClip();
				musicClip.open(musicIn);
				FloatControl gainControl = (FloatControl) musicClip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-15.0f);
				musicClip.start();
				musicClip.loop(10);
				music = false;
			}

			if(stopM)
				musicClip.stop();

			if(gameOverM){
				gameOverFile = new File("assets/gameOver.wav");
				gameOverIn = AudioSystem.getAudioInputStream(gameOverFile);
				gameOverClip = AudioSystem.getClip();
				gameOverClip.open(gameOverIn);
				gameOverClip.start();
				gameOverM = false;
			}

			if(eatFruitM){
				eatFile = new File("assets/eatFruit.wav");
				eatIn = AudioSystem.getAudioInputStream(eatFile);
				eatClip = AudioSystem.getClip();
				eatClip.open(eatIn);
				eatClip.start();
				eatFruitM = false;
			}
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (LineUnavailableException e)  {

		}
	}


	private void DrawGrid(Graphics g){

		BufferedImage grid = null;


		try{
			grid = ImageIO.read(new File("assets/GameImages/gridGame.png"));
			barrierImage1 = ImageIO.read(new File("assets/GameImages/barrierGame.png"));
			barrierImage2 = ImageIO.read(new File("assets/GameImages/barrierGame.png"));
		}catch(IOException e){

		}

		g.drawImage(grid, 0, 0, null);

		if(barrier){
			g.drawImage(barrierImage1, 165, 75, null);
			g.drawImage(barrierImage2, 570, 75, null);
		}

	}

	private void DrawSnake(Graphics g){

		try{
			for(Point p : snake){

				if(!(snake.peekFirst()==p))
					g.drawImage(new DrawImages().loadBody(snakeColor), p.x * boxWidth, p.y * boxHeight, null);

				if(snake.peekFirst() == p)
					g.drawImage(new DrawImages().loadHead(direction, snakeColor), p.x * boxWidth, p.y * boxHeight, null);

			}
		}catch(ConcurrentModificationException c){

		}


	}

	private void DrawFruit(Graphics g){		

		if(eatFruit){

			if(fruitColor==2)
				fruitColor=0;

			if(fruitColor==1)
				fruitColor=2;

			if(fruitColor==0)
				fruitColor=1;

			if(fruitColor==3)
				fruitColor=0;

			if(score==90 || score==270 || score==450 || score==690 || score==890|| score==1050 || score==1400 || score==1400 || score==1800){
				fruitColor = 3;
				goldenFruit = true;	
			}

			eatFruit = false;
		}

		g.drawImage(new DrawImages().loadFruit(fruitColor), fruit.x * boxWidth, fruit.y * boxHeight, null);

	}

	private void PlaceFruit(){
		Random rand = new Random();
		Point randomPoint = new Point(rand.nextInt(gridWidth), rand.nextInt(gridHeight));

		if(barrier){
			while(snake.contains(randomPoint) || randomPoint.getX()==175 || randomPoint.getX()==570 || randomPoint.getY()==75){
				randomPoint = new Point(rand.nextInt(gridWidth), rand.nextInt(gridHeight));
			}
		}else{
			while(snake.contains(randomPoint)){
				randomPoint = new Point(rand.nextInt(gridWidth), rand.nextInt(gridHeight));
			}
		}


		fruit = randomPoint;
	}

	public void run() {
		while(true){	

			if(direction != Direction.noDirection)
				repaint();


			try{	

				if(!won){
					Move();

					if(eatGFruit){
						Thread.currentThread();
						Thread.sleep(29);
					}else{
						if(mode==0){
							Thread.sleep(90);
						}	

						if(mode==1){
							Thread.sleep(70);
						}
						if(mode==2){
							Thread.sleep(50);
						}

					}
				}else
					Thread.sleep(1500);

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void endGameMenu(){
		new EndMenu(this.sf, this.db, this.pc, this.sf.mw);
	}

	public void setPlayerController(PlayerController pc){
		this.pc = pc;
	}

	public void setDatabase(Database db){
		this.db = db;
	}

	public void setJframe(SnakeFrame sf){
		this.sf = sf;
	}

	public void setMode(int mode){
		this.mode = mode;
	}

	public void setBarrierMode(boolean value){
		this.barrier = value;
	}

	public void restartSnake(){
		this.snake = null;
		this.createSnake();

		won = false;
		move = true;
		keyDirection = true;
		direction = Direction.noDirection;
		score = 0;
		gameOverClip.stop();
		this.sf.updateScore();
	}

	public void keyPressed(KeyEvent e) {
		if(keyDirection && move){
			switch(e.getKeyCode()){
			case KeyEvent.VK_UP:
				if(direction != Direction.south && Direction.noDirection != direction){
					direction = Direction.north;
					keyDirection = false;
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != Direction.north){
					direction = Direction.south;
					keyDirection = false;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != Direction.west){
					direction = Direction.east;
					keyDirection = false;
				}
				break;
			case KeyEvent.VK_LEFT:
				if(direction != Direction.east){
					direction = Direction.west;
					keyDirection = false;
				}
				break;
			case KeyEvent.VK_ESCAPE:
				won = true;
				break;
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

}
