package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import utility.decal.Decal;
import model.map.Map;
import model.entity.Statistics;
import model.inventory.Slot;
import model.inventory.SlotCategory;
import model.item.InteractiveItem;
import model.item.Item;
import model.item.Category;
import model.item.ObstacleItem;
import model.item.OneShotItem;
import model.item.TakeableItem;

/**
 * @author Jean-Ralph Aviles
 */
public class ItemGenerator {

	public static Slot[][] generateItems(File file, Map map) {
		int width = map.getWidth();
		int height = map.getHeight();
		Slot[][] slots = new Slot[width][height];
		for (int x = 0; x < width; ++x) {
			for (int y = 0; y < height; ++y) {
				slots[x][y] = new Slot(SlotCategory.ANY_SLOT);
			}
		}
		BufferedReader reader = null;
		String line = "";
		String lineSplit = "\\r?\\n";
		String tupleSplit = ",";
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				String[] itemSpecs = line.split(lineSplit); // Items
				for (String itemSpec : itemSpecs) { // For every item tuple
					Item item;
					String[] itemArgs = itemSpec.split(tupleSplit); // Split
																	// individual
																	// attributes
					for (String arg : itemArgs) {
						arg = arg.trim(); // Trim whitespace from arguments
					}
					int i = 0; // Argument Iterator
					String category = itemArgs[i++]; // 0
					switch (Category.valueOf(category)) { // Create correct item
															// based on category
					case INTERACTIVE_ITEM:
						item = new InteractiveItem(); // Will Add Experience
						break;
					case OBSTACLE_ITEM:
						item = new ObstacleItem();
						break;
					case ONE_SHOT_ITEM: // Will Add Health and Vanish
						item = new OneShotItem();
						break;
					case TAKEABLE_ITEM:
						item = new TakeableItem();
						break;
					default:
						System.out.println("Can't determine Category of item!");
						throw new Exception();
					}
					item.setName(itemArgs[i++]); // 1 Get Name
					if (item.getCategory() == Category.TAKEABLE_ITEM) { // Set
																		// slot
																		// category
																		// if
																		// needed
						((TakeableItem) item).setSlotCategory(SlotCategory
								.valueOf(itemArgs[i++])); // (2) possibly
					}

					File decalFile = new File(itemArgs[i++]); // 2 (3)
					Decal decal = new Decal(decalFile);
					item.setDecal(decal); // Set item Decal
					int xCoord = Integer.parseInt(itemArgs[i++]); // 3 (4)
					int yCoord = Integer.parseInt(itemArgs[i++]); // 4 (5)
					int strength = Integer.parseInt(itemArgs[i++]); // 5 (6)
					int agility = Integer.parseInt(itemArgs[i++]); // 6 (7)
					int intellect = Integer.parseInt(itemArgs[i++]); // 7 (8)
					int hardiness = Integer.parseInt(itemArgs[i++]); // 8 (9)

					Statistics stats = new Statistics(0, strength, agility,
							intellect, hardiness, 0, 0, 0, 0);
					item.setAugmentStatistics(stats); // Set Statistics
					if (xCoord >= 0 && xCoord < width && yCoord >= 0 && yCoord < height) { // Valid tile
						slots[xCoord][yCoord].storeItem(item);  // Add it, ignore
																// errors
					}
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return slots;
	}

	public static void main(String[] args) {
		Map map = MapGenerator.generateMap(new File("TestData/TestMap_1.csv"));
		try {
			Slot[][] slots = generateItems(new File("TestData/TestItems_1.csv"), map);
			// Burrito
			if (!slots[2][2].hasItem()) {
				System.out.println("Burrito not there");
			}
			Item burrito = slots[2][2].getItem();
			if (!burrito.getName().equals("Burrito")) {
				System.out.println("Burrito is not named correctly");
				System.out.println("Burrito is named " + burrito.getName());
			}
			if (burrito.getAugmentStatistics().getStrength() != 10
					|| burrito.getAugmentStatistics().getAgility() != 10
					|| burrito.getAugmentStatistics().getIntellect() != 10
					|| burrito.getAugmentStatistics().getHardiness() != 10) {
				System.out.println("Statistics for Burrito not set correctly");
			}
			// TheOneRing from the Lord of the Rings
			if (!slots[4][4].hasItem()) {
				System.out.println("TheOneRing is not placed");
			}
			Item theOneRing = slots[4][4].getItem();
			if (!theOneRing.getName().equals("TheOneRing")) {
				System.out.println("The OneRing is named " + theOneRing.getName());
			}
			if (theOneRing.getAugmentStatistics().getStrength() != 4
					|| theOneRing.getAugmentStatistics().getAgility() != 4 
					|| theOneRing.getAugmentStatistics().getIntellect() != 4 
					|| theOneRing.getAugmentStatistics().getHardiness() != 4) {
				System.out.println("Statistics for TheOneRing not set correctly");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
