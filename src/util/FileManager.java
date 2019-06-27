package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileManager {

	private PrintWriter writer;

	public void write(String text, String path){

		writer = null;

		try{
			File f = new File(path);
			f.createNewFile();
			writer = new PrintWriter(new FileWriter(path, true));
			writer.println(text);
			writer.close();
		}catch(FileNotFoundException e){
	
		}catch(UnsupportedEncodingException e){

		}catch(IOException e){

		}
	}

	public ArrayList<String> read(String path){
		ArrayList<String> lines = new ArrayList<String>();

		BufferedReader br = null;

		try{

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				lines.add(sCurrentLine);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}

		return lines;
	}
	
	public void delete(String path) {
		try {
			FileWriter a = new FileWriter(path, false);
	        PrintWriter b = new PrintWriter(a, false);
	        b.flush();
	        b.close();
	        a.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
	
	public void createFile(String path){
		File f = new File(path);
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public List<Integer> sortScore(){

		ArrayList<String> highscoreList = this.read("Players.txt");
		int a = 0;
		List<Integer> sort = new ArrayList<Integer>();
		
		for(int i=0; i<highscoreList.size(); i++){
			a = Integer.parseInt(highscoreList.get(i).split(":")[2]);
			sort.add(a);
		}

		Collections.sort(sort);
		Collections.reverse(sort);
		
		return sort;
	}

}
