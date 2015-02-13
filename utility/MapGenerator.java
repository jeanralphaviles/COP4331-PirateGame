package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import map.Grass;
import map.Map;
import map.Maptile;
import map.Mountain;
import map.Water;

public class MapGenerator {

	public static Map generateMap(File file) {
		ArrayList< ArrayList<Maptile> > grid = new ArrayList< ArrayList<Maptile> >();
		BufferedReader reader = null;
		String line = "";
		String csvSplit = ",";

		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				String[] row = line.split(csvSplit);
				ArrayList<Maptile> rowTiles = new ArrayList<Maptile>();
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
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/* Convert 2d ArrayList to 2d Array */
		return null;
	}

}
