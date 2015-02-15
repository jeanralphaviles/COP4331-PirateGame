package model.map;

import java.util.HashMap;

import utility.Course;

public class Map {

    /*Properties*/
    
    private int height = 3, width = 3;
    private Maptile[][] grid = new Maptile[width][height];
    private HashMap<Integer, GridLocation> tileLocations = new HashMap<Integer, GridLocation>(1); /* Associates Maptiles with GridLocations */

    /*Constructors*/
    
    public Map() {
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
    
    /*Methods*/
    
    public Maptile getMapTile(int x, int y) {
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
        GridLocation startLocation = getGridLocation(start);

        if (startLocation == null) {
            return null;
        }
        int destX = startLocation.getX() + xOffset;
        int destY = startLocation.getY() + yOffset;

        return getMapTile(destX, destY);
    }
    
    public GridLocation getGridLocation(Maptile maptile) {
        return tileLocations.get(maptile.hashCode());
    }
    
    /*Get-Sets*/

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    
}
