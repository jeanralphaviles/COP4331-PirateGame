package utility;

/**
 * @author Jean-Ralph Aviles
 */
public class Course {
	private int xDisplacement;
	private int yDisplacement;
	
	public Course(int xDisplacement, int yDisplacement) {
		setXDisplacement(xDisplacement);
		setYDisplacement(yDisplacement);
	}
	
	@Override
	public String toString() {
		return "[" + xDisplacement + "," + yDisplacement + "]";
	}
	
	public static Course fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		String[] parts = stripped.split(",");
		int xDisplacement = Integer.parseInt(parts[0]);
		int yDisplacement = Integer.parseInt(parts[1]);
		return new Course(xDisplacement, yDisplacement);
	}

	public int getYDisplacement() {
		return yDisplacement;
	}

	public void setYDisplacement(int yDisplacement) {
		this.yDisplacement = yDisplacement;
	}

	public int getXDisplacement() {
		return xDisplacement;
	}

	public void setXDisplacement(int xDisplacement) {
		this.xDisplacement = xDisplacement;
	}
	
	public static void main(String[] args) {
		Course orig = new Course(5, 5);
		orig.setYDisplacement(6);
		Course restored = Course.fromString(orig.toString());
		if (orig.toString().equals(restored.toString()) == false) {
			System.out.println("Serialized strings differ");
		}
		if (orig.getXDisplacement() != restored.getXDisplacement()) {
			System.out.println("X displacements differ");
		}
		if (orig.getYDisplacement() != restored.getYDisplacement()) {
			System.out.println("Y displacements differ");
		}
	}
}
