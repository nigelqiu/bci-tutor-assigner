package general;

public class Tutor extends Person{
	
	//Maximum number of tutoring sessions
	private int maxSessions;
	//Current number of sessions already assigned
	private int curSessions = 0;
	
	//Methods for int maxSessions
	public void setMaxSessions(int num) {
		maxSessions = num;
	}
	
	public int getMaxSessions() {
		return maxSessions;
	}
	
	//Methods for int curSessions
	public void increaseCurSessions() {
		curSessions++;
	}
	
	public int getCurSessions() {
		return curSessions;
	}

}
