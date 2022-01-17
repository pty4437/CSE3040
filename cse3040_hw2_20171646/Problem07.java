package JavaHomework;

import java.util.Scanner;

interface IntSequenceStr{
	boolean hasNext();
	int next();
}

class BinarySequenceStr implements IntSequenceStr{
	private int num;
	private int maxCount;

	public BinarySequenceStr(int num) {
		this.num = num;
		maxExpon(this.num);
	}
	
	private void maxExpon(int num) {
		int count = 0;
		
		while(true) {
			if(Math.pow(2,count) >= num)
				break;
			count++;
		}
		
		this.maxCount = count;
	}
	
	
	private int calBinary(int num,int count) {
		
		if(Math.pow(2, count) <= num) {
			this.num -= Math.pow(2, count);
			this.maxCount--;
			return 1;
		}
		else{
			this.maxCount--;
			return 0;
		}
		
	}
	
	public boolean hasNext() {
		
		if(this.num < 0)
			return false;
		
		if(this.maxCount >= 0)
			return true;
		else
			return false;
	}
	
	public int next() {
		return calBinary(this.num,this.maxCount);
	}
}

public class Problem07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a positive integer: ");
		String str = in.nextLine();		
		int num = Integer.parseInt(str);		
		in.close();		
		System.out.println("Integer: " + num);		
		IntSequenceStr seq = new BinarySequenceStr(num);		
		System.out.print("Binary number: ");		
		while(seq.hasNext()) System.out.print(seq.next());		
		System.out.println(" ");
	}

}
