package ParkTaeYoon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Text{
	private String str;
	
	boolean readTextFromFile(String str) {
		File file = new File(str);
		
		if(file.exists()) {
			this.str = str;
			return true;
		}
		else {
			System.out.println("Input file not found.");
			return false;
		}
	}
	
	int countChar(char c){
		int count = 0;
		String line;
		
		try {
			FileReader rw = new FileReader(this.str);
			BufferedReader br = new BufferedReader(rw);
			
			for(int i = 1; (line = br.readLine()) != null; i++) {
				for(int j = 0; j < line.length(); j++) {
					if(line.charAt(j) == Character.toUpperCase(c) || line.charAt(j) == Character.toLowerCase(c))
						count++;
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return count;
	}
}

public class Problem13 {
	public static void main(String[] args) {
		Text t = new Text();
		if(t.readTextFromFile("input_prob13.txt")) {
			for(char c = 'a'; c <= 'z'; c++) {
				System.out.println(c + ": " + t.countChar(c));
			}
		}
	}
}
