package game.bom.battle;

public enum TerminateNumber {
	normal(0, "Normal battle finish."),
	abnormal(5, "Abnormal finish");
	
	private int number;
	private String status;
	
	private TerminateNumber(int num, String stat) {
		number = num;
		status = stat;
	}
	
	@SuppressWarnings("unused")
	private String debug() {
		return number + ":" + status;
	}
	
	public String toString() {
		return status;
	}

}
