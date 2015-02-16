package model.inventory;

import java.io.IOException;
import java.util.ArrayList;

import model.item.Item;


/**
 * Author: Carlos Vizcaino
 * Date: 2/13/2015
 * Date: 2/14/2015
 *
 *Single slot in inventory for a single item
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
    
    @Override
	public String toString() {
    	return "[" + items.toString() + "," + capacity + "," + slotCategory + "]";
    }

	public static Slot fromString(String string) throws IOException {
		String stripped = string.substring(1, string.length() - 1);
		Slot slot = new Slot();
		int bracketCount = 0;
		int start = 0;
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				ArrayList<Item> items = new ArrayList<Item>();
				String arrayListStripped = stripped.substring(start + 1, i - 1);
				if (arrayListStripped.length() != 0) {
					Item item = Item.fromString(arrayListStripped);
					items.add(item);
				}
					
				start = i + 1;
				slot.items = items;
				break;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			} else if (bracketCount == 0) {
				slot.items = new ArrayList<Item>();
				start = i + 1;
				break;
			}
		}
		String[] rest = stripped.substring(start, stripped.length()).split(",");
		slot.capacity = Integer.parseInt(rest[0]);
		slot.slotCategory = SlotCategory.valueOf(rest[1]);
				
		return slot;
	}

} // End of class
