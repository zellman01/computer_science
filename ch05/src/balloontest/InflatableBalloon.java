package balloontest;

import java.awt.Color;

/**
 * This program represents an inflatable balloon
 * @author Zach Wellman
 */
public class InflatableBalloon extends Balloon {
	public InflatableBalloon() {
		super();
	}

	public InflatableBalloon(int x, int y, int r, Color c) {
		super(x, y, r, c);
	}

	public void inflate(int percentage) {
		int r = getRadius();
		double rCubed = Math.pow(r, 3);
		rCubed *= 0.01 * (100 + percentage);
		r = (int)(Math.pow(rCubed, 1.0/3.0) + 0.5);
		setRadius(r);
	}
}