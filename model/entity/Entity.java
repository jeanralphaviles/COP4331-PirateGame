package model.entity;

import model.entity.occupation.Occupation;
import model.entity.occupation.Smasher;
import model.inventory.EquippedInventory;
import model.inventory.Inventory;
import model.item.Item;
import model.map.Maptile;
import utility.decal.Decal;
import utility.decal.DefaultEntityDecal;

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

    public Statistics getEffectiveStatistics() {
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
        if (maptile.isPassable()) {
            if (maptile.addEntity(this)) {
            	this.maptile.removeEntity();
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

}
