package ParkTaeYoon;

class SubsequenceChecker{
	static void check(String str1, String str2) {
		int lengthStr1 = str1.length();
		int lengthStr2 = str2.length();
		int[] arr = new int[lengthStr2];
		int j = 0;
		
		for(int i = 0; i < lengthStr1; i++) {
			if(str1.charAt(i) == str2.charAt(j)) {
				arr[j] = i;
				j++;
			}
			
			if(j == lengthStr2)
				break;
		}
		
		if(j != lengthStr2)
			System.out.println(str2 + " is not a subsequence of " + str1);
		else {
			System.out.println(str2 + " is a subsequence of " + str1);
			for(int i = 0; i < lengthStr2; i++)
				System.out.print(arr[i] + " ");
			System.out.println();
		}
	}
}

public class Problem12 {
	public static void main(String[] args) {
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "pads");
		SubsequenceChecker.check("supercalifragilisticexpialidocious", "padsx");
	}
}
