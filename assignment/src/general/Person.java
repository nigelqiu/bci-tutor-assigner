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
	 * First character - day of the week Second character - time slot
	 */
	private ArrayList<String> times = new ArrayList<String>();
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
	
	public void removeCourse(int course) {
		courses.remove(course);
	}
	
	public boolean areCoursesEmpty() {
		return courses.isEmpty();
	}
	
	//Methods for List times
	public void addTime(String time) {
		times.add(time);
	}
	
}
