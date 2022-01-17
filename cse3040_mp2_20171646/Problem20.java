package MP2_Problem20;

import java.util.ArrayList;
import java.util.Collections;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
	static ArrayList<BookInfo> readBooksJsoup(String address){
		Document doc = null;
		try {
			doc = Jsoup.connect(address).get();		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		ArrayList<String> bList = new ArrayList<>();
		ArrayList<String> pList = new ArrayList<>();
		ArrayList<String> nList = new ArrayList<>();
		ArrayList<BookInfo> res = new ArrayList<>();
		int n = 1;

		Elements book = doc.select("h3 a[href]");
		Elements name = doc.select("div.product-shelf-author.contributors");
	
		Elements price = doc.select("span.current a[href]");
	
		for(int i = 0; i < book.size(); i++)
			bList.add(book.eq(i).text());
		
		for(int i = 0; i < price.size(); i++)
			pList.add(price.eq(i).text());
		
		for(int i = 0; i < name.size(); i++) {
			if(name.eq(i).text().substring(0,3).equals("by ")) {
				String str = name.eq(i).text().substring(3);
				
				if(str.contains(", ")) {
					int idx = str.indexOf(",");
					str = str.substring(0,idx);
					nList.add(str);	
				}
				
				else
					nList.add(str);
			}
			else {
				String str = name.eq(i).text();
				if(str.contains(", ")) {
					int idx = str.indexOf(",");
					str = str.substring(0,idx);
					nList.add(str);	
				}
				else
					nList.add(str);
			}
		}
				
		for(int i = 0; i < name.size(); i++) {
			BookInfo b = new BookInfo();
			b.putBook(bList.get(i));
			b.putPrice(pList.get(i));
			b.putName(nList.get(i));
			b.putNum(n++);
			
			res.add(b);
		}
		
			
		return res;
		
	}
}

public class Problem20 {
	public static void main(String[] args) {
		ArrayList<BookInfo> books;
		books = BookReader.readBooksJsoup("https://www.barnesandnoble.com/b/books/_/N-1fZ29Z8q8");
		Collections.sort(books);
		for(int i=0; i<books.size(); i++) {
		BookInfo book = books.get(i);
		System.out.println(book);
		}
	}
}
