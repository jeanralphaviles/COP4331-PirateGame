package model.map;

/**
 * Private nested class to hold grid locations
 * @author jraviles
 */
public class GridLocation {
	private final int x, y;
	
	public GridLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
