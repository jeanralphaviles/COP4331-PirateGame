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

public abstract class Entity {

    /* Attributes */

    protected Occupation occupation;
    protected Statistics statistics;
    protected Inventory inventory;
    protected EquippedInventory eInventory;
    protected MapTile currentLocation;

    /* Constructors */

    //Simple initialization constructor
    Entity(Occupation occ, Statistics stats, Inventory inv, EquippedInventory eInv ) {
        this.occupation = occ;
        this.statistics = stats;
        this.inventory = inv;
        this.eInventory = eInv;
    }

    /* Get/Set methods for private fields (Auto-generated) */
    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Inventory getInv() {
        return inventory;
    }

    public void setInvetory(Inventory inv) {
        this.inventory = inv;
    }

    public EquippedInventory geteInv() {
        return eInventory;
    }

    public void setEInventory(EquippedInventory eInv) {
        this.eInventory = eInv;
    }

    /* Other methods */
}