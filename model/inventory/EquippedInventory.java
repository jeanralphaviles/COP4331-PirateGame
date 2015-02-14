package model.inventory;

import java.util.ArrayList;

import model.entity.Statistics;
import model.item.Category;
import model.item.Item;


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
        slots = new ArrayList<Slot>();
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // Accessors
    // --------------------------------------------------------
    public ArrayList<Slot> getSlots(){

        return slots;
    }

    // Mutators Methods:
    // -------------------------------------------------------
    public void addSlot(Slot slot){

        this.slots.add(slot);
    }

    // -------------------------------------------------------
    @Override
    public boolean removeItem(Item item) {

        if ( item.getCategory() != Category.TAKEABLE_ITEM){

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
    // -------------------------------------------------------
    @Override
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

	public void augmentStatistics(Statistics statistics) {
		for (Slot s : slots) {
			Item item = s.getItem();
			if (item != null) {
				item.augmentStatistics(statistics);
			}
		}
	}

}
