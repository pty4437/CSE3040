package MP2_Problem19;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

class BookInfo implements Comparable<BookInfo>{
	private int num;
	private String book;
	private String name;
	private String price;
	
	void putNum(int num) {
		this.num = num;
	}
	
	void putBook(String book) {
		this.book = book;
	}
	
	void putName(String name) {
		this.name = name;
	}
	
	void putPrice(String price) {
		this.price = price;
	}
	
	public String toString() {
		return "#" + Integer.toString(this.num) + " " + this.book + ", " + this.name + ", " + this.price;
	}
	
	public int compareTo(BookInfo b) {
		if(this.num > b.num)
			return -1;
		else if(this.num < b.num)
			return 1;
		else
			return 0;
	}
}

class BookReader{
	static ArrayList<BookInfo> readBooks(String address){
		URL url = null;
		BufferedReader input = null;
		String line = "";
		ArrayList<String> list = new ArrayList<>();
		ArrayList<BookInfo> res = new ArrayList<>();
		int n = 1;
		int status = 0;
		
		try {
			url = new URL(address);
			input = new BufferedReader(new InputStreamReader(url.openStream()));
			while((line=input.readLine()) != null) {
				if(line.trim().length() > 0) list.add(line);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		String book = null;
		String name = null;
		String price = null;
		
		for(int i = 0; i < list.size(); i++) {
			String l = list.get(i);
			if(status == 0) {
				if(l.contains("<img src=\"//prodimage.images-bn")) {
					int bBegin = l.indexOf("\"full-shadow\" alt=\"") + "\"full-shadow\" alt=\"".length();
					int bEnd = l.indexOf("\" />");
	
					book = l.substring(bBegin, bEnd);
				
					status = 1;
				}
				
			}
			
			else if(status == 1) {
				if(l.contains("<div class=\"product-shelf-author contributors\">")) {
			
					int nBegin = l.indexOf("\">") + "\">".length();
					int nEnd = l.indexOf("</a>");

					
					name = l.substring(nBegin, nEnd);
					
					nBegin = name.indexOf("\">") + "\">".length();
					name = name.substring(nBegin);
	
					status = 2;
				}
			}
			
			else if(status == 2) {
				if(l.contains("<span class=\"sr-only\">Current price is")) {
					BookInfo b = new BookInfo();
					int pBegin = l.indexOf("Current price is ") + "Current price is ".length();
					int pEnd = l.indexOf(", Original");
					
					price = l.substring(pBegin, pEnd);

					b.putPrice(price);
					b.putBook(book);
					b.putName(name);
					b.putNum(n++);
	
					res.add(b);
					
					status = 0;
				}
			}
			
			}
		return res;
		}
		
		
}


public class Problem19 {
	public static void main(String[] args) {
		ArrayList<BookInfo> books;
		books = BookReader.readBooks("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		Collections.sort(books);
		for(int i=0; i<books.size(); i++) {
			BookInfo book = books.get(i);
			System.out.println(book);
		}
				
	}
}
