package general;

import java.util.ArrayList;

public class Person {

	//Name of the person
	private String name;
	// List of courses requested
	// Uses course codes shorten to the first four characters
	private ArrayList<String> courses = new ArrayList<String>();
	// List of times available
	/*
	 * Tens digit - day of the week 
	 * Ones digit - time slot
	 */
	private ArrayList<Integer> times = new ArrayList<Integer>();
	// Array of tutoring group sizes requested
	// The index corresponds to the course requested at the same index
	private int[] groupSize;
	
	//Methods for String name
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	//Methods for List courses
	public void addCourse(String course) {
		courses.add(course);
	}
	
	public String getCourse(int course) {
		return courses.get(course);
	}
	
	public int coursesSize() {
		return courses.size();
	}
	
	public void removeCourse(int course) {
		courses.remove(course);
	}
	
	//Methods for List times
	public void addTime(int time) {
		times.add(time);
	}
	
	public int getTime(int slot) {
		return times.get(slot);
	}
	
	public int timesSize() {
		return times.size();
	}
	
	public void removeTime(int slot) {
		times.remove(slot);
	}
	
	//Initializing Array groupSize
	public void initGroupSize() {
		groupSize = new int[courses.size()];
	}
	
	//Methods for Array groupSize
	public void setGroupSize(int course, int size) {
		groupSize[course] = size;
	}
	
	public int getGroupSize(int course) {
		return groupSize[course];
	}
	
}
