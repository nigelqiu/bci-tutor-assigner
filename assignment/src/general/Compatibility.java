package general;

public class Compatibility {
	
	public boolean checkCourses(String course, Tutor tutor) {
		for (int i = 0; i < tutor.coursesSize(); i++) {
			if (tutor.getCourse(i).equals(course))
				return true;
		}
		return false;
	}
	
	public boolean checkTimes(Tutee tutee, Tutor tutor) {
		for (int i = 0; i < tutee.timesSize(); i++) {
			for (int j = 0; j < tutor.timesSize(); j++) {
				if (tutor.getTime(j) == (tutee.getTime(i)))
					return true;
			}
		}
		return false;
	}
	
	public boolean checkCompatible(Tutee tutee, Tutor tutor) {
		if (checkCourses(tutee.getCourse(0), tutor))
			if (checkTimes(tutee, tutor))
				return true;
		return false;
	}

}
