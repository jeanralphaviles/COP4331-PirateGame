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
    // Constructor I
    public Entity(Decal decal) {

        this();
        this.decal = decal;
    }

    // Constructor II
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

    public Statistics getEffectiveStatistics() {
        Statistics effectiveStats = statistics.clone();
        equippedInventory.augmentStatistics(effectiveStats);
        occupation.augmentStatistics(statistics);
        return effectiveStats;
    }

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

    protected boolean putItemInInventory(Item item) {
        return storeItem(item);
    }

    public boolean move(Maptile maptile) {
        if (maptile.isPassable()) {
            if (maptile.addEntity(this)) {
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
