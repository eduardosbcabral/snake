package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import control.PlayerController;
import database.Database;
import util.FileManager;
import view.LoginWindow;
import view.MenuWindow;

public class LoginListener implements ActionListener{
	
	private LoginWindow lw;
	private Database db;
	private MenuWindow mw;
	private PlayerController pc;
	
	public LoginListener(LoginWindow lw, Database db, MenuWindow mw, PlayerController pc){
		this.lw = lw;
		this.db = db;
		this.mw = mw;
		this.pc = pc;
	}

	public void actionPerformed(ActionEvent e) {
		
		boolean isValid = pc.validate(lw.getName(), lw.getPassword());

		if(isValid){
			this.lw.setVisible(false);
			this.mw.updateAccount(pc.player.getName());
			this.mw.updateColorButton();
			this.mw.updateModeButton();
			
			
		}else{
			this.lw.reset();
			JOptionPane.showMessageDialog(null, "Invalid account!");
		}
		
	}
	
	

}
