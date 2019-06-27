package database;

import java.util.ArrayList;

import model.Player;
import util.FileManager;

public class Database {
	
	private ArrayList<Player> players;
	
	public Database(){
		this.players = new ArrayList<Player>();
		FileManager fm = new FileManager();
		fm.createFile("Players.txt");
		this.readPlayers();
	}
	
	public void addPlayer(Player p){
		this.players.add(p);
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}
	
	/*private void populateDatabase(){
		FileManager fw = new FileManager();
		
		Player p1 = new Player("Eduardo", "123");
		fw.write(p1.toFileString(), "Players.txt");
		Player p2 = new Player("Felipe", "123");
		fw.write(p2.toFileString(), "Players.txt");
	}*/
	
	private void readPlayers(){
		FileManager fw = new FileManager();
		
		ArrayList<String> players = fw.read("Players.txt");
		
		for(String pl : players){
			String[] a = pl.split(":");
			Player p = new Player(a[0], a[1]);
			p.setHighscore(Integer.parseInt(a[2]));
			p.setSnakeColor(Integer.parseInt(a[3]));
			
			this.addPlayer(p);
		}
	}


}
