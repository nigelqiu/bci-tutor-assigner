package general;

import java.util.ArrayList;

public class Tutee extends Person {

	// List storing all possible tutors for the course being currently evaluated
	private ArrayList<String> possibleTutors = new ArrayList<String>();
	// Boolean marking whether the tutee is assigned
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
	
	// Methods for Boolean assigned
	public void setAssigned(boolean state) {
		assigned = state;
	}
	
	public boolean isAssigned() {
		return assigned;
	}

}
