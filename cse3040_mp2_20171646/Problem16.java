package MP2_Problem16;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

class Element implements Comparable<Element>{
	private String name;
	private double price;
	
	void putName(String name) {
		this.name = name;
	}
	
	void putPrice(double price) {
		this.price = price;
	}
	
	String getName() {
		return this.name;
	}
	
	double getPrice() {
		return this.price;
	}
	
	public int compareTo(Element e) {
		if(this.price > e.getPrice())
			return 1;
		else if(this.price < e.getPrice())
			return -1;
		else
			return this.name.compareTo(e.getName());
	}
	
	public String toString() {
		String str;
		
		str = this.name + " " + this.price;
		return str;
	}
}

class ElementReader{
	static ArrayList<Element> readElements(String fileName) {
		int i;
		String line;
		ArrayList<Element> list = new ArrayList<>();
		
		try {
			FileReader rw = new FileReader(fileName);
			BufferedReader br = new BufferedReader(rw);
			
			for(i = 1; (line = br.readLine()) != null; i++) {
				String[] tmp = line.split(" ");
				Element e = new Element();
				
				e.putName(tmp[0]);
				e.putPrice(Double.parseDouble(tmp[1]));
				
				list.add(e);
			}
		}catch(IOException e) {
			System.out.println("Input file not found.");
		}
		
		return list;
	}
}

public class Problem16 {
	public static void main(String[] args) {
		ArrayList<Element> list = ElementReader.readElements("input.txt");
		if(list == null) {
			System.out.println("Input file not found.");
			return;
		}
		Collections.sort(list);
		Iterator<Element> it = list.iterator();
		while(it.hasNext()) System.out.println(it.next());
	}
}
