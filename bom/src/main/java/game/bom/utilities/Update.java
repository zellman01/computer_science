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

	public Update() {
		sql = new SQL();

		stmt = new Statements(sql);
	}

	// -1 for HP or ATK means that does not exist
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
					cardNums++;
					try {
						if (card.isSame(Loader.card(idNum)) && Globals.DEBUG_BUILD)
							System.err.println(idNum + " is the same - skipping");
						else
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
				System.err.println(ErrorCodes.E201);
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println(ErrorCodes.E900);
				e.printStackTrace();
			}
		} catch (NullPointerException e) {
			System.err.println(ErrorCodes.E200);
			finished();
			e.printStackTrace();
		}

		File rootDir = new File("cards/");
		System.out.println(cardNums + "|" + rootDir.list().length);
		if (cardNums < rootDir.list().length) { // Check length of cards checked against the amount of card files existing
			if (Globals.DEBUG_BUILD)
				System.err.println("Cards exist that do not exist in database - deleting...");
			for (int i = cardNums+1; i <= rootDir.list().length; i++) {
				if (Deleter.deleteFile("cards", Integer.toString(i), "crd") && Globals.DEBUG_BUILD) {
					System.out.println("Card " + i + " has been deleted.");
				}
			}
		}

		if (finalU)
			finished();
		return comp;
	}

	public int numCards(boolean finalU) {
		int num = -1;
		try {
			ResultSet rs = stmt.execStatements("SELECT COUNT(*) FROM card_data");
			rs.next();
			num = rs.getInt(1);
		} catch (Exception e) {
			System.err.println(ErrorCodes.E900);
			e.printStackTrace();
		}
		if (finalU)
			finished();
		return num;
	}

	// Should be the last thing called. Closes SQL connection, without a way to restart it
	// TODO: Check if sql was unable to start, and throw an error code (TBD) if true
	private void finished() {
		sql.endConnection();
		sql = null;
		stmt = null;
	}
}
