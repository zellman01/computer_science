package game.bom.error;

/*
Error code series:
1xx - Login failures (reserved)
2xx - Update errors
3xx - Database errors (reserved)
4xx - N/A
5xx - Card errors (reserved)
6xx - Deck errors (reserved)
7xx - N/A
8xx - N/A
900 - Unknown (reserved)
 */
/**
 * Error codes for all of Brawl of Minds
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public enum ErrorCodes implements ErrorClass {
	/**
	 * Unable to contact login database
	 */
	E100(100, "Login database not accessible"),
	/**
	 * Incorrect credentials
	 */
	E101(101, "Username/password not found"),
	/**
	 * Issue with the Result Set of a statement
	 */
	E200(200, "Result Set is unable to be used"),
	/**
	 * Card update failed for some reason
	 */
	E201(201, "Unable to update cards"),
	/**
	 * Database connection could not happen
	 */
	E300(300, "Database connection failed"),
	/**
	 * Card does not exist
	 */
	E500(500, "Card does not exist"),
	/**
	 * Card directory was not detected
	 */
	E501(501, "Card directory does not exist"),
	/**
	 * Selected deck no longer exists
	 */
	E600(600, "Selected deck does not exist"),
	/**
	 * There are too many cards in one deck
	 */
	E601(601, "Too many cards in your deck"),
	/**
	 * Unknown error, could be anything
	 */
	E900(900, "Unknown error");
	
	private int errorCode;
	private String errorDesc;
	
	private ErrorCodes(int error, String desc) {
		errorCode = error;
		errorDesc = desc;
	}

	@Override
	public int errorNum() {
		return errorCode;
	}

	@Override
	public String desc() {
		return errorDesc;
	}

	public String toString() {
		return "Error code: " + errorNum() + ". " + desc() + ".";
	}

}
