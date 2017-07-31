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
	
	//Returns an Array of initialized tutees
	public Tutee[] initTuteeArr() {
		Tutee[] tutees = new Tutee[numOfTutees()];
		
		try (BufferedReader reader = Files.newBufferedReader(tuteeFile)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	//Variable holds the index of the current tutee being read
		    	int index = -1;
		    	//Finding the start of a tutee data block
		    	if (line.contains("Name")) {
		    		//Increments the index
		    		index++;
		    		//Creates a Tutee object at the index location
		    		tutees[index] = new Tutee();
		    		
		    		//Sets the name of the Tutee object
		    		tutees[index].setName(line.substring(6));
		    		
		    		//Reads the next line
		    		line = reader.readLine();
		    		
		    		//While loop that exits once all the courses are added
		    		while(true) {
		    			//Adds the course
		    			tutees[index].addCourse(line.substring(0, 4));
		    			//Breaks if last course was added, else, removes the course that was added
		    			if (line.length() == 4)
		    				break;
		    			else
		    				line = line.substring(5);
		    		}
		    		
		    		//Reads the next line
		    		line = reader.readLine();
		    		
		    		//While loop that exits one all the time slots are added
		    		while(true) {
		    			//Adds the time slot
		    			tutees[index].addTime(Integer.parseInt(line.substring(0, 2)));
		    			//Temporary [WIP]
		    			break;
		    		}
		    	}
		    }
		    reader.close();
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		return tutees;
	}
	
}
