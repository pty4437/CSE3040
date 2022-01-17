package ParkTaeYoon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Item{
	private String text = " ";
	private int num = 1;
	
	String getText() {
		return this.text;
	}
	
	int getNum() {
		return this.num;
	}
	
	void changeText(String str) {
		this.text = str;
	}
	
	void increaseNum() {
		this.num++;
	}
	
	public String toString() {
		String str;
		
		str = this.text + " " + this.num;
		return str;
	}
	
}

class MyFileReader{
	static boolean readDataFromFile(String fileName, ArrayList<Item> list) {
		String line;
		String name;
		int i,j,k;
		File file = new File(fileName);
		
		if(file.exists()) {
			try {
				FileReader rw = new FileReader(fileName);
				BufferedReader br = new BufferedReader(rw);
				
				for(i = 1; (line = br.readLine()) != null; i++) {
					String[] tmp = line.split(" ");
					
					for(j = 0; j < tmp.length; j++) {
						
						if(list.size() == 0) {
							list.add(new Item());
							list.get(list.size()-1).changeText(tmp[j].toLowerCase());
						}
						
						else {						
							for(k = 0; k < list.size(); k++) {
								if(tmp[j].toLowerCase().equals(list.get(k).getText())) {
									list.get(k).increaseNum();
									break;
								}
							}
						
							if(k == list.size() && !tmp[j].equals(list.get(k-1).getText())) {
								list.add(new Item());
								list.get(list.size()-1).changeText(tmp[j].toLowerCase());
							}
						}
					}
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}
}

public class Problem15 {
	public static void main(String[] args) {
		 ArrayList<Item> list = new ArrayList<>();
		 boolean rv = MyFileReader.readDataFromFile("input_prob15.txt", list);
		 if(rv == false) {
		 System.out.println("Input file not found.");
		 return;
		 }
		 for(Item it: list) System.out.println(it); 
	}
}
