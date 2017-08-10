package general;

import java.util.ArrayList;

public class Tutee extends Person {

	// List storing the name of the tutor for every possible instance of a tutoring
	// session for the current course
	private ArrayList<String> possibleTutors = new ArrayList<String>();
	// List storing the time for every possible instance of a tutoring sessions for
	// the current course
	private ArrayList<Integer> possibleTimes = new ArrayList<Integer>();
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
	
	// Methods for List possibleTimes
	public void addPossibleTime(int time) {
		possibleTimes.add(time);
	}
	
	public int getPossibleTime(int index) {
		return possibleTimes.get(index);
	}
	
	public int possibleTimesSize() {
		return possibleTimes.size();
	}
	
	public void clearPossibleTimes() {
		possibleTimes.clear();
	}

	// Methods for Boolean assigned
	public void setAssigned(boolean state) {
		assigned = state;
	}

	public boolean isAssigned() {
		return assigned;
	}

}
