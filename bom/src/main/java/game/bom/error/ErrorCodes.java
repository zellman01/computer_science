package game.bom.error;

/*
Error code series:
1xx - Login failures
2xx - Update errors
3xx - Database errors
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
	 * Account restrictions found
	 */
	E102(102, "Your account has restrictions placed on it."),
	/**
	 * Account temporarily banned
	 */
	E103(103, "Your account has been locked"),
	/**
	 * Account banned forever
	 */
	E104(104, "Account has been banned."),
	/**
	 * Issue with the Result Set of a statement
	 */
	E200(200, "Result Set is unable to be used"),
	/**
	 * Card update failed for some reason
	 */
	E201(201, "Unable to update cards"),
	/**
	 * Pack update failed
	 */
	E202(202, "Pack update failure"),
	/**
	 * Database connection could not happen
	 */
	E300(300, "Database connection failed"),
	/**
	 * Table not found
	 */
	E301(301, "Database table does not exist"),
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
	 * There are too many cards in your hand
	 */
	E602(602, "Too many cards in your hand to add anymore."),
	/**
	 * Card already exists in the pack
	 */
	E603(603, "Current card already exists in the card pack"),
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
