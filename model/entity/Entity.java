package model.entity;

import model.entity.occupation.Occupation;
import model.entity.occupation.Smasher;
import model.inventory.EquippedInventory;
import model.inventory.Inventory;
import model.inventory.Slot;
import model.item.Item;
import model.map.Maptile;
import utility.decal.Decal;
import utility.decal.DefaultEntityDecal;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jean-Ralph Aviles and Carlos Vizcaino
 */
public class Entity {

    protected Inventory inventory;
    protected EquippedInventory equippedInventory;
    protected Occupation occupation;
    protected Statistics statistics;
    protected Maptile maptile;
    protected Decal decal;

    public Entity() {
        this.decal = new DefaultEntityDecal();
        occupation = new Smasher();
        statistics = new Statistics();
        inventory = new Inventory( statistics.getInventoryCapacity() );
        equippedInventory = new EquippedInventory();
    }

    public Entity(Decal decal) {

        this();
        this.decal = decal;
    }


    public Entity( Decal decal, Statistics statistics ){

        this.decal = decal;
        this.statistics = statistics;
    }

    // Accessors:
    // ----------------------------------------------------
    public Inventory getInventory() {return inventory; }
    
    // ----------------------------------------------------
    public EquippedInventory getEquippedInventory(){ return equippedInventory; }
    
    // ----------------------------------------------------
    public ArrayList<Item> getEquippedInventoryItems() {

        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<Slot> slots = equippedInventory.getSlots();
        for( Slot s : slots ){

            // When the slot does actually have an item
            if ( s.isFull() ){

                items.add(s.getItem());
            }
        }

        return items;
    }

    public int getMaxEquippedItems() {
        return 15 + (statistics.getLevel() - 1) * 2;
    }

    public boolean storeItem(Item item) {
        return inventory.storeItem(item);
    }

    public boolean equipItem(Item item) {
        return equippedInventory.storeItem(item);
    }
    
    public boolean hasItem(Item item) {
        if (inventory != null && inventory.hasItem(item)) {
            return true;
        }
        if (equippedInventory != null && equippedInventory.hasItem(item)) {
            return true;
        }
        return false;
    }
    /**
     * get effective stats returns statistics object.
     * This stats has been modified depending on:
     * equiped items, level, occupation, etc.
     */

    public Statistics getDerivedStatistics() {
        Statistics effectiveStats = statistics.clone();
        equippedInventory.augmentStatistics(effectiveStats);
        occupation.augmentStatistics(statistics);
        return effectiveStats;
    }
 /**drop item stores item on map. removes item from inventory/equipped inventory depending on where it is.
     * @param item - item to be removed from inventory/equiped item and placed on map.
     * @return - returns true if item managed to drop
     */
   
    public boolean dropItem(Item item) {
        if (!hasItem(item)) {
            return false;
        }
        if (maptile.getItemSlot().isItemAllowed()) {
            if (maptile.getItemSlot().storeItem(item)) { /* Try to store item in maptile */
                if (inventory != null && inventory.hasItem(item)) { /* Remove item from inventory if it has it */
                    inventory.removeItem(item);
                } else { /* Else item is in equippedInventory, remove it from there */
                    equippedInventory.removeItem(item);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * @param item - item is attempted to be placed in inventory
     * @return - true if item is able to be placed in inventory
     */
    protected boolean putItemInInventory(Item item) {
        return storeItem(item);
    }

    /** entity movement
     * @param maptile - maptile they attempt to move onto
     * @return - returns true if they were able to move onto tile.
     */

    public boolean move(Maptile maptile) {
        if (maptile != null && maptile.isPassable()) {
            if (maptile.addEntity(this)) {
            	if (this.maptile != null) {
                    this.maptile.removeEntity();
                }
                this.maptile = maptile;
                return true;
            }
        }
        return false;
    }

	public Maptile getMaptile() {
        return maptile;
    }

    public void setMaptile(Maptile maptile) {
        this.maptile = maptile;
    }

    public Decal getDecal() {
        return decal;
    }

    public void setDecal(Decal decal) {
        this.decal = decal;
    }

    public Statistics getStatistics() {
        return statistics;
    }
    // ----------------------------------------------------
    public boolean unequipItem(Item item ){

        if ( equippedInventory.hasItem(item)){

            equippedInventory.removeItem(item);
            inventory.storeItem(item);
            return true;
        }
        return false;
    }
    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
    
    @Override
	public String toString() {
    	return "[" + inventory.toString() + "," + equippedInventory.toString() + "," + (occupation != null ? occupation.toString() : "[WishWeHadGSON]") + "," + statistics.toString() + "," + (maptile != null ? maptile.toString() : "[WishWeHadGSON]") + "," + decal.toString() + "]";
    }

	public static Entity fromString(String string) throws IOException {
		String stripped = string.substring(1, string.length() - 1);
		int bracketCount = 0;
		int start = 0;
		int itemCount = 0;
		Entity entity = new Entity();
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				if (itemCount == 0) {
					Inventory inventory = Inventory.fromString(stripped.substring(start, i));
					entity.inventory = inventory;
				} else if (itemCount == 1) {
					EquippedInventory equippedInventory = EquippedInventory.fromString(stripped.substring(start, i));
					entity.equippedInventory = equippedInventory;
				} else if (itemCount == 2) {
					Occupation occupation = Occupation.fromString(stripped.substring(start, i));
					entity.occupation = occupation;
				} else if (itemCount == 3) {
					Statistics statistics = Statistics.fromString(stripped.substring(start, i));
					entity.statistics = statistics;
				} else if (itemCount == 4) {
					if (stripped.substring(start, i) != "WishWeHadGSON") {
						Maptile maptile = Maptile.fromString(stripped.substring(start, i));
						entity.maptile = maptile;
					} else {
						entity.maptile = null;
					}
				} else if (itemCount == 5) {
					Decal decal = Decal.fromString(stripped.substring(start, i));
					entity.decal = decal;
					break;
				}
				++itemCount;
				start = i + 1;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		return entity;
	}


}
