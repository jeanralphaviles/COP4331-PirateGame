package model.inventory;

import model.item.Item;


/**
 * Author: Carlos Vizcaino
 * Date: 2/13/2015
 * Date: 2/14/2015
 *
 */

public class Slot extends Inventory {

    // Attributes
    private SlotCategory slotCategory;

    // Default Constructor
    public Slot() {

        super();
        setSlotCategory(SlotCategory.ANY_SLOT);
        setCapacity(1);
    }
    // Constructor I
	public Slot(SlotCategory slotCategory) {
        super();
        setSlotCategory( slotCategory );
        setCapacity(1);
	}


    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // Accessors
    // --------------------------------------------------------
	public SlotCategory getSlotCategory() {

        return slotCategory;
	}

    // --------------------------------------------------------
    public boolean isItemAllowed(){

        // Again: ONLY one item is allowed per slot
        if ( items.isEmpty() )
            return true;

        return false;
    }

    // --------------------------------------------------------
    public Item getItem() {

        // If this slot has an item
        if (!items.isEmpty() ){

            return  items.get(0);
        }

        return null;
    }

    // --------------------------------------------------------
	public boolean removeItem() {

        if ( !items.isEmpty() ) {

            items.clear();
			return true;
		}
		return false;
	}


    // Mutators Methods:
    // --------------------------------------------------------
	public void setSlotCategory(SlotCategory slotCategory ) {

        this.slotCategory = slotCategory;
	}
    // --------------------------------------------------------
    public Item unquippedItem() {

        // Remove it and return it
        if ( !items.isEmpty() ){
            Item temp = items.get(0);
            items.clear();
            return temp;
        }
        return null;
    }
}
