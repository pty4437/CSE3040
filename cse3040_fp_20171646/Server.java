package FP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class book implements Comparable<book>{
	private String bookName;
	private String author;
	private String borrower;
	
	void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	void setAuthor(String author) {
		this.author = author;
	}
	
	void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	
	String getBookName() {
		return this.bookName;
	}
	
	String getAuthor() {
		return this.author;
	}
	
	String getBorrower() {
		return this.borrower;
	}
	
	public int compareTo(book a) {
		int compare = this.bookName.toLowerCase().compareTo(a.bookName.toLowerCase());
		
		if(compare < 0)
			return -1;
		else if(compare > 0)
			return 1;
		else
			return 0;
	}
}



public class Server {
	ArrayList<book> bookList = new ArrayList<>();
	HashMap<String,DataOutputStream> clients;
	int portNum;

	Server() {
		int i;
		String line;
		
		try {
			FileReader rw = new FileReader("books.txt");
			BufferedReader br = new BufferedReader(rw);
			
			clients = new HashMap<>();
			Collections.synchronizedMap(clients);
				
			for(i = 1; (line = br.readLine()) != null; i++) {
				book tmp = new book();
				String[] tmpStr = line.split("\t");
				
				tmp.setBookName(tmpStr[0]);
				tmp.setAuthor(tmpStr[1]);
				tmp.setBorrower(tmpStr[2]);
				
				bookList.add(tmp);
							
			}
			
			br.close();
			rw.close();
			
		}catch(IOException e) {
				System.out.println("Input file not found.");
		}
		
	}
	
	public void start(int portNum) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		this.portNum = portNum;
					
		try {
			System.out.println("server has started.");
			serverSocket = new ServerSocket(this.portNum);
			while(true) {
			socket = serverSocket.accept();			
			ServerReceiver thread = new ServerReceiver(socket);
			thread.start();
			}
					
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		
		if(args.length != 1) {
			System.out.println("Please give the port number as an argument.");
			System.exit(0);
		}
		
		new Server().start(Integer.parseInt(args[0]));
	}
	
	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;
		
		ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch(IOException e) {}
		}
		
		public void run() {
			String book = "";
			String author = "";
			String borrower = "";
			String tmpString = "";
			String name =""; 
			try {
				name = in.readUTF();
				clients.put(name, out);
				while(true) {
					
				tmpString = in.readUTF();	
					
				if(tmpString.equals("add")) {
					int addDecide = 1;
					book tmp;
					book = in.readUTF();
					author = in.readUTF();
					
					if(book.replaceAll(" ","").length() != 0 && author.replaceAll(" ","").length() != 0) {
						for(int i = 0; i < bookList.size(); i++) {
							if(bookList.get(i).getBookName().equalsIgnoreCase(book)) {
								addDecide = 0;
							}
						}
						
						if(addDecide == 1) {
							tmp = new book();
							
							tmp.setBookName(book);
							tmp.setAuthor(author);
							tmp.setBorrower("-");
							
							bookList.add(tmp);

							Collections.sort(bookList);
							
							out.writeUTF("A new book added to the list.");
						
							FileWriter fw = new FileWriter("books.txt");
							for(int i = 0 ; i < bookList.size(); i++) {
								fw.write(bookList.get(i).getBookName() + "\t" + bookList.get(i).getAuthor() + "\t" + bookList.get(i).getBorrower() + "\n");
							}
							fw.close();
						}
					
						else {
							out.writeUTF("The book already exists in the list.");
							addDecide = 1;
						}
					}
					
					else {
						book = "";
						author = "";
					}

				}

				else if(tmpString.equals("borrow")) {
					int boDecide = 0;
					int index = 0;
					book = in.readUTF();
					borrower = in.readUTF();
						
					if(book.replaceAll(" ","").length() != 0) {
						for(int i = 0; i < bookList.size(); i++) {
							if(bookList.get(i).getBookName().equalsIgnoreCase(book)) {
								boDecide = 1;
								index = i;
								break;
							}
						}
							
						if(boDecide == 0) {
							out.writeUTF("The book is not available.");
						}
						else {
							if(bookList.get(index).getBorrower().equals("-")) {
								bookList.get(index).setBorrower(borrower);
								out.writeUTF("You borrowed a book. - " + book);
								boDecide = 0;
								
								FileWriter fw = new FileWriter("books.txt");
								for(int i = 0 ; i < bookList.size(); i++) {
									fw.write(bookList.get(i).getBookName() + "\t" + bookList.get(i).getAuthor() + "\t" + bookList.get(i).getBorrower() + "\n");
								}
								fw.close();
							}
							
							else {
								out.writeUTF("The book is not available.");
								boDecide = 0;
							}
						}
					}
						
				}
				
				else if(tmpString.equals("return")) {
					int reDecide = 0;
					int reIndex = 0;
					book = in.readUTF();
					
					if(book.replaceAll(" ","").length() != 0) {
						for(int i = 0 ; i < bookList.size(); i++) {
							if(bookList.get(i).getBookName().equalsIgnoreCase(book)) {
								reIndex = i;
								reDecide = 1;
								break;
							}
						}
						
						
						if(reDecide == 1) {
							if(bookList.get(reIndex).getBorrower().equals(name)){
								out.writeUTF("You returned a book. - " + bookList.get(reIndex).getBookName());
								bookList.get(reIndex).setBorrower("-");
								reDecide = 0;
								
								FileWriter fw = new FileWriter("books.txt");
								for(int i = 0 ; i < bookList.size(); i++) {
									fw.write(bookList.get(i).getBookName() + "\t" + bookList.get(i).getAuthor() + "\t" + bookList.get(i).getBorrower() + "\n");
								}
								fw.close();
							}
							
							else {
								out.writeUTF("You did not borrow the book.");
								reDecide = 0;
							}
						}
						
						else {
							out.writeUTF("You did not borrow the book.");
							reDecide = 0;
						}
					}
					
				}
				
				else if(tmpString.equals("info")) {
					ArrayList<book> tempList = new ArrayList<>();
					book infoBook;
					
					for(int i = 0 ; i < bookList.size(); i++) {
						if(bookList.get(i).getBorrower().equals(name)) {
							infoBook = new book();
							infoBook.setBookName(bookList.get(i).getBookName());
							infoBook.setAuthor(bookList.get(i).getAuthor());
							
							tempList.add(infoBook);
						}
					}
					
					out.writeUTF("You are currently borrowing " + tempList.size() +  " books:");
					out.writeUTF(Integer.toString(tempList.size()));
					for(int i = 0 ; i < tempList.size(); i++) {
						out.writeUTF((i + 1) +  "."  + tempList.get(i).getBookName() + ", " + tempList.get(i).getAuthor());
					}
									
				}
				
				else if(tmpString.equals("search")) {
					ArrayList<book> sList = new ArrayList<>();
					book sBook;
					String key;
					
					key = in.readUTF();
					
					if(key.length() > 2) {
						for(int i = 0 ; i < bookList.size(); i++) {
							if(bookList.get(i).getBookName().toLowerCase().contains(key.toLowerCase()) || bookList.get(i).getAuthor().toLowerCase().contains(key.toLowerCase())) {
								sBook = new book();
								sBook.setBookName(bookList.get(i).getBookName());
								sBook.setAuthor(bookList.get(i).getAuthor());
								
								sList.add(sBook);
							}
						}
						
						out.writeUTF("Your search matched " + sList.size() +  " results.");
						out.writeUTF(Integer.toString(sList.size()));
						for(int i = 0 ; i < sList.size(); i++) {
							out.writeUTF((i + 1) +  "."  + sList.get(i).getBookName() + ", " + sList.get(i).getAuthor());
						}
					}
				}
		
			}
			} catch(IOException e) {} 
			finally {
				clients.remove(name);
			}
		}
		
	}
}
	

