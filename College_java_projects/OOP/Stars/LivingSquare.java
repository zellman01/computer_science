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
	
	public LivingSquare(double xPos, double yPos, double angle, double angleSpeed, double xAcceleration, double yAcceleration, double radius, int lifeRemaining, LifeEventListener lel, DrawPanelSize dps, boolean idle) {
		super(xPos, yPos, angle, angleSpeed, xAcceleration, yAcceleration, radius, lifeRemaining, lel, dps, idle);
		// TODO: Add radius. Assume 3 always for now
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
		if (lifeRemaining < 10) {
			setAlpha(alpha-lifeRemaining);
		}
		g.setColor(new Color(red, blue, green, alpha));
		g.drawRect((int)(xPos)-(int)(radius), (int)(yPos)-(int)(radius), (int)(radius)*2, (int)(radius)*2);
	}
	
	public static LivingSquare getRandom(LifeEventListener lel, Random rand, DrawPanelSize dps, int lifeRemaining, boolean idle) {
		int radius1 = rand.nextInt(6)+2;
		int width = (int)dps.getPanelWidth();
		int height = (int)dps.getPanelHeight();
		LivingSquare ls = new LivingSquare(rand.nextInt(width-radius1)+radius1+1, rand.nextInt(height-radius1)+radius1+1, rand.nextInt(5)+1, rand.nextInt(5)+1, rand.nextInt(31 + 11) - 11, rand.nextInt(31 + 11) - 11, radius1, lifeRemaining, lel, dps, idle);
		ls.setRed(rand.nextInt(256));
		ls.setBlue(rand.nextInt(256));
		ls.setGreen(rand.nextInt(256));
		ls.setAlpha(255);
		return ls;
	}
}