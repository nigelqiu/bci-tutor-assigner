package general;

import java.util.ArrayList;
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
		// Initializing and loading an Array of Tutee
		Tutee[] tutees = read.initTuteeArr();
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
		// List of tutees' names which have not been checked for compatible tutors
		ArrayList<String> unchecked = new ArrayList<String>(tutees.length);
		// Adding all the tutee's names to the list
		for (int i = 0; i < tutees.length; i++) {
			unchecked.add(tutees[i].getName());
		}

		/* !Note! -Look into the below later- */

		// Integer assigned a random number within possible unchecked index values to
		// randomly select a tutee
		int targetTutee = ThreadLocalRandom.current().nextInt(0, unchecked.size());
		// Boolean to mark whether the current tutee is able to be assigned
		boolean canAssign;
		ArrayList<String> oneMatch = new ArrayList<String>();
		// Main while loop
		while (run) {
			// Compatibility checking loop
			while (true) {
				canAssign = false;
				// For loop to check the compatibility between each tutor and the target tutee
				for (int i = 0; i < tutors.length; i++) {
					// If a match is found, add the tutor's name to the list of possible tutors for
					// this tutee and mark the tutee as possible to assign
					if (compatible.checkCompatible(tutees[targetTutee], tutors[i])) {
						tutees[targetTutee].addPossibleTutor(tutors[i].getName());
						canAssign = true;
					}
				}
				
				// If the tutee can not be assigned, print a non-assignment statement
				if (!canAssign)
					write.writeNonAssignment(tutees[targetTutee].getName(), tutees[targetTutee].getCourse(0));

				// Remove the tutee from the unchecked list
				unchecked.remove(targetTutee);
				
				// If the size of unchecked is not zero, find a new target tutee
				if (unchecked.size() != 0)
					targetTutee = ThreadLocalRandom.current().nextInt(0, unchecked.size());
				// Else, break this compatibility checking loop
				else
					break;
			}
			
			
			for (int i = 0; i < tutees.length; i++) {
				
			}
		}
		
	}

}