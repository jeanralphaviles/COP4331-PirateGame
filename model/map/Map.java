package model.map;

import java.util.ArrayList;

import model.map.terrain.Mountain;
import utility.MapGenerator;

/**
 * @author Jean-Ralph Aviles
 */
public class Map {
    private int height = 5, width = 5;
	private Maptile[][] grid = new Maptile[width][height];
    
    public Map() {
    	for (int x = 0; x < width; ++x) {
    		for (int y = 0; y < height; ++y) {
    			grid[x][y] = new Maptile();
    		}
    	}
    }
    
    public Map(Maptile[][] grid) {
    	this.grid = grid;
    	setHeight(grid[0].length);
    	setWidth(grid.length);
    }

	public boolean isValidGridLocation(GridLocation gridLocation) {
		return gridLocation != null && gridLocation.getX() >= 0
				&& gridLocation.getX() < getWidth()
				&& gridLocation.getY() >= 0
				&& gridLocation.getY() < getHeight();
	}
    
    public Maptile getMaptile(GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		return grid[gridLocation.getX()][gridLocation.getY()];
    	} else {
    		return null;
    	}
    }
    
    @Override
	public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("[");
    	for (int y = 0; y < height; ++y) {
    		builder.append("[");
    		for (int x = 0; x < width; ++x) {
    			builder.append(grid[x][y].toString());
    			if (x < width - 1) {
    				builder.append(",");
    			}
    		}
    		builder.append("]");
    		if (y < height - 1) {
    			builder.append(",");
    		}
    	}
    	builder.append("]");
    	return builder.toString();
    }
    
    public static Map fromString(String string) {
    	String stripped = string.substring(1, string.length() - 1);
    	ArrayList<ArrayList<Maptile>> grid = new ArrayList<ArrayList<Maptile>>();
    	int bracketCount = 0;
    	int start = 0;
    	for (int i = 0; i < stripped.length(); ++i) {
    		if ((bracketCount == 0 && stripped.charAt(i) == ',') || i == stripped.length() - 1) {
    			// Found Row
    			String rowStripped;
    			if (stripped.charAt(i) == ',') {
    				rowStripped = stripped.substring(start + 1, i);
    			} else {
    				rowStripped = stripped.substring(start + 1, i + 1);
    			}
    			int innerBracketCount = 0;
    			int innerStart = 0;
    			ArrayList<Maptile> tileRow = new ArrayList<Maptile>();
    			for (int j = 0; j < rowStripped.length(); ++j) {
    				if ((innerBracketCount == 0 && rowStripped.charAt(j) == ',') || j == rowStripped.length() - 1) {
    					Maptile mapTile;
    					if (rowStripped.charAt(j) == ',') {
    						mapTile = Maptile.fromString(rowStripped.substring(innerStart, j));
    					} else {
    						mapTile = Maptile.fromString(rowStripped.substring(innerStart, j + 1));
    					}
    					tileRow.add(mapTile);
    					innerStart = j + 1;
    				} else if (rowStripped.charAt(j) == '[') {
    					++innerBracketCount;
    				} else if (rowStripped.charAt(j) == ']') {
    					--innerBracketCount;
    				}
    			}
    			grid.add(tileRow);
    			start = i + 1;
    		} else if (stripped.charAt(i) == '[') {
    			++bracketCount;
    		} else if (stripped.charAt(i) == ']') {
    			--bracketCount;
    		}
    	}
    	return new Map(MapGenerator.convertArrayListToArray(grid, grid.get(0).size()));
    }

    public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public static void main(String[] args) {
		Maptile[][] maptiles = new Maptile[6][5];
		for (int x = 0; x < 6; ++x) {
			for (int y = 0; y < 5; ++y) {
				maptiles[x][y] = new Maptile();
			}
		}
		maptiles[0][1].setTerrain(new Mountain());
		Map orig = new Map(maptiles);
		Map restored = Map.fromString(orig.toString());
		if (orig.getWidth() != restored.getWidth()) {
			System.out.println("Failed case #1");
		}
		if (orig.getHeight() != restored.getHeight()) {
			System.out.println("Failed case #2");
		}
		for (int x = 0; x < orig.getWidth(); ++x) {
			for (int y = 0; y < orig.getHeight(); ++y) {
				GridLocation gridLocation = new GridLocation(x, y);
				if (orig.getMaptile(gridLocation).toString().equals(restored.getMaptile(gridLocation).toString()) == false) {
					System.out.println("Tiles do not match up at (" + x + "," + y + ")");
				}
			}
		}
	}

}
