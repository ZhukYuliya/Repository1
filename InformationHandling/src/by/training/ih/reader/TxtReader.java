package by.training.ih.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TxtReader {

	public static String readTxt(String fileName) throws TxtReaderException {
		StringBuilder text = new StringBuilder();

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = reader.readLine()) != null) {
				text.append(line).append("\n");
			}
			return text.toString();
		} catch (IOException e) {
			throw new TxtReaderException("Reading txt file failed", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new TxtReaderException("Closing txt file failed", e);
				}
			}
		}

	}
}
