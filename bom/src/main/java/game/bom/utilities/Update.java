package game.bom.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;

import game.bom.sqlConnection.SQL;
import game.bom.sqlConnection.Statements;
import game.bom.card.Card;
import game.bom.error.ErrorCodes;
import game.bom.global.Globals;

/**
 * Updater class
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public class Update {
	private SQL sql;
	private Statements stmt;

	/**
	 * Create the Update object
	 */
	public Update() {
		sql = new SQL();

		stmt = new Statements(sql);
	}

	/**
	 * Low-level update function for cards
	 * @param finalU If it is the final function of the object
	 * @return If the update was successful or not
	 */
	public boolean updateCards(boolean finalU){
		boolean comp = false;
		int cardNums = 0;
		try {
			ResultSet rs = stmt.execStatements("Select * FROM card_data;");
			try {
				boolean create = false;
				while (rs.next()) {
					Card card = new Card(rs.getString(5), rs.getInt(6), rs.getString(2), rs.getInt(1), rs.getInt(4), rs.getInt(3));
					String idNum = Integer.toString(card.getIdNumber());
					Globals.cardArray.add(card.getIdNumber());
					cardNums++;
					try {
						if (card.isSame(Loader.card(idNum))) {
							if (Globals.DEBUG_BUILD) System.err.println(idNum + " is the same - skipping");
						} else
							create = true;
					} catch(Exception e) {
						create = true;
					}

					if (create)
						Saver.saveFile("cards", idNum, "crd", card);
				}
				System.out.println("Successfully updates cards");
				comp = true;
			} catch (SQLException | IOException e) {
				exceptionPrint(e, ErrorCodes.E201);
			} catch (Exception e) {
				exceptionPrint(e, ErrorCodes.E900);
			}
		} catch (NullPointerException e) {
			finished();
			exceptionPrint(e, ErrorCodes.E200);
		}

		File rootDir = new File("cards/");
		if (cardNums < rootDir.list().length) { // Check length of cards checked against the amount of card files existing
			if (Globals.DEBUG_BUILD)
				System.err.println("Cards exist that do not exist in database - deleting...");
			for (int i = 0; i <= rootDir.list().length; i++) { // If run, delete card ids not present
				if (!Globals.cardArray.contains(i)) {
					if (Deleter.deleteFile("cards", Integer.toString(i), "crd")) {
						if (Globals.DEBUG_BUILD) System.out.println("Card " + i + " has been deleted.");
					}
				}
			}
		}

		if (finalU)
			finished();
		return comp;
	}

	/**
	 * Calculate the total amount of cards present in the database
	 * @param finalU If it is the final function of the object
	 * @return Total number of cards in the database
	 */
	public int numCards(boolean finalU) {
		int num = -1;
		try {
			ResultSet rs = stmt.execStatements("SELECT COUNT(*) FROM card_data");
			rs.next();
			num = rs.getInt(1);
		} catch (Exception e) {
			exceptionPrint(e, ErrorCodes.E900);
			num = new File("cards/").list().length;
		}
		if (finalU)
			finished();
		return num;
	}

	/**
	 * Get the checksum of the rarity table
	 * @param finalU If it is the final function of the object
	 * @return The checksum of the rarity table
	 * @deprecated Useless method check. Will be removed before next test release
	 */
	public long rarityCheck(boolean finalU) {
		long checksum = -1;
		try {
			ResultSet rs = stmt.execStatements("CHECKSUM TABLE `rarity`");
			rs.next();
			checksum = rs.getLong(2);
		} catch(Exception e) {
			exceptionPrint(e, ErrorCodes.E900);
		}
		if (finalU)
			finished();
		return checksum;
	}
	
	private void exceptionPrint(Exception e, ErrorCodes ec) {
		System.err.println(ec);
		if (Globals.DEBUG_BUILD) e.printStackTrace();
	}

	// Should be the last thing called. Closes SQL connection, without a way to restart it
	// TODO: Check if sql was unable to start, and throw an error code (TBD) if true
	private void finished() {
		sql.endConnection();
		sql = null;
		stmt = null;
	}
}
