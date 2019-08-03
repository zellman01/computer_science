package forfun;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ISBNGen {
	public static int[] ISBN = {4, 9, 9, 9, 9, 9, 8, 0, 0, 0, 0, 0, 0};
	public static int foundISBNs = 0;
	public static void main(String[] args) {
		List<String> isbns = new ArrayList<>();
		Path file = Paths.get("isbn2.txt");
		boolean cont = true;
		if (ISBN.length != 13) {
			throw new Error("Incorrect length for an ISBN number");
		}
		while (cont) {
			int isbnCheck = 0;
			boolean Double = false;
			for (int i = 0; i < ISBN.length; i++) {
				if (Double == false) {
					isbnCheck += ISBN[i];
					Double = true;
				} else {
					isbnCheck += 3 * ISBN[i];
					Double = false;
				}
			}
			isbnCheck %= 10;
			if (isbnCheck == 0) {
				String isbnToString = "";
				for (int i = 0; i < ISBN.length; i++) {
					System.out.print(ISBN[i]);
					isbnToString += ISBN[i];
				}
				foundISBNs++;
				isbns.add(isbnToString);
				System.out.println();
			}
			if (ISBN[8] > 8) {
				cont = false;
			}
			ISBN[0] += 1;
			for (int i = 0; i < ISBN.length-1; i++) {
				switch(ISBN[i]) {
				case 10:
					ISBN[i] = 0;
					ISBN[i+1] += 1;
					break;	
				}
			}
		}
		try {
			Files.write(file, isbns, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
