package ch07problems;

/**
 * @author Zach Wellman
 * Date: 10/2/2018
 */
public class LinesAtt2 {
	private int a = 4 /*x*/, b = 4 /*y*/, t = 1 /*Total*/;
	int x, y, total;
	public LinesAtt2() {
		x = a;
		y = b;
		total = t;
	}
	public int obtainTotal() {return this.total;}
	public static void main(String [] args) {
		LinesAtt2 Line = new LinesAtt2();
		/*if (1 < Line.x || Line.x < Line.y || Line.y < 100) {
			System.exit(1);
		}*/
		/*if (Line.x == Line.y) {
			System.out.println(Line.x);
			System.exit(0);
		}*/
		for (int x = Line.x; x <= 2; x++) {
			for (int y = Line.y; y <= x ; y++) {
				if (x == y) {
					//Line.total++;
					continue;
				}
				
			}
		}
		if (Line.x != Line.y) 
			Line.total++;
		System.out.println(Line.obtainTotal());
	}
}
// If (x, y) is the same, it is just what x is (Not always)