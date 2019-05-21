package ch04questions;

import java.awt.Image;
import java.awt.Graphics;

public class Coin {
	private Image heads;
	private Image tails;
	private int side = 1;
	
	public Coin(Image h, Image t) {
		heads = h;
		tails = t;
	}
	
	/**
	 * Flips this coin
	 */
	public void flip() {
		switch (side) {
		case 0:
			side = 1;
			break;
		case 1:
			side = 0;
			break;
		}
	}
	
	/**
	 * Draws the appropriate side of the coin
	 * centered at (x, y)
	 * @param x coordinate of the point
	 * @param y coordinate of the point
	 * @param g Graphics variable
	 */
	public void draw(Graphics g, int x, int y) {
		switch (side) {
		case 0:
			g.drawImage(tails, x-250, y-250, null);
			break;
		case 1:
			g.drawImage(heads, x-250, y-250, null);
			break;
		}
	}
}
