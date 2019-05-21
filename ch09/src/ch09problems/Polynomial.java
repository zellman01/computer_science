package ch09problems;

/**
 * 
 * @author Zach Wellman
 * DateStarted: 3/31/2019
 * DateCompleted: 3/31/2019
 */
public class Polynomial {
	private double[] coefficients;
	public Polynomial (double[] a) {
		coefficients = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			coefficients[i] = a[i];
		}
	}
	
	public Polynomial(int degree) {
	    coefficients = new double[degree+1];
	    for (int i = 0; i <= degree; i++)
	        coefficients[i] = 0;
	}
	
	public double degree() {
		return this.coefficients.length-1;
	}
	
	public double getValue(double x) {
		double sum = this.coefficients[0];
		for (int i = 1; i<this.coefficients.length; i++) {
			sum += this.coefficients[i] * x;
		}
		return sum;
	}
	
	public double getValue2(double x) {
		double sum = this.coefficients[0];
		for (int i = 1; i<this.coefficients.length; i+=2) {
			//sum += this.coefficients[i] * x;
			sum += x * (this.coefficients[i] + x * (this.coefficients[i+1]));
		}
		return sum;
	}
	
	public static String superscript(String str) {
	    str = str.replaceAll("0", "⁰");
	    str = str.replaceAll("1", "¹");
	    str = str.replaceAll("2", "²");
	    str = str.replaceAll("3", "³");
	    str = str.replaceAll("4", "⁴");
	    str = str.replaceAll("5", "⁵");
	    str = str.replaceAll("6", "⁶");
	    str = str.replaceAll("7", "⁷");
	    str = str.replaceAll("8", "⁸");
	    str = str.replaceAll("9", "⁹");         
	    return str;
	}
	
	public String toString() {
		//return "";
		String returnString = Double.toString(this.coefficients[0]);
		for (int i = 1; i<this.coefficients.length; i++) {
			if (i==1)
				returnString += " + " + Double.toString(this.coefficients[i]) + "x";
			else
				returnString += " + " + Double.toString(this.coefficients[i]) + "x" + superscript(Integer.toString(i));
		}
		return returnString;
	}
	
	public double getCoefficient(int i) {
		return this.coefficients[i];
	}
	
	public void setCoefficient(int i, double value) {
	    coefficients[i] = value;
	}
	
	public Polynomial multiply(Polynomial other) {
		int n = (int)degree();
	    int m = (int)other.degree();
	    Polynomial result = new Polynomial(Polynomial.max(n, m));
	    int i;

	        for (i = 0; i <= Polynomial.min(n, m); i++) 
	            result.setCoefficient(i, coefficients[i] * other.getCoefficient(i));
	        if (i <= n) {
	            //we have to copy the remaining coefficients from this object
	            for ( ; i <= n; i++) 
	                result.setCoefficient(i, coefficients[i]);
	        } else {
	            // we have to copy the remaining coefficients from p
	            for ( ; i <= m; i++) 
	                result.setCoefficient(i, other.getCoefficient(i));
	        }
	    return result;
	}
	
	private static int max (int n, int m) {
	    if (n > m)
	        return n;
	    return m;
	}

	private static int min (int n, int m) {
	    if (n > m)
	        return m;
	    return n;
	}
	
	public static void main(String[] args) {
		double[] thing = {4.0, 8.1, 9.3};
		Polynomial poly1 = new Polynomial(thing);
		System.out.println(poly1);
		System.out.println(poly1.getValue(3));
		System.out.println(poly1.getValue2(3));
		System.out.println(poly1.degree());
		System.out.println();
		double[] thing2 = {4.8, 7.2, 3.3};
		Polynomial poly2 = new Polynomial(thing2);
		System.out.println(poly2);
		System.out.println(poly2.getValue(3));
		System.out.println(poly2.getValue2(3));
		System.out.println(poly2.degree());
		System.out.println();
		
		System.out.println(poly1.multiply(poly2));
	}
}
