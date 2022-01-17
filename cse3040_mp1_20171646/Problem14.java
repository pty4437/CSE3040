package ParkTaeYoon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class FruitBox<T>{
	private ArrayList<Fruit> list = new ArrayList<Fruit>();

	void add(Fruit item){
		list.add(item);
	}
	
	int getNumItems() {
		return list.size();
	}
	
	String getMaxItem() {
		int idx = 0;
		double maxPrice = list.get(0).getPrice();
		
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getPrice() > maxPrice) {
				maxPrice = list.get(i).getPrice();
				idx = i;
			}
		}
		
		
		return list.get(idx).getName();	
	}
	
	double getMaxPrice() {
		int idx = 0;
		double maxPrice = list.get(0).getPrice();
		
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getPrice() > maxPrice) {
				maxPrice = list.get(i).getPrice();
				idx = i;
			}
		}
		
		
		return list.get(idx).getPrice();	
	}
	
	String getMinItem() {
		int idx = 0;
		double minPrice = list.get(0).getPrice();
		
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getPrice() < minPrice) {
				minPrice = list.get(i).getPrice();
				idx = i;
			}
		}
		
		
		return list.get(idx).getName();
	}
	
	double getMinPrice() {
		int idx = 0;
		double minPrice = list.get(0).getPrice();
		
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getPrice() < minPrice) {
				minPrice = list.get(i).getPrice();
				idx = i;
			}
		}
		
		
		return list.get(idx).getPrice();
	}
	
	double getAvgPrice() {
		double sum = 0;
		
		for(int i = 0; i < list.size(); i++) {
			sum += list.get(i).getPrice();
		}
		
		return sum / list.size();
	}
}

class Fruit{
	private String name;
	private double price;
	
	Fruit(String name, double price) {
		System.out.println(name + " " + price);
		
		this.name = name;
		this.price = price;
	}
	
	String getName() {
		return this.name;
	}
	
	double getPrice() {
		return this.price;
	}
	
}

class ItemReader{
	static boolean fileToBox(String str, FruitBox box) {
		String line;
		String name;
		double price;
		int index;
		File file = new File(str);
		
		if(file.exists()) {
			try {
				FileReader rw = new FileReader(str);
				BufferedReader br = new BufferedReader(rw);
				
				for(int i = 1; (line = br.readLine()) != null; i++) {
					index = line.indexOf(" ");
					name = line.substring(0,index);
					price = Double.parseDouble(line.substring(index+1));
					box.add(new Fruit(name,price));
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
			return true;
		}
		else {
			System.out.println("Input file not found.");
			return false;
		}
	
	}
}

public class Problem14 {
	public static void main(String[] args) {
		FruitBox<Fruit> box = new FruitBox<>();
		boolean rv = ItemReader.fileToBox("input_prob14.txt", box);
		if(rv == false) return;
		//box.add(new Fruit("orange", 9.99));
		System.out.println("----------------");
		System.out.println(" Summary");
		System.out.println("----------------");
		System.out.println("number of items: " + box.getNumItems());
		System.out.println("most expensive item: " + box.getMaxItem() + " (" + box.getMaxPrice() + ")");
		System.out.println("cheapest item: " + box.getMinItem() + " (" + box.getMinPrice() + ")");
		System.out.printf("average price of items: %.2f", box.getAvgPrice());}
}


