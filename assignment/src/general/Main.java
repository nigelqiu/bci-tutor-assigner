package general;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static void main(String[] args) {
		
		//Creating instance of FileReader
		FileReader read = new FileReader();
		//Initializing and loading a master Array of Tutee
		Tutee[] tuteesMaster = read.initTuteeArr();
		//Copying tuteesMaster to another Tutee Array
		Tutee[] tutees = tuteesMaster;
		//Initializing and loading an Array of Tutors
		Tutor[] tutors = read.initTutorArr();
		//Creating instance of Compatibility
		Compatibility compatible = new Compatibility();
		//Boolean to run main while loop
		Boolean run = true;
		
		//Precondition check to see if it is possible to run the main program
		if (tutees.length == 0 || tutors.length == 0)
			run = false;
		
		//Integer assigned a random number within possible tutees index values to randomly select a tutee
		int targetTutee = ThreadLocalRandom.current().nextInt(0, tuteesMaster.length + 1);
		//Main while loop
		while (run) {
			for (int i = 0; i < tutors.length; i++) {
				if (compatible.checkCompatible(tutees[targetTutee], tutors[i])) {
					tutees[targetTutee].addPossibleTutor(tutors[i].getName());
				}
			}
		}

	}

}
