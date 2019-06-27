package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawImages {
	
	
	public BufferedImage loadHead(int direction, int color){
		BufferedImage snakeHead = null;
		
		try{
			if(color==0){
				if(direction == Direction.north)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGreen90.png"));
				else if(direction == Direction.south)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGreen270.png"));
				else if(direction == Direction.west)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGreen180.png"));
				else if(direction == Direction.east)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGreen0.png"));
				else if(direction == Direction.noDirection)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGreen270.png"));
			}
			
			if(color==1){
				if(direction == Direction.north)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadRed90.png"));
				else if(direction == Direction.south)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadRed270.png"));
				else if(direction == Direction.west)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadRed180.png"));
				else if(direction == Direction.east)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadRed0.png"));
				else if(direction == Direction.noDirection)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadRed270.png"));
			}
			
			if(color==2){
				if(direction == Direction.north)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadBlue90.png"));
				else if(direction == Direction.south)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadBlue270.png"));
				else if(direction == Direction.west)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadBlue180.png"));
				else if(direction == Direction.east)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadBlue0.png"));
				else if(direction == Direction.noDirection)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadBlue270.png"));
			}
			
			if(color==3){
				if(direction == Direction.north)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadPink90.png"));
				else if(direction == Direction.south)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadPink270.png"));
				else if(direction == Direction.west)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadPink180.png"));
				else if(direction == Direction.east)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadPink0.png"));
				else if(direction == Direction.noDirection)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadPink270.png"));
			}
			
			if(color==4){
				if(direction == Direction.north)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGray90.png"));
				else if(direction == Direction.south)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGray270.png"));
				else if(direction == Direction.west)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGray180.png"));
				else if(direction == Direction.east)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGray0.png"));
				else if(direction == Direction.noDirection)
					snakeHead = ImageIO.read(new File("assets/SnakeImages/snakeHeadGray270.png"));
			}

		}catch(IOException e){
			
		}
		
		return snakeHead;
	}

	public BufferedImage loadBody(int color){
		BufferedImage snakeBody = null;
		
		try{		
			if(color==0)
				snakeBody = ImageIO.read(new File("assets/SnakeImages/snakeBodyGreen.png"));
			if(color==1)
				snakeBody = ImageIO.read(new File("assets/SnakeImages/snakeBodyRed.png"));
			if(color==2)
				snakeBody = ImageIO.read(new File("assets/SnakeImages/snakeBodyBlue.png"));
			if(color==3)
				snakeBody = ImageIO.read(new File("assets/SnakeImages/snakeBodyPink.png"));
			if(color==4)
				snakeBody = ImageIO.read(new File("assets/SnakeImages/snakeBodyGray.png"));
		}catch(IOException e){
			
		}
		
		return snakeBody;
	}
	
	public BufferedImage loadFruit(int number){
		BufferedImage fruitImage = null;
		
		try{
			if(number==0)
				fruitImage = ImageIO.read(new File("assets/FruitImages/apple.png"));
			if(number==1)
				fruitImage = ImageIO.read(new File("assets/FruitImages/orange.png"));
			if(number==2)
				fruitImage = ImageIO.read(new File("assets/FruitImages/tomato.png"));
			if(number==3)
				fruitImage = ImageIO.read(new File("assets/FruitImages/goldenFruit.png"));
		}catch (IOException ex){
			ex.printStackTrace();
		}
		
		return fruitImage;
	}

}
