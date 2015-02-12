package map;

import java.util.HashMap;

import utility.Course;

public class Map {
	private Maptile[][] grid;
	private int height = 3, width = 3;
	private HashMap<Integer, gridLocation> tileLocations;
	
	public Map() {
		grid = new Maptile[width][height];
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				Maptile tile = new Maptile();
				grid[i][j] = tile;
				tileLocations.put(tile.hashCode(), new gridLocation(i, j));
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	private Maptile getMapTile(int x, int y) {
		if (x < 0 || x >= width) {
			return null;
		}
		if (y < 0 || y >= height) {
			return null;
		}
		return grid[x][y];
	}
	
	public Maptile getDestination(Maptile start, Course course) {
		int xOffset = course.getRelativeXDisplacement();
		int yOffset = course.getRelativeYDisplacement();
		gridLocation startLocation = tileLocations.get(start.hashCode());
		
		int destX = startLocation.getX() + xOffset;
		int destY = startLocation.getY() + yOffset;

		return getMapTile(destX, destY);
	}
	
	private class gridLocation {
		private final int x, y;
		
		public gridLocation(int x, int y) {
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
