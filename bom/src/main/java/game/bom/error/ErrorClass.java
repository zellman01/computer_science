package game.bom.error;

/**
 * Interface for errors
 * @author zellma01
 * @version 0.1.0
 * @since 0.1.0
 */
public interface ErrorClass {
	public int errorCode = 0;
	public String errorDesc = "";
	
	public int errorNum();
	public String desc();
}
