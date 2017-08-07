package general;

import java.io.*;
import java.nio.file.*;

public class FileWriter {

	// Path to assignments data file
	private Path assignmentsFile = Paths.get("Assignments.txt");

	public void writeNonAssignment(String name, String course) {
		// Initializing buffered reader
		try (BufferedReader reader = Files.newBufferedReader(assignmentsFile)) {
			String line;
			String input = "";
			while ((line = reader.readLine()) != null) {
				// Storing entire file in input variable
				input += line + '\n';
			}
			reader.close();
			input += "Tutee -" + name + "- could not be assigned for target course -" + course +"- \n\n";
			// Initializing file output stream
			FileOutputStream out = new FileOutputStream("Assignments.txt");
			// Writing file
			out.write(input.getBytes());
			out.close();

		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
	}

}
