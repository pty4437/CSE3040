package JavaHomework;

class Shape{
	double getArea() {
		return 0;
	};
}

class Circle extends Shape{
	private double radius;
	
	public Circle(double radius) {
		this.radius = radius;
	}
	
	public double getArea() {
		return this.radius*this.radius*Math.PI;
	}
}

class Square extends Shape{
	private double d;
	
	public Square(double d) {
		this.d = d;
	}
	
	public double getArea() {
		return d*d;
	}
}

class Rectangle extends Shape{
	private double row,col;
	
	public Rectangle(double row,double col) {
		this.row = row;
		this.col = col;
	}
	
	public double getArea() {
		return row*col;
	}
}


public class Problem08 {

	public static void main(String[] args) {
		Shape[] arr = { new Circle(5.0), new Square(4.0), new Rectangle(3.0, 4.0), new Square(5.0)};	
		System.out.println("Total area of the shapes is: " + sumArea(arr));
	}

	private static double sumArea(Shape[] arr) {
		double sum = 0;
		
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i].getArea();
		}
		
		return sum;
	}

}
