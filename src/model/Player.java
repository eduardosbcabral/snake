package model;

public class Player {
	
	private String name;
	private String password;
	private int highscore;
	private int snakeColor;

	public Player(String name, String password){
		this.setName(name);
		this.setPassword(password);
		this.setSnakeColor(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHighscore() {
		return highscore;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSnakeColor() {
		return snakeColor;
	}

	public void setSnakeColor(int snakeColor) {
		this.snakeColor = snakeColor;
	}

	public String toString(){
		return this.name + ":" + this.highscore;
	}
	
	public String toFileString(){
		return this.name + ":" + this.password + ":" + this.highscore + ":" + this.snakeColor;
	}
	
}
