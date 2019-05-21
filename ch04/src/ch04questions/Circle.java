package ch04questions;

public class Circle {
	public static int radius;
	public static double Area;
	
	public Circle (int r) {
		Area = Math.PI*(r*r);
	}
	
	public static double getArea() {
		return Math.PI*(radius*radius);
	}
	
	public static double getRadius(int r) {
		return Math.PI*(r*r);
	}
}