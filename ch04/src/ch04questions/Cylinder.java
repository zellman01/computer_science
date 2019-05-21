package ch04questions;

public class Cylinder {
	public static int radius;
	public static double Area;
	
	public Cylinder (int r, int h) {
		Area = (Math.PI*(r*r))*h;
	}
	
	public static double getArea(int radius) {
		return radius; //(Math.PI*(radius*radius))*h;
	}
	
	public static double getVolume(int r, int h) {
		return (Math.PI*(r*r))*h;
	}
}