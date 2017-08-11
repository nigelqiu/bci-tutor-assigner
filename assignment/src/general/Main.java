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
		// List of the names of the tutees which only have one possible tutor
		ArrayList<Integer> match = new ArrayList<Integer>();
		// Main while loop
		while (run) {
			// Compatibility checking loop
			while (true) {
				// Adds all possible tutoring sessions to the target tutee
				tutees[targetTutee] = compatible.checkCompatible(tutees[targetTutee], tutors);

				// If the tutee can not be assigned, print a non-assignment statement and set
				// the tutee as assigned
				if (tutees[targetTutee].possibleTutorsSize() == 0) {
					write.writeNonAssignment(tutees[targetTutee].getName(), tutees[targetTutee].getCourse(0));
					tutees[targetTutee].setAssigned(true);
				}

				// Remove the tutee from the unchecked list
				unchecked.remove(targetTutee);

				// If the size of unchecked is not zero, find a new target tutee
				if (unchecked.size() != 0)
					targetTutee = ThreadLocalRandom.current().nextInt(0, unchecked.size());
				// Else, break this compatibility checking loop
				else
					break;
			}

			int amount = 1;
			while (true) {
				for (int i = 0; i < tutees.length; i++) {
					if (tutees[i].possibleTutorsSize() == amount)
						match.add(i);
				}

				if (match.size() > 0) {
					int target = match.get(ThreadLocalRandom.current().nextInt(0, match.size()));
					int session = ThreadLocalRandom.current().nextInt(0, tutees[target].possibleTutorsSize());
					int time = tutees[target].getPossibleTime(session);
					int size = Math.min(tutees[target].getGroupSize(0),
							tutors[tutees[target].getPossibleTutor(session)].getGroupSize(0));
					ArrayList<Integer> group = compatible.formGroup(target, time, size, tutees);
					group.add(target);
					
					for (int i = 0; i < group.size(); i++) {
						tutees[i].setAssigned(true);
						for (int j = 0; j < tutees[i].timesSize(); j++) {
							if (time == tutees[i].getTime(j)) {
								tutees[i].removeTime(j);
								break;
							}
						}
					}
					
					tutors[tutees[target].getPossibleTutor(session)].increaseCurSessions();
					
					break;
				}

				amount++;
			}
		}

	}

}