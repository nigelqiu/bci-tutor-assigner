package general;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

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
		for (int i = 0; i < tutees.length; i++)
			unchecked.add(tutees[i].getName());
		// Integer assigned a random number within possible unchecked index values to
		// randomly select a tutee
		int targetTutee = ThreadLocalRandom.current().nextInt(0, unchecked.size());
		// List of indices of the tutees which have the target number of tutors
		ArrayList<Integer> toAssign = new ArrayList<Integer>();
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

			//
			int amount = 1;
			// Assignment forming loop
			while (true) {
				// For loop to go through all the tutees
				for (int i = 0; i < tutees.length; i++) {
					// If the tutee has amount number of tutors, add them to toAssign
					if (tutees[i].possibleTutorsSize() == amount)
						toAssign.add(i);
				}

				// If toAssign has indices
				if (toAssign.size() > 0) {
					// Index of the tutee being assigned
					int target = toAssign.get(ThreadLocalRandom.current().nextInt(0, toAssign.size()));
					// Index of session being evaluated
					int session = ThreadLocalRandom.current().nextInt(0, tutees[target].possibleTutorsSize());
					// Integer time of the session being evaluated
					int time = tutees[target].getPossibleTime(session);
					// Value of the maximum group size
					int maxGroup = Math.min(tutees[target].getGroupSize(0),
							tutors[tutees[target].getPossibleTutor(session)].getGroupSize(0));
					// Indices of all the tutees in a group
					ArrayList<Integer> group = compatible.formGroup(target, time, maxGroup, tutees);
					group.add(target);

					// Begin printing assignment statement
					write.startAssignment();

					// For loop to go through all tutees in the group
					for (int i = 0; i < group.size(); i++) {
						// Continue printing assignment statement
						write.continueAssignment(tutees[i].getName());
						// Set the tutee as assigned
						tutees[i].setAssigned(true);
						// For loop to go through all the time slots of the tutee
						for (int j = 0; j < tutees[i].timesSize(); j++) {
							// If the session time equals to that time slot, remove it
							if (time == tutees[i].getTime(j)) {
								tutees[i].removeTime(j);
								break;
							}
						}
					}

					// Finish printing assignment statement
					write.endAssignment(tutees[target].getCourse(0), time,
							tutors[tutees[target].getPossibleTutor(session)].getName());

					// Increment the session count of the group's tutor
					tutors[tutees[target].getPossibleTutor(session)].increaseCurSessions();

					// Clear toAssign
					toAssign.clear();

					// Exit this while loop
					break;
				} else
					// Increment amount
					amount++;
			}
		}

	}

}