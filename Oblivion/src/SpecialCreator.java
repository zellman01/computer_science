import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class SpecialCreator {
	private Specials spe;
	
	private void saveSystem(String name) {
		try {
			File filePath = new File(name + ".spe");
			filePath.createNewFile();
			FileOutputStream fileOut = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(spe);
			out.close();
			fileOut.close();

			System.out.println("Save successful.");
		} catch(IOException ex) {
			System.out.println("*** CANNOT FIND FILE ***");
			System.out.println(ex);
		}
	}
	
	public void system(String specialName, String userName, int damage) {
		spe = new Specials(specialName, damage, userName);
		this.saveSystem(specialName);
	}
	
	public static void main(String[] args) {
		SpecialCreator sc = new SpecialCreator();
		Scanner kb = new Scanner(System.in);
		System.out.println("What is the special's name?");
		String name = kb.next();
		System.out.println("How much damage does the special do? (Input a negative number to heal)");
		int damage = kb.nextInt();
		System.out.println("What is the name of the character that may use this special?");
		String user = kb.next();
		sc.spe = new Specials(name, damage, user);
		kb.close();
		sc.saveSystem(sc.spe.getName());
	}
}
