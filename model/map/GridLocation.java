package model.map;

import utility.Course;

/**
 * @author Jean-Ralph Aviles
 */
public class GridLocation {
	private final int x, y;
	
	public GridLocation() {
		x = 0;
		y = 0;
	}

	public GridLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public GridLocation nextGridLocation(Course course) {
		int x = this.getX() + course.getXDisplacement();
		int y = this.getY() + -1 * course.getYDisplacement();
		return new GridLocation(x, y);
	}

	public boolean equals(GridLocation gridLocation) {
		if (gridLocation != null) {
			return (gridLocation.getX() == getX()) && (gridLocation.getY() == getY());
		}
		return false;
	}
	
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	
	public static GridLocation fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		String[] components = stripped.split(",");
		int x = Integer.parseInt(components[0]);
		int y = Integer.parseInt(components[1]);
		return new GridLocation(x, y);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public static void main(String[] args) {
		GridLocation original = new GridLocation(1, 2);
		GridLocation restored = GridLocation.fromString(original.toString());
		if (original.toString().equals(restored.toString()) == false) {
			System.out.println("Serialized Strings differ");
		}
		if (original.getX() != restored.getX()) {
			System.out.println("X values differ");
		}
		if (original.getY() != restored.getY()) {
			System.out.println("Y values differ");
		}
	}
}
