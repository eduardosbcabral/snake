package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.PlayerController;
import database.Database;
import view.EndMenu;
import view.MenuWindow;
import view.SnakeFrame;

public class EndListener implements ActionListener{

	private SnakeFrame sf;
	private Database db;
	private PlayerController pc;
	private EndMenu em;
	private MenuWindow mw;
	
	public EndListener(EndMenu em, SnakeFrame sf, Database db, PlayerController pc, MenuWindow mw){
		this.sf = sf;
		this.db = db;
		this.pc = pc;
		this.em = em;
		this.mw = mw;
	}
	
	public void actionPerformed(ActionEvent e) {	
		
		if(e.getSource() == em.restart){
			this.em.dispose();
			this.sf.dispose();	
			this.sf = new SnakeFrame(this.db, this.pc, this.mw, this.mw.ml.barrier, this.mw.ml.speed);	
			sf.setVisible(true);
		}
		
		if(e.getSource() == em.exit){
			em.dispose();
			this.sf.dispose();
		}
		
		if(e.getSource() == em.backToMenu){
			this.sf.setVisible(false);
			this.em.dispose();
			this.mw.setVisible(true);
			this.sf.snake.restartSnake();
		}
			
	}
	
}
