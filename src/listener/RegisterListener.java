package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import control.PlayerController;
import database.Database;
import view.RegisterWindow;

public class RegisterListener implements ActionListener {
	
	private RegisterWindow rw;
	private Database db;
	
	public RegisterListener(RegisterWindow rw, Database db){
		this.rw = rw;
		this.db = db;
	}

	public void actionPerformed(ActionEvent e) {
		PlayerController pc = new PlayerController(this.db);
		pc.registerPlayer(rw.getName(), rw.getPassword());
		this.rw.setVisible(false);
	}
	
	

}
 