package JavaHomework;

interface IntSequence{
	int next();
	boolean hasNext();
}

class FibonacciSequence implements IntSequence{
	private int num = 0;
	private int count = 0;
	
	private int fibo(int num) {
		
		if(num == 0) {
			return 0;
		}
		
		else if(num == 1) {
			return 1;
		}
		
		else {
			return fibo(num - 1) + fibo(num - 2);
		}
	}
	
	public int next() {
		return fibo(this.count++);
	}
	
	public boolean hasNext() {
		if(this.num < 0)
			return false;
		else
			return true;
	}
}

public class Problem06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntSequence seq = new FibonacciSequence();		
		 for(int i=0; i<20; i++) {
			 if(seq.hasNext() == false) break;
			 System.out.print(seq.next() + " ");
		 }		 
		 System.out.println(" ");
	}

}
