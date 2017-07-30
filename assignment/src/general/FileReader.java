package general;

import java.io.*;
import java.nio.file.*;

public class FileReader {
	
	//Path to tutee data file
	private Path tuteeFile = Paths.get("Tutees.txt");
	//Path to tutor data file
	private Path tutorFile = Paths.get("Tutors.txt");
	
	//Returns the number of tutees 
	public int numOfTutees() {
		int tuteeNum = 0;
		
		try (BufferedReader reader = Files.newBufferedReader(tuteeFile)) {
		    String line = null;
		    //Scans the file looking for occurrences of "name"
		    //If found, it increments tuteeNum
		    while ((line = reader.readLine()) != null) {
		    	if (line.contains("Name")) {
		    		tuteeNum++;
		    	}
		    }
		    reader.close();
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return tuteeNum;
	}
	
}
