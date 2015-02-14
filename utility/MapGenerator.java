package utility;

import model.map.Map;
import model.map.Maptile;

import java.io.*;
import java.util.ArrayList;

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
                /*
				for (int j = 0; j < row.length; ++j) {
					Maptile tile = new Maptile();

					switch (row[j]) {
					case "Grass":
						tile.setTerrain(new Grass());
						break;
					case "Water":
						tile.setTerrain(new Water());
						break;
					case "Mountain":
						tile.setTerrain(new Mountain());
						break;
					default:
						break;
					}
					rowTiles.add(tile);
				}
				*/

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

	private static Maptile[][] convertArrayListToArray(
			ArrayList<ArrayList<Maptile>> grid, int length) {
		Maptile[][] customGrid = new Maptile[length][grid.size()];

		for (int i = 0; i < grid.size(); ++i) {
			Maptile[] mapRow = (Maptile[]) (grid.get(i)).toArray();

			for (int j = 0; j < length; ++j) {
				if (mapRow[j] != null) {
					customGrid[j][i] = mapRow[j];
				} else {
					customGrid[j][i] = new Maptile();
				}
			}
		}
		return customGrid;
	}
}
