package control;

import database.Database;
import view.MenuWindow;

public class ControlManager {
	
	private Database db;
	private PlayerController pc;
	
	public ControlManager(){
		this.db = new Database();
		this.pc = new PlayerController(this.db);
		start();
	}
	
	private void start(){
		new MenuWindow(this.db, this.pc);
	}	
	
}
