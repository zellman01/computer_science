package game.bom.utilities;

import java.io.File;

public class Deleter {
	protected static boolean deleteFile(String dir, String name, String ext) {
		if (dir.contains("../"))
			return false;
		File filePath = new File(dir + "/" + name + "." + ext);
		return filePath.delete();
	}
}
