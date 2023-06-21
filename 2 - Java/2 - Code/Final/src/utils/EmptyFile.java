package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EmptyFile {
	// take a string in parameter, open the file (catch exception if not found), and empties it (note that it's a json file)
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

	public EmptyFile() {
	}
}
