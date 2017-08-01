package general;

import java.util.ArrayList;

public class Tutee extends Person{
	
	//List storing all possible tutors for the course being currently evaluated
	private ArrayList<String> possibleTutors = new ArrayList<String>();
	
	//Methods for List possibleTutors
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

}
