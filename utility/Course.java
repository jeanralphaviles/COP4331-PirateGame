package utility;

/**
 * 
 * how far does entity want to move? x positions to the right and y positions upward?
 *course takes the course the enity wants to take and encapsulates it into an object.
 */
public class Course {

	private int relativeYDisplacement;
	private int relativeXDisplacement;
	
	public Course(int xDisplacement, int yDisplacement) {
		setRelativeXDisplacement(xDisplacement);
		setRelativeYDisplacement(-yDisplacement);
	}

	public int getRelativeYDisplacement() {
		return relativeYDisplacement;
	}

	public void setRelativeYDisplacement(int relativeYDisplacement) {
		this.relativeYDisplacement = relativeYDisplacement;
	}

	public int getRelativeXDisplacement() {
		return relativeXDisplacement;
	}

	public void setRelativeXDisplacement(int relativeXDisplacement) {
		this.relativeXDisplacement = relativeXDisplacement;
	}
}
