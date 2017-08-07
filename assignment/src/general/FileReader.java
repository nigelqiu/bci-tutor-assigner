package general;

import java.io.*;
import java.nio.file.*;

public class FileReader {

	// Path to tutee data file
	private Path tuteeFile = Paths.get("Tutees.txt");
	// Path to tutor data file
	private Path tutorFile = Paths.get("Tutors.txt");

	// Returns the number of tutees
	public int numOfTutees() {
		// Number of tutees
		int tuteeNum = 0;

		// BufferedReader to read data
		try (BufferedReader reader = Files.newBufferedReader(tuteeFile)) {
			// String to store read one line of read data
			String line = null;
			// Scans the file looking for occurrences of "name"
			// If found, it increments tuteeNum
			while ((line = reader.readLine()) != null) {
				if (line.contains("Name")) {
					tuteeNum++;
				}
			}
			reader.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		return tuteeNum;
	}

	// Returns an Array of initialized Tutees
	public Tutee[] initTuteeArr() {
		Tutee[] tutees = new Tutee[numOfTutees()];

		try (BufferedReader reader = Files.newBufferedReader(tuteeFile)) {
			String line = null;
			// Variable holds the index of the current tutee being read
			int index = -1;
			while ((line = reader.readLine()) != null) {
				// Finding the start of a tutee data block
				if (line.contains("Name")) {
					// Increments the index
					index++;
					// Creates a Tutee object at the index location
					tutees[index] = new Tutee();

					// Sets the name of the Tutee object
					tutees[index].setName(line.substring(6));

					// Reads the next line
					line = reader.readLine();

					// While loop that exits once all the courses are added
					while (true) {
						// Adds the course
						tutees[index].addCourse(line.substring(0, 4));
						// Breaks if last course was added, else, removes the course that was added
						if (line.length() == 4)
							break;
						else
							line = line.substring(5);
					}

					// Reads the next line
					line = reader.readLine();

					// While loop that exits one all the time slots are added
					while (true) {
						// Adds the time slot
						tutees[index].addTime(Integer.parseInt(line.substring(0, 2)));
						// Breaks if last time slot was added, else, removes the time slot that was
						// added
						if (line.length() == 2)
							break;
						else
							line = line.substring(3);
					}

					// Reads the next line
					line = reader.readLine();

					// Initializing groupSize array
					tutees[index].initGroupSize();
					// For loop which increments for each index in groupSize
					for (int i = 0; i < tutees[index].coursesSize(); i++) {
						// Index value of the first space in line
						int space = line.indexOf(' ');
						// Reads the group size value to the related groupSize index
						tutees[index].setGroupSize(i, Integer.parseInt(line.substring(0, space)));
						// Removes the last read group size value unless it is at the end of the string
						if (!(line.length() == space))
							line = line.substring(space + 1);
					}
				}
			}
			reader.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		return tutees;
	}

	// Returns the number of tutors
	public int numOfTutors() {
		// Number of tutors
		int tutorNum = 0;

		// BufferedReader to read data
		try (BufferedReader reader = Files.newBufferedReader(tutorFile)) {
			// String to store one line of read data
			String line = null;
			// Scans the file looking for occurrences of "name"
			// If found, it increments tutorNum
			while ((line = reader.readLine()) != null) {
				if (line.contains("Name")) {
					tutorNum++;
				}
			}
			reader.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		return tutorNum;
	}

	// Returns an Array of initialized Tutors
	public Tutor[] initTutorArr() {
		Tutor[] tutors = new Tutor[numOfTutors()];

		try (BufferedReader reader = Files.newBufferedReader(tutorFile)) {
			String line = null;
			// Variable holds the index of the current tutor being read
			int index = -1;
			while ((line = reader.readLine()) != null) {
				// Finding the start of a tutor data block
				if (line.contains("Name")) {
					// Increments the index
					index++;
					// Creates a Tutor object at the index location
					tutors[index] = new Tutor();

					// Sets the name of the Tutor object
					tutors[index].setName(line.substring(6));

					// Reads the next line
					line = reader.readLine();

					// While loop that exits once all the courses are added
					while (true) {
						// Adds the course
						tutors[index].addCourse(line.substring(0, 4));
						// Breaks if last course was added, else, removes the course that was added
						if (line.length() == 4)
							break;
						else
							line = line.substring(5);
					}

					// Reads the next line
					line = reader.readLine();

					// While loop that exits one all the time slots are added
					while (true) {
						// Adds the time slot
						tutors[index].addTime(Integer.parseInt(line.substring(0, 2)));
						// Breaks if last time slot was added, else, removes the time slot that was
						// added
						if (line.length() == 2)
							break;
						else
							line = line.substring(3);
					}

					// Reads the next line
					line = reader.readLine();

					// Initializing groupSize array
					tutors[index].initGroupSize();
					// For loop which increments for each index in groupSize
					for (int i = 0; i < tutors[index].coursesSize(); i++) {
						// Index value of the first space in line
						int space = line.indexOf(' ');
						// Adds the group size value to the related groupSize index
						tutors[index].setGroupSize(i, Integer.parseInt(line.substring(0, space)));
						// Removes the last read group size value unless it is at the end of the string
						if (!(line.length() == space))
							line = line.substring(space + 1);
					}

					// Reads the next line
					line = reader.readLine();

					// Adds the value to maxSessions
					tutors[index].setMaxSessions(Integer.parseInt(line));
				}
			}
			reader.close();
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}

		return tutors;
	}

}
