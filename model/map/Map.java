package model.map;

import java.util.HashMap;

import utility.Course;

/**
 * @author Jean-Ralph Aviles
 */
public class Map {
	private Maptile[][] grid;
	private int height = 3, width = 3;
	private HashMap<Integer, GridLocation> tileLocations; /* Associates Maptiles with GridLocations */
	
	public Map() {
		grid = new Maptile[width][height];
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				Maptile tile = new Maptile();
				grid[i][j] = tile;
				tileLocations.put(tile.hashCode(), new GridLocation(i, j));
			}
		}
	}

	public Map(Maptile[][] grid) {
		this.grid = grid;
		this.height = grid.length;
		this.width = grid[0].length;

		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				tileLocations.put(grid[i][j].hashCode(), new GridLocation(i, j));
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	/**
	 * @param x x position of Maptile
	 * @param y y position of Maptile
	 * @return Maptile at position (x, y); may return null if tile doesn't exist
	 */
	private Maptile getMapTile(int x, int y) {
		if (x < 0 || x >= width) {
			return null;
		}
		if (y < 0 || y >= height) {
			return null;
		}
		return grid[x][y];
	}
	
	/**
	 * @param Start Maptile to move from
	 * @param Course offsets to find destination tile
	 * @return Maptile destination tile; may be null if tile doesn't exist
	 */
	public Maptile getDestination(Maptile start, Course course) {
		int xOffset = course.getRelativeXDisplacement();
		int yOffset = course.getRelativeYDisplacement();
		GridLocation startLocation = getGridLocation(start);
		
		if (startLocation == null) {
			return null;
		}
		int destX = startLocation.getX() + xOffset;
		int destY = startLocation.getY() + yOffset;

		return getMapTile(destX, destY);
	}
	
	/**
	 * @param maptile Maptile to find location
	 * @return Gridlocation of maptile; may be null if tile doesn't exist
	 */
	private GridLocation getGridLocation(Maptile maptile) {
		return tileLocations.get(maptile.hashCode());
	}
	
	/**
	 * Private nested class to hold grid locations
	 * @author jraviles
	 */
	private class GridLocation {
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
}
