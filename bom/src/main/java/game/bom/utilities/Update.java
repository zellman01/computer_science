package game.bom.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;

import game.bom.sqlConnection.SQL;
import game.bom.sqlConnection.Statements;
import game.bom.card.Card;
import game.bom.card.CardPack;
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
					Card card = new Card(rs.getString(5), rs.getInt(6), rs.getString(2), rs.getInt(1), rs.getInt(4), rs.getInt(3), rs.getString(7));
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
	 * Obtain the password of a given user. Protected because nothing outside of the utilities package needs this
	 * @param username The username to get the hashed password of
	 * @return The hashed password
	 */
	protected String password(String username) {
		String str = "";
		try {
			ResultSet rs = stmt.execStatements("SELECT * FROM `users` WHERE `username` == '?'", username);
			rs.next();
			str = rs.getString("password");
		} catch(SQLException e) {
			exceptionPrint(e, ErrorCodes.E900);
		}
		finished();
		return str;
	}
	
	/**
	 * Update the packs in the local directory (MUST be used after cards are updated)
	 * @param finalU If this is the final function of the object
	 * @return If updating was successful
	 */
	public boolean updatePacks(boolean finalU) {
		boolean comp = false;
		try {
			ResultSet rs = stmt.execStatements("SELECT * FROM `booster_packs`");
			while(rs.next()) {
				String name = rs.getString(2);
				CardPack addPack = new CardPack(name);
				String temp = rs.getString(3);
				String[] cardIdArray = temp.split(",");
				Card[] cardArray = new Card[cardIdArray.length];
				for (int i = 0; i < cardArray.length; i++) {
					cardArray[i] = Loader.card(cardIdArray[i]);
				}
				for (int i = 0; i < cardArray.length; i++) {
					addPack.addCard(cardArray[i]);
				}
				Saver.saveFile("packs/", name, ".pck", addPack);
			}
			comp = true;
		} catch (SQLException | IOException e) {
			exceptionPrint(e, ErrorCodes.E202);
		}
		return comp;
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
