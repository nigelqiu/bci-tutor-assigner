package general;

public class Compatibility {
	
	/**
	 * Checks whether the target course is found in the tutor's course list
	 * @param course - The target course
	 * @param tutor - The target tutor
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
	 * Checks whether a time matches on both the tutor's and tutee's availability schedule
	 * @param tutee - The target tutee
	 * @param tutor - The target tutor
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
	 * @param tutee - The target tutee
	 * @param tutor - The target tutor
	 * @return true if a match is found, false if not
	 */
	public boolean checkCompatible(Tutee tutee, Tutor tutor) {
		if (checkCourses(tutee.getCourse(0), tutor))
			if (checkTimes(tutee, tutor))
				return true;
		return false;
	}

}
