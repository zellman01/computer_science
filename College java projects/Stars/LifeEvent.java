public class LifeEvent {
	private LivingObject obj;
	private boolean isDeath;
	
	public LifeEvent(LivingObject obj, boolean isDeath) {
		this.obj = obj;
		this.isDeath = isDeath;
	}
	
	public boolean isDeathEvent() {
		return isDeath;
	}
	
	public LivingObject getSource() {
		return obj;
	}
}