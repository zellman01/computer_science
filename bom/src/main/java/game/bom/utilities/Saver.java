package game.bom.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Custom saving class
 * @author zellma01
 * @version 0.1.0
 * @since 0.1.0
 */
public class Saver {
	public static void createDirectory(String dir) throws IOException {
		if (!Files.exists(Paths.get(dir + "/"))) {
			Files.createDirectories(Paths.get(dir + "/"));
		}
	}
	
	/**
	 * Save a given file
	 * @param dir The directory to use when saving (will be created if it does not exist)
	 * @param name The name of the file to save
	 * @param ext The extension to use
	 * @param save The object to save
	 * @throws Exception If it is incapable of saving the object for whatever reason
	 */
	public static void saveFile(String dir, String name, String ext, Object save) throws Exception {
		createDirectory(dir);
		File filePath = new File(dir + "/" + name + "." + ext);
		filePath.createNewFile();
		FileOutputStream fileOut = new FileOutputStream(filePath);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(save);
		out.close();
		fileOut.close();
	}
}
