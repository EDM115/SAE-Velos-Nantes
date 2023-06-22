package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * EmptyFile, allows to empty a file
 */
public class EmptyFile {

	/**
	 * Empty a file
	 * @param fileName the file to empty
	 */
	public static void emptyFile(String fileName) {
		try {
			File file = new File(fileName);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * EmptyFile constructor
	 */
	public EmptyFile() {}

}
