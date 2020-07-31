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
	E200(200, "Result Set is unable to be used"),
	E201(201, "Unable to update cards"),
	E300(300, "Database connection failed"),
	E500(500, "Card does not exist"),
	E501(501, "Card directory does not exist"),
	E600(600, "Selected deck does not exist"),
	E601(601, "Too many cards in your deck"),
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
