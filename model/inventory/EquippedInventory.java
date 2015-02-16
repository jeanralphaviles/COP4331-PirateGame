package model.inventory;

import model.entity.Statistics;
import model.item.Category;
import model.item.Item;
import model.item.TakeableItem;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Author: Carlos Vizcaino
 * Date: 2/13/2015
 * 
 * Stores inventory the entity is wearing and augments stats.
 */

public class EquippedInventory extends Inventory{

    // Attributes
    private ArrayList<Slot> slots;

    // Default Constructor
    public EquippedInventory(){


        super( SlotCategory.values().length);
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

        int i = 0;
        for ( Slot s : slots){

            if ( s.isFull() ){

                ++i;
            }
        }

        return i;
    }

    // Mutators Methods:

    //-------------------------------------------------------
    @Override
    public boolean storeItem(Item item){

        // Since every slot has ONLY one item:
        // Then, we check the amount of items
        if ( getTotalItems() < capacity  && item.getCategory() == Category.TAKEABLE_ITEM ){

            TakeableItem tempItem = (TakeableItem)item;
            for (Slot s: slots){
                
                // s.getSlotCategory() == tempItem.getSlotSCategory()
                if ( !s.isFull()  ){

                    s.storeItem(tempItem);
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

            if (s.hasItem(item)) {

                return s.removeItem(item);
            }
        }

        return false;
    }
    // -------------------------------------------------------
    @Override
    public boolean hasItem(Item item ){

        if ( item.getCategory() != Category.TAKEABLE_ITEM){

            return false;
        }

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
    // -------------------------------------------------------

    @Override
	public String toString() {
    	return "[" + items.toString() + "," + slots.toString() + "," + capacity + "]";
    }
    
    public static EquippedInventory fromString(String string) throws IOException {
		String stripped = string.substring(1, string.length() - 1);
		EquippedInventory equippedInventory = new EquippedInventory();
		int bracketCount = 0;
		int start = 0;
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				ArrayList<Item> items = new ArrayList<Item>();
				int start2 = 0;
				String arrayListStripped = stripped.substring(start + 1, i - 1);
				for (int j = 0; j < arrayListStripped.length(); ++j) {
					if (bracketCount == 0 && arrayListStripped.charAt(j) == ',') {
						Item item = Item.fromString(arrayListStripped.substring(start2, j));
						items.add(item);
						start2 = j + 1;
					} else if (arrayListStripped.charAt(j) == '[') {
						++bracketCount;
					} else if (arrayListStripped.charAt(j) == ']') {
						--bracketCount;
					}
				}
				start = i + 1;
				bracketCount = 0;
				equippedInventory.items = items;
				break;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		
		bracketCount = 0;
		for (int i = start; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				ArrayList<Slot> slots = new ArrayList<Slot>();
				int start2 = 0;
				String arrayListStripped = stripped.substring(start + 1, i - 1);
				for (int j = 0; j < arrayListStripped.length(); ++j) {
					if (bracketCount == 0 && arrayListStripped.charAt(j) == ',') {
						Slot slot = Slot.fromString(arrayListStripped.substring(start2, j));
						slots.add(slot);
						start2 = j + 2;
					} else if (arrayListStripped.charAt(j) == '[') {
						++bracketCount;
					} else if (arrayListStripped.charAt(j) == ']') {
						--bracketCount;
					}
				}
				start = i + 1;
				equippedInventory.slots = slots;
				break;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		
		equippedInventory.capacity = Integer.parseInt(stripped.substring(start, stripped.length()));
		return equippedInventory;
	}

}
