package general;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static Tutee[] removeTutee(Tutee[] tutees, int index) {
		Tutee[] result = new Tutee[tutees.length - 1];
		int counter = 0;
		for (int i = 0; i < tutees.length; i++) {
			if (i != index) {
				result[counter] = tutees[i];
				counter++;
			}
		}
		return result;
	}

	public static void main(String[] args) {

		// Creating instance of FileReader
		FileReader read = new FileReader();
		// Initializing and loading a master Array of Tutee
		Tutee[] tuteesMaster = read.initTuteeArr();
		// Initializing a copy of tuteesMaster
		Tutee[] tutees = tuteesMaster;
		// Initializing and loading an Array of Tutors
		Tutor[] tutors = read.initTutorArr();
		// Creating instance of Compatibility
		Compatibility compatible = new Compatibility();
		// Creating instance of FileWriter
		FileWriter write = new FileWriter();

		// Boolean to run main while loop
		Boolean run = true;
		// Precondition check to see if it is possible to run the main program
		if (tutees.length == 0 || tutors.length == 0)
			run = false;

		/* !Note! -Look into the below later- */

		// Integer assigned a random number within possible tutees index values to
		// randomly select a tutee
		int targetTutee = ThreadLocalRandom.current().nextInt(0, tutees.length);
		// Main while loop
		while (run) {
			// For loop to check the compatibility between each tutor and the target tutee
			for (int i = 0; i < tutors.length; i++) {
				// If a match is found, add the tutor's name to the list of possible tutors for
				// this tutee
				if (compatible.checkCompatible(tutees[targetTutee], tutors[i])) {
					tutees[targetTutee].addPossibleTutor(tutors[i].getName());
				}
			}

			// If the tutee has no possible tutors, print a non-assignment statement and
			// remove the tutee from the Array
			if (tutees[targetTutee].possibleTutorsSize() == 0) {
				write.writeNonAssignment(tutees[targetTutee].getName(), tutees[targetTutee].getCourse(0));
				tutees = removeTutee(tutees, targetTutee);
			}

		}

	}

}