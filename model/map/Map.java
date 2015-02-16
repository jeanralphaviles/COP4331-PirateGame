package model.map;

import java.util.HashMap;

import utility.Course;

public class Map {

    /*Properties*/
    
    private int height = 3, width = 3;
    private Maptile[][] grid = new Maptile[width][height];
    private HashMap<Integer, GridLocation> tileLocations = new HashMap<Integer, GridLocation>(); /* Associates Maptiles with GridLocations */

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
        this.height = grid[0].length;
        this.width = grid.length;

        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                tileLocations.put(this.grid[x][y].hashCode(), new GridLocation(x, y));
            }
        }
    }
    
    /*Methods*/
     /** returns maptile based on x and y coordinate of maptile
     * @param x - x coordinate
     * @param y - y coordinate
     * @return - returns maptile
     */
    public Maptile getMapTile(int x, int y) {
        if (x < 0 || x >= width) {
            return null;
        }
        if (y < 0 || y >= height) {
            return null;
        }
        return grid[x][y];
    }
    /** depending on course passed in and starting point the map tile you will end up on will be returned
     * @param start - the starting postion of entity
     * @param course - the course/displacement the entity wishes to move
     * @return - the map tile they will end up on
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
     /** get location of given maptile
     * @param maptile - maptile you want location of
     * @return - location on map
     */
    public GridLocation getGridLocation(Maptile maptile) {
        return tileLocations.get(maptile.hashCode());
    }
    
    /*Get-Sets*/

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

    public Maptile[][] getGrid() {
        return grid;
    }

    public void setGrid(Maptile[][] grid) {
        this.grid = grid;
    }

    public HashMap<Integer, GridLocation> getTileLocations() {
        return tileLocations;
    }

    public void setTileLocations(HashMap<Integer, GridLocation> tileLocations) {
        this.tileLocations = tileLocations;
    }

    
    
}
