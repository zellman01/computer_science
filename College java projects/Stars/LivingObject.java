import java.awt.Graphics2D;

public abstract class LivingObject {
	protected double xPos;
	protected double yPos;
	private double angle;
	private double angleSpeed;
	private double xAcceleration;
	private double yAcceleration;
	private double xVelocity;
	private double yVelocity;
	private double xSpeed;
	private double ySpeed;
	public static int timeScalar = 1;
	private int lifeRemaining;
	private LifeEventListener lel;
	
	public LivingObject(double xPos, double yPos, double angle, double angleSpeed, double xAcceleration, double yAcceleration, int lifeRemaining, LifeEventListener lel) {
		setupObject(xPos, yPos, angle, angleSpeed, xAcceleration, yAcceleration, lifeRemaining);
		this.lel = lel;
		lel.lifeOccured(new LifeEvent(this));
	}
	
	private void setupObject(double xPos, double yPos, double angle, double angleSpeed, double xAcceleration, double yAcceleration, int lifeRemaining) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.angle = angle;
		this.angleSpeed = angleSpeed;
		this.xAcceleration = xAcceleration;
		this.yAcceleration = yAcceleration;
		this.lifeRemaining = lifeRemaining;
		xSpeed = 0;
		ySpeed = 0;
		
	}
	
	public void updateLinearVelocity(int delta) {
		xSpeed += delta * xAcceleration;
		ySpeed += delta * yAcceleration;
	}
	
	public void updateAngularVelocity(int delta) {
		xAcceleration += delta * xVelocity;
		yAcceleration += delta * yVelocity;
	}
	
	// Delta is the timescalarmillis
	public void updateCurrentPosition(int delta) {
		xPos += delta * xSpeed;
		yPos += delta * ySpeed;
	}
	
	public void updateCurrentOrientation(int delta) {
		angle += delta * angleSpeed;
	}
	
	public void reflectOffVerticalWall() {
		xSpeed = -xSpeed;
		// Move X to be off of the wall
	}
	
	public void reflectOffHorizontalWall() {
		ySpeed = -ySpeed;
		// Move Y to be off of the wall
	}
	
	public void bounceOffFloor(double keepEnergyFactor) {
		yPos *= keepEnergyFactor;
	}
	
	public void addLifeEventListener(LifeEventListener e) {
		lel = e;
	}
	
	public void update() {
		updateCurrentOrientation(LivingObject.timeScalar);
		updateAngularVelocity(LivingObject.timeScalar);
		updateLinearVelocity(LivingObject.timeScalar);
		updateCurrentPosition(LivingObject.timeScalar);
		lifeRemaining -= LivingObject.timeScalar;
		if(lifeRemaining <= 0) {
			lel.deathOccured(new LifeEvent(this));
		}
		
	}
	
	public abstract void draw(Graphics2D g);
}