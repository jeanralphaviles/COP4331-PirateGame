package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.map.Map;
import model.map.Maptile;
import model.map.terrain.Grass;
import model.map.terrain.Mountain;
import model.map.terrain.Water;

public class MapGenerator {

	public static Map generateMap(File file)
	{
		ArrayList< ArrayList<Maptile> > grid = new ArrayList< ArrayList<Maptile> >();

		BufferedReader reader = null;
		String line = "";
		String csvSplit = ",";
		int longest = 0;

		try {
			reader = new BufferedReader(new FileReader(file));

			while ((line = reader.readLine()) != null) {
				String[] row = line.split(csvSplit);
				ArrayList<Maptile> rowTiles = new ArrayList<Maptile>();

				if (row.length > longest) { longest = row.length; }

				for (int j = 0; j < row.length; ++j) {
                    Maptile tile = new Maptile();

					switch(row[j]) {

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

				grid.add(rowTiles);
			}

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		Maptile[][] customGrid = convertArrayListToArray(grid, longest);

		return null;
	}

	private static Maptile[][] convertArrayListToArray(ArrayList<ArrayList<Maptile>> grid, int length)
	{
		Maptile[][] customGrid = new Maptile[length][grid.size()];

		for (int i = 0; i < grid.size(); ++i) {
			Maptile[] mapRow = (Maptile[]) (grid.get(i)).toArray();

			for (int j = 0; j < length; ++j) {
				if (mapRow[j] != null) { customGrid[j][i] = mapRow[j]; }
				else { customGrid[j][i] = new Maptile(); }
			}
		}

		return customGrid;
	}
}
