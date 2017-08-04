package general;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) {
		
		//Creating instance of FileReader
		FileReader read = new FileReader();
		//Initializing and loading an Array of Tutees
		Tutee[] tutees = read.initTuteeArr();
		//Initializing and loading an Array of Tutors
		Tutor[] tutors = read.initTutorArr();
		//Boolean to run main while loop
		Boolean run = true;
		
		//Precondition check to see if it is possible to run the main program
		if (tutees.length == 0 || tutors.length == 0)
			run = false;
		
		//Integer assigned a random number within possible tutees index values to randomly select a tutee
		int targetTutee = ThreadLocalRandom.current().nextInt(0, tutees.length + 1);
		//Main while loop
		while (run) {
			//[WIP]
		}

	}

}
