package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

import control.PlayerController;
import database.Database;
import util.FileManager;
import view.LoginWindow;
import view.MenuWindow;
import view.RegisterWindow;
import view.SnakeFrame;

public class MenuListener implements ActionListener{

	private MenuWindow mw;
	private Database db;
	private PlayerController pc;
	private SnakeFrame sf;
	public boolean barrier = false;
	public int speed;
	
	File musicFile = null;
	AudioInputStream musicIn = null;
	Clip musicClip = null;

	public MenuListener(MenuWindow mw, Database db, PlayerController pc){
		this.mw = mw;
		this.db = db;
		this.pc = pc;	
		this.sf = new SnakeFrame(this.db, this.pc, this.mw, barrier, speed);	
	}

	public void actionPerformed(ActionEvent e) {

		try {
			musicFile = new File("assets/button.wav");
			musicIn = AudioSystem.getAudioInputStream(musicFile);
			musicClip = AudioSystem.getClip();
			musicClip.open(musicIn);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		if(e.getSource()== mw.btnBarrier){
			musicClip.start();
			this.barrier = true;
			this.sf = new SnakeFrame(this.db, this.pc, this.mw, barrier, this.speed);	
			mw.btnBarrier.setEnabled(false);
			mw.btnNoBarrier.setEnabled(true);
		}
		
		if(e.getSource()== mw.btnNoBarrier){
			musicClip.start();
			this.barrier = false;
			this.sf = new SnakeFrame(this.db, this.pc, this.mw, barrier, this.speed);	
			mw.btnNoBarrier.setEnabled(false);
			mw.btnBarrier.setEnabled(true);
		}
		
		if(e.getSource() == mw.btnPlay){
			musicClip.start();
			if(mw.loggedIn.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Please, register and login!");
			}else{
				mw.setVisible(false);
				this.sf = new SnakeFrame(this.db, this.pc, this.mw, this.mw.ml.barrier, this.speed);
				this.sf.setVisible(true);
			}
		}	

		if(e.getSource() == mw.btnRegister){
			musicClip.start();
			new RegisterWindow(this.db);
		}

		if(e.getSource() == mw.btnLogin){
			musicClip.start();
			new LoginWindow(this.db, this.mw, this.pc);
		}
		
		if(e.getSource() == mw.btnHighscore){
			musicClip.start();
			String name = "";
			for(int i=0; i<this.db.getPlayers().size(); i++){
				if(this.db.getPlayers().get(i).getHighscore()==new FileManager().sortScore().get(0))
					name = this.db.getPlayers().get(i).getName();
			}
			JOptionPane.showMessageDialog(null, "The highscore is from:\n" + name + " - " + new FileManager().sortScore().get(0));
		}
			
		if(e.getSource() == mw.btnExit){
			musicClip.start();
			this.mw.dispose();
		}

		if(e.getSource() == mw.snakeColor){
			musicClip.start();
			for(int i=0; i<this.db.getPlayers().size(); i++){
				if(pc.player.getName().equals(this.db.getPlayers().get(i).getName()))
					this.db.getPlayers().get(i).setSnakeColor(this.mw.snakeColor.getSelectedIndex());
			}
		}
			
		
		
		if(e.getSource() == mw.btnEasy){
			musicClip.start();
			this.mw.btnEasy.setEnabled(false);
			this.mw.btnMedium.setEnabled(true);
			this.mw.btnHard.setEnabled(true);
			this.speed = 0;
		}
		if(e.getSource() == mw.btnMedium){
			musicClip.start();
			this.mw.btnEasy.setEnabled(true);
			this.mw.btnMedium.setEnabled(false);
			this.mw.btnHard.setEnabled(true);
			this.speed = 1;
		}
		if(e.getSource() == mw.btnHard){
			musicClip.start();
			this.mw.btnEasy.setEnabled(true);
			this.mw.btnMedium.setEnabled(true);
			this.mw.btnHard.setEnabled(false);
			this.speed = 2;
		}
		
	}

}
