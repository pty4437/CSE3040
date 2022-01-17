package FP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
	static class ClientSender extends Thread {
		Socket socket;
		DataOutputStream out;
		DataInputStream in;
		String name;
		Scanner scanner = new Scanner(System.in);
				
		ClientSender(Socket socket, String name) {
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				this.name = name;
			} catch(Exception e) {}
		}
		
		@SuppressWarnings("all")
		public void run() {
			try {
				String tmp;
				out.writeUTF(name);
				while (true) {
					System.out.print(this.name+">>");
					tmp = scanner.nextLine();
					
					if(tmp.equals("add")) {
						out.writeUTF(tmp);
						String addBook = "";
						String author = "";
						System.out.print("add-title> ");
						addBook = scanner.nextLine();
						if(addBook.replaceAll(" ","").length() != 0) {
							//out.writeUTF(addBook);
							System.out.print("add-author> ");
							author = scanner.nextLine();
							if(author.replaceAll(" ","").length() != 0) {
								out.writeUTF(addBook);
								out.writeUTF(author);	
								System.out.println(in.readUTF());
							}
							
							else {
								out.writeUTF(addBook);
								out.writeUTF(author);	
							}
							
						}
						
						else {
							out.writeUTF(addBook);
							out.writeUTF(author);
						}
						
					}
					
					else if(tmp.equals("borrow")) {
						out.writeUTF(tmp);
						String boBook;
						System.out.print("borrow-title> ");
						boBook = scanner.nextLine();
						if(boBook.replaceAll(" ","").length() != 0) {
							out.writeUTF(boBook);
							out.writeUTF(this.name);
							System.out.println(in.readUTF());
						}
						else {
							out.writeUTF(boBook);
							out.writeUTF(this.name);
						}
						
					}
					
					else if(tmp.equals("return")) {
						out.writeUTF(tmp);
						String reBook;
						System.out.print("return-title> ");
						reBook = scanner.nextLine();
						if(reBook.replaceAll(" ","").length() != 0) {
							out.writeUTF(reBook);
							System.out.println(in.readUTF());
						}
						else
							out.writeUTF(reBook);
					}
					
					else if(tmp.equals("info")) {
						out.writeUTF(tmp);
						int size;
						
						System.out.println(in.readUTF());
						
						size = Integer.parseInt(in.readUTF());
						
						for(int i = 0 ; i < size; i++) {
							System.out.println(in.readUTF());
						}
						
					}
					
					else if(tmp.equals("search")) {
						String searchKey;
						int sSize;
						
						System.out.print("search-string> ");
						searchKey = scanner.nextLine();
						if(searchKey.length() < 3 && searchKey.length() != 0) {
							System.out.println("Search string must be longer than 2 characters.");
							out.writeUTF(tmp);
							out.writeUTF(searchKey);
						}
						else if(searchKey.length() != 0){
							out.writeUTF(tmp);
							out.writeUTF(searchKey);
							
							System.out.println(in.readUTF());
							
							sSize = Integer.parseInt(in.readUTF());
							
							for(int i = 0 ; i< sSize; i++) {
								System.out.println(in.readUTF());
							}
						}
						else {
							out.writeUTF(tmp);
							out.writeUTF(searchKey);
						}
					}
					
					else {
						System.out.println("[available commands]");
						System.out.println("add: add a new book to the list of books.");
						System.out.println("borrow: borrow a book from the library.");
						System.out.println("return: return a book to the library.");
						System.out.println("info: show list of books I am currently borrowing.");
						System.out.println("search: search for books.");
					}
					
				}
				
			} catch(Exception e) {}
			
		}
		
	}
	
	public static void main(String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		if(args.length != 2) {		
			System.out.println("Please give the IP address and port number as arguments.");
			System.exit(0);	
		}
		
		try {					
			String serverIp = args[0];
			Socket socket = new Socket(serverIp, Integer.parseInt(args[1]));
			String name;
			
			while(true) {
				System.out.print("Enter userID>>");
				name = scan.nextLine();							
				
				if(name.matches("(.*)^[0-9a-z]*$(.*)") == true && name.length() != 0) {
					System.out.println("Hello " + name + "!");
					break;
				}
				else {
					System.out.println("UserID must be a single word with lowercase alphabets and numbers.");
				}
			}
			
			Thread sender = new Thread(new ClientSender(socket, name));
			sender.start();
		} catch(ConnectException ce) {
			System.out.println("Connection establishment failed.");
		} catch(Exception e) {}
	}
}
