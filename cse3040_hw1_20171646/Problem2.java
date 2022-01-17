package cse3040_5;
import java.util.Scanner;
import java.util.Random;

public class Problem2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		Random rand = new Random();
		int count = 1;
		int num = rand.nextInt(100) + 1;
		int high = 100;
		int low = 1;
		int ans;
		
		while(count > 0){
			System.out.print("[" + count + "]" + " Guess a number (" + low + "-" + high + "): ");
			ans = scan.nextInt();
			
			if(ans < low || ans > high) {
				System.out.println("Not in range!");
				continue;
			}
			
			if(ans == num) {
				System.out.println("Correct! Number of guesses: " + count);
				break;
			}
			
			else if(ans < num) {
				System.out.println("Too small!!");
				low = ans + 1;
				count++;
			}
			
			else {
				System.out.println("Too large!!");
				high = ans - 1;
				count++;
			}
		}
		
		scan.close();
		
	}

}
