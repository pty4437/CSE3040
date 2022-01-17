package cse3040_5;
import java.util.Scanner;

public class Problem1 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String str;

		System.out.print("ASCII code teller. Enter a letter: ");
		
		str = scan.nextLine();
		
		if(str.length() == 0) {
			System.out.println("You must input a single uppercase or lowercase alphabet.");
			System.exit(0);
		}
		
		if(str.length() > 1)
			System.out.println("You must input a single uppercase or lowercase alphabet.");
		else if(str.charAt(0) == '\n' || str.charAt(0) == ' ')
			System.out.println("You must input a single uppercase or lowercase alphabet.");
		else if((int)str.charAt(0) < 65 || (int)str.charAt(0) > 122)
			System.out.println("You must input a single uppercase or lowercase alphabet.");
		else
			System.out.println("The ASCII code of " + str.charAt(0) + " is " + (int)str.charAt(0));
		
		scan.close();
	}

	
}
