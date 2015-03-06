package model.map;

import utility.Course;

/**
 * @author Jean-Ralph Aviles
 */
public class GridLocation {
	private final int x, y;
	
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
			return gridLocation.getX() == getX() && gridLocation.getY() == getY();
		}
		return false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
