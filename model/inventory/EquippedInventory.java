package model.inventory;

import model.entity.Statistics;
import model.item.Category;
import model.item.Item;
import model.item.TakeableItem;

import java.util.ArrayList;


/**
 * Author: Carlos Vizcaino
 * Date: 2/13/2015
 */

public class EquippedInventory extends Inventory{

    // Attributes
    private ArrayList<Slot> slots;
    private int totalItems;

    // Default Constructor
    public EquippedInventory(){

        super();
        totalItems = 0;
        createSlots();
    }
    // Constructor I
    public EquippedInventory(int capacity ){

        super(capacity);
        totalItems = 0;
        createSlots();
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // Accessors
    // --------------------------------------------------------
    public ArrayList<Slot> getSlots(){

        return slots;
    }
    // --------------------------------------------------------
    @Override
    public int getTotalItems(){

        return totalItems;
    }

    // Mutators Methods:
    // -------------------------------------------------------
    public void addSlot(Slot slot){

        this.slots.add(slot);
    }

    //-------------------------------------------------------
    @Override
    public boolean storeItem(Item item){

        // Since every slot has ONLY one item:
        // Then, we check the amount of items
        if ( totalItems < capacity  && item.getCategory() == Category.TAKEABLE_ITEM ){

            TakeableItem tempItem = (TakeableItem)item;
            for (Slot s: slots){

                if ( !s.isFull() && s.getSlotCategory() == tempItem.getSlotSCategory() ){

                    s.storeItem(tempItem);
                    ++totalItems;
                    return true;
                }
            }
        }

        return false;
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
    // -------------------------------------------------------
    private void createSlots(){

        slots = new ArrayList<Slot>();
        for ( SlotCategory i : SlotCategory.values() ){

            slots.add( new Slot(i) );
        }
    }

}
