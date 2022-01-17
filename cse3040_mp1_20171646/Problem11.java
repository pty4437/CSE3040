package ParkTaeYoon;

class PalindromeChecker{
	static void check(String str) {
		int length = str.length();
		int i = 0;
		int j = length-1;
		
		if(length % 2 == 0) {		
			while(i < j) {						
				if(str.charAt(i) != str.charAt(j)) 
					break;
				
				i++; j--;
			}
			
			if(i > j) 
				System.out.println(str + " is a palindrome.");
			else
				System.out.println(str + " is not a palindrome.");
		}
		else {
			while(true) {
				if(str.charAt(i) != str.charAt(j) || i == j)
					break;
				
				i++; j--;
			}
			
			if(i == j) 
				System.out.println(str + " is a palindrome.");
			else
				System.out.println(str + " is not a palindrome.");
		}
	}
	
	static void check(int num) {
		int length = 0;
		int front, back;
		
		if(num == 0)
			System.out.println(num + " is a palindrome.");
		
		else {
			for(int i = 1;;i*=10) {
				if(num / i == 0)
					break;
				length++;
			}
			
			if(length == 1)
				System.out.println(num + " is a palindrome.");
			else {
				front = 10;
				back = (int)Math.pow(10, length);
				
				while(true) {
					if(num % front / (front/10) != num % back / (back/10) || front > back)
						break;
					if(front > back)
						break;
					front *= 10;
					back /= 10;
				}
				
				if(front > back)
					System.out.println(num + " is a palindrome.");
				else
					System.out.println(num + " is not a palindrome.");
			}
		}
	}
}

public class Problem11 {
	public static void main(String[] args) {
		PalindromeChecker.check("abcde");
		PalindromeChecker.check("abcba");
		PalindromeChecker.check(1234);
		PalindromeChecker.check(12321);
	}
}
