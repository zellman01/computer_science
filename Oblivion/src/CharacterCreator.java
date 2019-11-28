import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class CharacterCreator {
	private Character char1 = null;
	private void saveSystem(String name) {
		try {
			File filePath = new File(name + ".chr");
			filePath.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(char1);
			out.close();
			fileOut.close();

			System.out.println("Save successful.");
		} catch(IOException ex) {
			System.out.println("*** CANNOT FIND FILE ***");
			System.out.println(ex);
		}
	}
	
	public static void main(String[] args) {
		CharacterCreator cc = new CharacterCreator();
		Scanner kboard = new Scanner(System.in);
		System.out.println("What is the character's first name? (used internally as the identification)");
		String fname = kboard.next();
		System.out.println("\nWhat is the character's last name? (N/A for none)");
		String lname = kboard.next();
		if (lname.equalsIgnoreCase("n/a")) {
			
		}
		if (lname.equalsIgnoreCase("n/a"))
			System.out.println("\nHow much HP does " + fname + " have?");
		else
			System.out.println("\nHow much HP does " + fname + " " + lname + " have?");
		int hp = kboard.nextInt();
		kboard.close();
		
		if (lname.equalsIgnoreCase("N/A")) {
			cc.char1 = new Character(fname, hp, 0, fname);
			cc.saveSystem(fname);
		} else {
			cc.char1 =  new Character(fname + " " + lname, hp, 0, fname);
			cc.saveSystem(lname + "_" + fname);
		}
	}
}