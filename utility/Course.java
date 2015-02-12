package utility;

public class Course {

	private int relativeYDisplacement;
	private int relativeXDisplacement;
	
	public Course(int xDisplacement, int yDisplacement) {
		setRelativeXDisplacement(xDisplacement);
		setRelativeYDisplacement(yDisplacement);
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
