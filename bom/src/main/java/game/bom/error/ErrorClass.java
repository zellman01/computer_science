package game.bom.error;

public interface ErrorClass {
	public int errorCode = 0;
	public String errorDesc = "";
	
	public int errorNum();
	public String desc();
}
