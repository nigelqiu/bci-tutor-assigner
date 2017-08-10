package general;

import java.util.ArrayList;

public class Compatibility {

	/**
	 * Checks whether the target course is found in the tutor's course list
	 * 
	 * @param course
	 *            - The target course
	 * @param tutor
	 *            - The target tutor
	 * @return true if the target course is found, false if not
	 */
	public boolean checkCourses(String course, Tutor tutor) {
		for (int i = 0; i < tutor.coursesSize(); i++) {
			if (tutor.getCourse(i).equals(course))
				return true;
		}
		return false;
	}

	/**
	 * Checks whether a time matches on both the tutor's and tutee's availability
	 * schedule
	 * 
	 * @param tutee
	 *            - The target tutee
	 * @param tutor
	 *            - The target tutor
	 * @return true if a match is found, false if not
	 */
	public boolean checkTimes(Tutee tutee, Tutor tutor) {
		for (int i = 0; i < tutee.timesSize(); i++) {
			for (int j = 0; j < tutor.timesSize(); j++) {
				if (tutor.getTime(j) == (tutee.getTime(i)))
					return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether it is possible to match the tutee to a tutor
	 * 
	 * @param tutee
	 *            - The target tutee
	 * @param tutor
	 *            - The target tutor
	 * @return true if a match is found, false if not
	 */
	public boolean checkCompatible(Tutee tutee, Tutor tutor) {
		if (checkCourses(tutee.getCourse(0), tutor))
			if (checkTimes(tutee, tutor))
				return true;
		return false;
	}

	/**
	 * Attempts to form a group of tutees for the same assignment
	 * 
	 * @param target
	 *            - The index of the original tutee
	 * @param time
	 *            - The time of the assignment
	 * @param maxSize
	 *            - The maximum size of the group
	 * @param tutees
	 *            - The array of tutees
	 * @return a list of the names of the other tutees in the group
	 */
	public ArrayList<String> formGroup(int target, int time, int maxSize, Tutee[] tutees) {
		ArrayList<String> group = new ArrayList<String>();
		int curSize = 1;

		boolean run = false;
		if (maxSize > curSize)
			run = true;

		int index = 0;
		while (run) {
			// If the current index is not the same as the target index
			if (index != target)
				// For each course requested by the tutee
				for (int i = 0; i < tutees[index].coursesSize(); i++)
					// If the current course is the same as the target course
					if (tutees[index].getCourse(i).equals(tutees[target].getCourse(0)))
						// If the tutee's group size is more than the current size of the group
						if (tutees[index].getGroupSize(i) > curSize)
							// For each time slot requested by the tutee
							for (int j = 0; j < tutees[index].timesSize(); j++)
								// If the current time slot is the same as the target time slot
								if (tutees[index].getTime(j) == time) {
									// Add the tutee's name to the group
									group.add(tutees[index].getName());
									// Increment current group size
									curSize++;
									// If the tutee's group size is less than the maximum group size
									if (tutees[index].getGroupSize(i) < maxSize)
										// Make the tutee's group size the maximum group size
										maxSize = tutees[index].getGroupSize(i);
								}

			index++;

			if (!(maxSize > curSize))
				run = false;
			if (index == tutees.length)
				run = false;
		}

		return group;
	}

}
