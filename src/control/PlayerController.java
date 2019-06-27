package control;

import java.util.ArrayList;

import database.Database;
import model.Player;
import util.FileManager;

public class PlayerController {

	public Player player;
	private Database db;
	public int score;

	public PlayerController(Database database){
		this.db = database;
	}

	public void registerPlayer(String name, String password) {
		Player p = new Player(name, password);
		FileManager fw = new FileManager();
		fw.write(p.toFileString(), "Players.txt");
		this.db.addPlayer(p);
	}

	public boolean validate(String name, String password){
		ArrayList<Player> players = this.db.getPlayers();

		for(Player player : players){
			if(player.getName().equals(name) && player.getPassword().equals(password)){
				this.player = player;
				return true;		
			}
		}

		return false;
	}
	
	public void setHighscore(){
		FileManager fm = new FileManager();
		fm.write(this.player.toString(), "Highscore.txt");
	}
	
	

}
