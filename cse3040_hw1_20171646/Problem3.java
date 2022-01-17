package cse3040_5;
import java.util.Scanner;

public class Problem3 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String text;
		String letter;
		int count = 0;
		
		System.out.print("Enter a text: ");
		text = scan.nextLine();
		
		while(true) {
			System.out.print("Enter a letter: ");
			letter = scan.nextLine();
			
			if(letter.length() > 1 || letter.length() == 0) {
				System.out.println("You must enter a single letter.");
				continue;
			}
			else
				break;
			
		}
		
		
		for(int i = 0; i < text.length(); i++) {
			if(letter.charAt(0) == text.charAt(i))
				count++;
		}
		
		System.out.println("There are " + count + " " + letter.charAt(0) + "'s in the text.");
		
		scan.close();
		
		
	}

}
