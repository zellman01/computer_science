public class LifeEvent {
	private LivingObject obj;
	
	public LifeEvent(LivingObject obj) {
		this.obj = obj;
	}
	
	public LivingObject getSource() {
		return obj;
	}
}