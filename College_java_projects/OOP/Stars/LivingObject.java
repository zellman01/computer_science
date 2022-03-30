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
	protected double radius;
	public static int timeScalar = 1;
	protected int lifeRemaining;
	private LifeEventListener lel;
	private DrawPanelSize dps;
	private boolean idle;
	
	public LivingObject(double xPos, double yPos, double angle, double angleSpeed, double xAcceleration, double yAcceleration, double radius, int lifeRemaining, LifeEventListener lel, DrawPanelSize dps, boolean idle) {
		setupObject(xPos, yPos, angle, angleSpeed, xAcceleration, yAcceleration, radius, lifeRemaining, idle);
		this.lel = lel;
		this.dps = dps;
		lel.lifeOccured(new LifeEvent(this));
		idle = false;
	}
	
	private void setupObject(double xPos, double yPos, double angle, double angleSpeed, double xAcceleration, double yAcceleration, double radius, int lifeRemaining, boolean idle) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.angle = angle;
		this.angleSpeed = angleSpeed;
		this.xAcceleration = xAcceleration;
		this.yAcceleration = yAcceleration;
		this.radius = radius;
		this.lifeRemaining = lifeRemaining;
		this.idle = idle;
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
	
	public void updateMortality(int delta) {
		lifeRemaining -= delta;
		if (lifeRemaining <= 0) lel.deathOccured(new LifeEvent(this));
	}
	
	public void update() {
		updateMortality(LivingObject.timeScalar);
		if (idle) return;
		if (xPos - radius <= 0 || xPos + radius >= dps.getPanelWidth()) {
			reflectOffVerticalWall();
		}
		
		if (yPos - radius <= 0 || yPos + radius >= dps.getPanelHeight()) {
			reflectOffHorizontalWall();
		}
		updateCurrentOrientation(LivingObject.timeScalar);
		updateAngularVelocity(LivingObject.timeScalar);
		updateLinearVelocity(LivingObject.timeScalar);
		updateCurrentPosition(LivingObject.timeScalar);
	}
	
	public void changeIdleState(boolean idle) {
		this.idle = idle;
	}
	
	public abstract void draw(Graphics2D g);
}