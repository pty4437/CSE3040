package JavaHomework;

class Points{
	private double[] arr;
	private double sum = 0;
	
	public Points(double[] arr) {
		this.arr = arr;
		calSum();
	}
	
	private void calSum() {
		for(int i = 0; i < this.arr.length; i++)
			this.sum += this.arr[i];
	}
	
	public String toString() {
		return "[sum of points: " + sum + "]";
	}
	
	public boolean equals(Points p2) {
		if(p2 == null)
			return false;
		
		if(this.sum == p2.sum)
			return true;
		else
			return false;
	}
}

public class Problem10 {

	public static void main(String[] args) {
		Points p1 = new Points(new double[] {1.0, 2.0, 3.0});
		Points p2 = new Points(new double[] {4.0, 5.0, 6.0});
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p1.equals(p2));
		Points p3 = new Points(new double[] {1.0, 4.0, 7.0});
		Points p4 = new Points(new double[] {3.0, 9.0});
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p3.equals(p4));
		Points p5 = new Points(new double[] {1.0, 2.0});
		Points p6 = null;
		System.out.println(p5);
		System.out.println(p6);
		System.out.println(p5.equals(p6));
	}

}
