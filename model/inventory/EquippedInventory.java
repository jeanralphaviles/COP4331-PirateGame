package model.inventory;


import java.util.ArrayList;

import model.item.Item;
import model.item.TakeableItem;

/**
 * Author: Carlos Vizcaino
 * Date: 2/13/2015
 */

public class EquippedInventory extends Inventory{

    // Attributes
    private ArrayList<Slot> slots;


    // Default Constructor
    public EquippedInventory(){

        super();
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // Accessors
    // --------------------------------------------------------
    public ArrayList<Slot> getSlots(){return slots;}

    @Override
    // -------------------------------------------------------
    public boolean hasItem(Item item ){
        // Check every slot
        for ( Slot c : slots){
            // Check every item in that slot
            for( Item i: c.getItems() ) {
                if ( i == item)
                    return true;
            }
        }
        // Else
        return false;
    }

    // -------------------------------------------------------
    @Override
    public boolean storeItem(Item item) {
    	if (!(item instanceof TakeableItem)) {
    		return false;
    	}
    	TakeableItem tempItem = (TakeableItem)item;
        // Iterate through the Slots
        for (Slot s : slots) {
            // If category matches I will save
            if( (tempItem).getCategory() == s.getCategory() ){
                if ( s.isItemAllowed() ){
                    s.storeItem(tempItem);
                    return true;
                }
                return false;
            }
        } // End of each loop
        return false;
    }

    @Override
    public boolean removeItem(Item item) {
    	if (!(item instanceof TakeableItem)) {
    		return false;
    	}
        // For every slot in the equipped inventory
        for (Slot s : slots) {
            if ( s.hasItem(item) ) {
                return s.removeItem(item);
            }
        }
        return false;
    }


    // Mutators
    // -------------------------------------------------------
    public void addSlot(Slot slot){ this.slots.add(slot); }
}
