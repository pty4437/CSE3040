package cse3040_5;
import java.util.Scanner;

public class Problem4 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String text;
		String str;
		int count = 0;
		int i = 1;
		
		System.out.print("Enter a text: ");
		text = scan.nextLine();
		
		while(true) {
			System.out.print("Enter a letter: ");
			str = scan.nextLine();
			
			if(str.length() == 0) {
				System.out.println("You must enter a string.");
				continue;
			}
			else
				break;
			
		}
		
		scan.close();
		
			
		for(i = 0; i < text.length() - str.length()+1; i++) {
			if(str.equals(text.substring(i,i+str.length()))) {
				count++;
			}
		}
		
		System.out.print("There are " + count + " instances of " + "\"" + str + "\"");
		
		
	}

}
