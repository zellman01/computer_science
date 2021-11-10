import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Random;

public class LivingSquare extends LivingObject {
	private double startingXPos;
	private double startingYPos;
	private int red;
	private int blue;
	private int green;
	private int alpha;
	
	public LivingSquare(double xPos, double yPos, double angle, double angleSpeed, double xAcceleration, double yAcceleration, double radius, int lifeRemaining, LifeEventListener lel, DrawPanelSize dps) {
		super(xPos, yPos, angle, angleSpeed, xAcceleration, yAcceleration, radius, lifeRemaining, lel, dps);
		startingXPos = xPos;
		startingYPos = yPos;
	}
	
	private void setRed(int color) {
		if (color < 0 || color > 255) return;
		red = color;
	}
	
	private void setBlue(int color) {
		if (color < 0 || color > 255) return;
		blue = color;
	}
	
	public void setGreen(int color) {
		if (color < 0 || color > 255) return;
		green = color;
	}
	
	private void setAlpha(int color) {
		if (color < 0) alpha = 0;
		else if (color > 255) alpha = 255;
		else alpha = color;
	}
	
	@Override
	public void draw(Graphics2D g) {
		int alpha = 255;
		if (lifeRemaining < 10) {
			setAlpha(alpha/9);
		}
		g.setColor(new Color(red, blue, green, 255));
		g.drawRect((int)(xPos)-(int)(radius), (int)(yPos)-(int)(radius), (int)(startingXPos)+3, (int)(startingYPos)+3);
	}
	
	public static LivingSquare getRandom(LifeEventListener lel, Random rand, DrawPanelSize dps) {
		// Do random later
		LivingSquare ls = new LivingSquare(rand.nextInt(100)+1, rand.nextInt(100)+1, rand.nextInt(5)+1, rand.nextInt(5)+1, rand.nextInt(5 + 3) - 3, rand.nextInt(5 + 3) - 3, rand.nextInt(5)+1, rand.nextInt(30)+30, lel, dps);
		ls.setRed(rand.nextInt(256));
		ls.setBlue(rand.nextInt(256));
		ls.setGreen(rand.nextInt(256));
		return ls;
	}
}