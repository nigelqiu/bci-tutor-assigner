package general;

public class Main {

	public static void main(String[] args) {
		
		//Creating instance of FileReader
		FileReader read = new FileReader();
		//Initializing and loading an Array of Tutees
		Tutee[] tutees = read.initTuteeArr();
		//Initializing and loading an Array of Tutors
		Tutor[] tutors = read.initTutorArr();

	}

}
