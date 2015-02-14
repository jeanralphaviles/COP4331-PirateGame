package model.entity;

/*
* Created by Conor Doherty
* on 2/11/2015
* Last edited: 2/11/15 by Conor Doherty
*
* The Entity abstract class is designed to be a superclass of any entity type in the game, including
* the player's Avatar.
*/

/*
* Changelog:
* 2/11/15 Added file and started skeleton/overall view - Conor Doherty
*/

import utility.decal.Decal;
import model.entity.occupation.Occupation;
import model.inventory.EquippedInventory;
import model.inventory.Inventory;
import model.item.Item;
import model.map.Maptile;

public abstract class Entity {

    /* Attributes */

    protected Occupation occupation;
    protected Statistics statistics;
    protected Inventory inventory;
    protected EquippedInventory equippedInventory;
    protected Maptile mapTile;
    protected Decal decal;
    //protected int maxEquippedItems; <-- Now stats.maxEquippedItems()

    /* Constructors */

    //Simple initialization constructor
    Entity(Occupation occ, Statistics stats, Inventory inv, EquippedInventory eInv, Decal decal) {
        this.occupation = occ;
        this.statistics = stats;
        this.inventory = inv;
        this.equippedInventory = eInv;
        this.decal = decal;
    }

    /* Get/Set methods for private fields (Auto-generated) */
    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Statistics getBaseStatistics() {
        return statistics;
    }

    public void setBaseStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inv) {
        this.inventory = inv;
    }

    public EquippedInventory getEquippedInventory() {
        return equippedInventory;
    }

    public void setEquippedInventory(EquippedInventory eInv) {
        this.equippedInventory = eInv;
    }

    /* Other methods */

    @SuppressWarnings("unused")
	private boolean equipItem(Item item) {
        //code goes here
        return true;
    }

    public boolean storeItem(Item item) {
        //code goes here
        return true;
    }

    //Gets final statistics, including equipped inv and base stats
    public Statistics getFullStatistics() { return statistics; }

    public boolean dropItem(Item item) {
        //code goes here
        return true;
    }

    public boolean putItemInInventory(Item item) {
        //code goes here
        return true;
    }

	public Decal getDecal() {
		return decal;
	}

	public void setDecal(Decal decal) {
		this.decal = decal;
	}

    public Maptile getMapTile() {
		return mapTile;
	}

	public void setMapTile(Maptile mapTile) {
		this.mapTile = mapTile;
	}

    public boolean move(Maptile mapTile) {
        //code goes here
        return true;
    }
    
    public Maptile getMaptile() {
    	return mapTile;
    }
   
    public int maxEquippedItems() {
        //code goes here
        return 0;
    }

    //Ded yet?
    public boolean isDead() {
        return (statistics.getHealth() == 0);
    }

    //Die
    public void kill() {
        statistics.setHealth(0);
    }
}
