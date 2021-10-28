public abstract class LivingObject {
	private int xPos;
	private int yPos;
	private int angle;
	private int xAcceleration;
	private int yAcceleration;
	private int xVelocity;
	private int yVelocity;
	public static timeScalar;
	private int lifeRemaining;
	
	public LivingObject() {
		
	}
	
	public void updateLinearVelocity(int millis) {
		
	}
	
	public void updateAngularVelocity(int millis) {
		
	}
	
	public void updateCurrentPosition() {
		
	}
	
	public void updateCurrentOrientation() {
		
	}
	
	public void reflectOffVerticalWall() {
		
	}
	
	public void reflectOffHorizontalWall() {
		
	}
	
	public void bounceOffFloor(double keepEnergyFactor) {
		
	}
	
	public void addLifeEventListener(LifeEvent e) {
		
	}
	
	public void update() {
		
	}
	
	public abstract void draw(Graphics2D g);
}