import java.awt.Graphics2D;

public class LivingSquare extends LivingObject {
	public LivingSquare(double xPos, double yPos, double angle, double angleSpeed, double xAcceleration, double yAcceleration, int lifeRemaining, LifeEventListener lel) {
		super(xPos, yPos, angle, angleSpeed, xAcceleration, yAcceleration, lifeRemaining, lel);
		// TODO: Add radius. Assume 3 always for now
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawRect((int)(xPos)-3, (int)(yPos)-3, (int)(xPos)+3, (int)(yPos)+3);
	}
	
	public static LivingSquare getRandom(LifeEventListener lel) {
		// Do random later
		return new LivingSquare(5, 5, 5, 5, 5, 5, 10, lel);
	}
}