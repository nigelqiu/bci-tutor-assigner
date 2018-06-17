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
		// Main while loop
		while (run) {
			// List of tutees' indices which have not been checked for compatible tutors
			ArrayList<Integer> unchecked = new ArrayList<Integer>(tutees.length);
			// Adding adding the index of all tutees that are not assigned
			for (int i = 0; i < tutees.length; i++)
				if (!tutees[i].isAssigned())
					unchecked.add(i);
			// Boolean to run while loops if there are tutees to check for
			boolean attempt = unchecked.size() > 0;
			// Integer assigned an random index value from unchecked to randomly select a
			// tutee
			int targetTutee = unchecked.get(ThreadLocalRandom.current().nextInt(0, unchecked.size()));
			// Compatibility checking loop
			while (attempt) {
				// Adds all possible tutoring sessions to the target tutee
				tutees[targetTutee] = compatible.checkCompatible(tutees[targetTutee], tutors);
				
				// If the tutee can not be assigned, print a non-assignment statement and set
				// the tutee as assigned
				if (tutees[targetTutee].possibleTutorsSize() == 0) {
					write.writeNonAssignment(tutees[targetTutee].getName(), tutees[targetTutee].getCourse(0));
					tutees[targetTutee].setAssigned(true);
				}

				// Remove the tutee from the unchecked list
				unchecked.remove(unchecked.indexOf(targetTutee));

				// If the size of unchecked is more than zero, find a new target tutee
				if (unchecked.size() > 0)
					targetTutee = unchecked.get(ThreadLocalRandom.current().nextInt(0, unchecked.size()));
				else
					break;
			}

			// List of indices of the tutees which have the target number of tutors
			ArrayList<Integer> toAssign = new ArrayList<Integer>();
			// Number of possible tutors for current assignment search
			int amount = 1;
			// Assignment forming loop
			while (attempt) {
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

			// If the while loops were not run
			if (!attempt) {
				for (int i = 0; i < tutees.length; i++) {
					tutees[i].setAssigned(false);
					tutees[i].removeCourse(0);
					if (tutees[i].coursesSize() == 0 || tutees[i].timesSize() == 0) {
						Tutee[] newTutees = new Tutee[tutees.length - 1];
						int index = 0;
						for (Tutee tutee : tutees)
							if (tutee != tutees[i]) {
								newTutees[index] = tutee;
								index++;
							}
						tutees = newTutees;
						i--;
					}
				}
				if (tutees.length == 0) {
					run = false;
					System.out.println("All tutees assigned.");
				}
			} else {
				// Resetting values to run main loop again
				for (int i = 0; i < tutees.length; i++) {
					tutees[i].clearPossibleTutors();
					tutees[i].clearPossibleTimes();
				}
				// 
				for (int i = 0; i < tutors.length; i++) {
					
				}
			}

			// If there are no more tutors, exit the main loop and print out a message
			if (tutors.length == 0) {
				run = false;
				System.out.println("All tutors assigned.");
			}
		}

	}

}