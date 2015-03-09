package utility;

import model.map.GridLocation;
import model.map.Map;
import model.map.Maptile;
import model.map.terrain.Grass;
import model.map.terrain.Mountain;
import model.map.terrain.Water;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Jean-Ralph Aviles
 */
public class MapGenerator {

	public static Map generateMap(File file) {
		ArrayList<ArrayList<Maptile>> grid = new ArrayList<ArrayList<Maptile>>();
		BufferedReader reader = null;
		String line = "";
		String csvSplit = ",";
		int longest = 0;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(csvSplit);
				ArrayList<Maptile> rowTiles = new ArrayList<Maptile>();
				longest = row.length > longest ? row.length : longest;
				for (int i = 0; i < row.length; ++i) {
					Maptile tile = new Maptile();
					if (row[i].equals("Water")) {
                        tile.setTerrain(new Water());
                    } else if (row[i].equals("Mountain")) {
                        tile.setTerrain(new Mountain());
                    } else {
                        tile.setTerrain(new Grass());
                    }
					rowTiles.add(tile);
				}
				longest = rowTiles.size() > longest ? rowTiles.size() : longest;
				grid.add(rowTiles);
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		Maptile[][] customGrid = convertArrayListToArray(grid, longest);
		return new Map(customGrid);
	}

	public static Maptile[][] convertArrayListToArray(
			ArrayList<ArrayList<Maptile>> grid, int length) {
		Maptile[][] customGrid = new Maptile[length][grid.size()];
		for (int y = 0; y < grid.size(); ++y) {
			for (int x = 0; x < length; ++x) {
				if (x < grid.get(y).size() && grid.get(y).get(x) != null) {
					customGrid[x][y] = grid.get(y).get(x);
				} else {
					customGrid[x][y] = new Maptile();
				}
			}
		}
		return customGrid;
	}
	
	public static void main(String[] args) {
		File file = new File("TestData/TestMap_1.csv");
		Map map = MapGenerator.generateMap(file);
		if (map.getHeight() != 19) {
			System.out.println("MapGenerator's Height is wrong");
		}
		if (map.getWidth() != 30) {
			System.out.println("MapGenerator's Width is wrong");
		}
		if (map.getMaptile(new GridLocation(2, 2)).getTerrain().getClass().getName() != "model.map.terrain.Water") {
			System.out.println("Tile not made correctly #1");
		}
		if (map.getMaptile(new GridLocation(22, 15)).getTerrain().getClass().getName() != "model.map.terrain.Mountain") {
			System.out.println("Tile not made correctly #2");
		}
	}
}
