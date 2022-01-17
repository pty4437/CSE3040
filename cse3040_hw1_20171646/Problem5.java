package cse3040_5;
import java.util.Scanner;

public class Problem5 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int[] score = new int[5];
		int first = 0, second = 0;
		int tmp;
		int i;
		
		System.out.println("Enter exam scores of each student.");
		
		for(i = 0; i < 5; i++) {
			System.out.print("Score of student " + (i+1) + ": ");
			score[i] = scan.nextInt();
		}
		
		tmp = score[0];
		
		for(i = 1; i < 5; i++) {
			if(tmp < score[i]) {
				tmp = score[i];
				first = i;
			}
		}
		
		
		System.out.println("The 1st place is student " + (first+1) + " with " + tmp + " points.");
		score[first] = -1;
		tmp = score[0];
		
		for(i = 1; i < 5; i++) {
			if(tmp < score[i]) {
				tmp = score[i];
				second = i;
			}
		}
		
		System.out.println("The 2nd place is student " + (second+1) + " with " + tmp + " points.");
		
		scan.close();
		
	}

}
