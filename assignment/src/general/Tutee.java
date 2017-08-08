package general;

import java.util.ArrayList;

public class Tutee extends Person {

	// List storing all possible tutors for the course being currently evaluated
	private ArrayList<String> possibleTutors = new ArrayList<String>();
	// Boolean stating whether the tutee is already assigned in the current session
	private boolean assigned;

	// Methods for List possibleTutors
	public void addPossibleTutor(String name) {
		possibleTutors.add(name);
	}

	public String getPossibleTutor(int index) {
		return possibleTutors.get(index);
	}

	public int possibleTutorsSize() {
		return possibleTutors.size();
	}

	public void clearPossibleTutors() {
		possibleTutors.clear();
	}

	// Methods for boolean assigned
	public void setAssigned(boolean assignment) {
		assigned = assignment;
	}

	public boolean isAssigned() {
		return assigned;
	}

}
