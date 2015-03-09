package model.inventory;

import model.entity.Statistics;
import model.item.Category;
import model.item.Item;
import model.item.TakeableItem;

import java.util.ArrayList;


/**
 * Author: Carlos Vizcaino
 * Date: 2/13/2015
 * 
 * Stores inventory the entity is wearing and augments stats.
 */
public class EquippedInventory extends Inventory {
    private ArrayList<Slot> slots;
    
    public EquippedInventory() {
        super(SlotCategory.values().length);
        createSlots();
    }

    @Override
    public boolean storeItem(Item item) {
        if (isItemAllowed(item)) {
            TakeableItem takeableItem = (TakeableItem)item;
            for (Slot slot: slots) {
                if (slot.isItemAllowed(item)) {
                    slot.storeItem(takeableItem);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isItemAllowed(Item item) {
		if (item.getCategory().equals(Category.TAKEABLE_ITEM)) {
			for (Slot slot : slots) {
				if (slot.isItemAllowed(item)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasItem(Item item) {
	    for (Slot slot : slots) {
	    	if (slot.getItems().contains(item)) {
	    		return true;
	    	}
	    }
	    return false;
	}

	@Override
	public boolean removeItem(Item item) {
	    for (Slot slot : slots) {
	        if (slot.hasItem(item)) {
	            return slot.removeItem(item);
	        }
	    }
	    return false;
	}

	public void augmentStatistics(Statistics statistics) {
		for (Slot slot : slots) {
			Item item = slot.getItem();
			if (item != null) {
				item.augmentStatistics(statistics);
			}
		}
	}
   
    private void createSlots() {
        slots = new ArrayList<Slot>(SlotCategory.values().length);
        for (SlotCategory category : SlotCategory.values()) {
            slots.add(new Slot(category));
        }
    }

    @Override
	public String toString() {
    	return "[" + slots.toString() + "," + capacity + "]";
    }
    
    public static EquippedInventory fromString(String string) {
    	String stripped = string.substring(1, string.length() - 1);
    	int bracketCount = 0;
    	int start = 0;
    	EquippedInventory equippedInventory = new EquippedInventory();
 		ArrayList<Slot> slots = new ArrayList<Slot>();
    	for (int i = 0; i < stripped.length(); ++i) {
    		if (bracketCount == 0 && stripped.charAt(i) == ',') {
    			String slotsStrip = stripped.substring(start + 1, i - 1);
    			int start2 = 0;
    			for (int j = 0; j < slotsStrip.length(); ++j) {
    				if ((bracketCount == 0 && slotsStrip.charAt(j) == ',') || j == slotsStrip.length() - 1) {
    					Slot slot;
    					if (slotsStrip.charAt(j) == ',') {
    						slot = Slot.fromString(slotsStrip.substring(start2, j));
    					} else {
    						slot = Slot.fromString(slotsStrip.substring(start2, j + 1));
    					}
    					slots.add(slot);
    					start2 = j + 2;
    				} else if (slotsStrip.charAt(j) == '[') {
    					++bracketCount;
    				} else if (slotsStrip.charAt(j) == ']') {
    					--bracketCount;
    				}
    			}
    			bracketCount = 0;
    			start = i + 1;
    		} else if (stripped.charAt(i) == '[') {
    			++bracketCount;
    		} else if (stripped.charAt(i) == ']') {
    			--bracketCount;
    		}
    	}
    	equippedInventory.capacity = Integer.parseInt(stripped.substring(start));
    	equippedInventory.slots = slots;
    	return equippedInventory;
	}
    
    public ArrayList<Slot> getSlots() {
	    return slots;
	}

	@Override
	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		for (Slot s : slots) {
			if (s.hasItem()) {
				items.add(s.getItem());
			}
		}
		return items;
	}

	@Override
	public int getItemCount() {
	    int count = 0;
	    for (Slot s : slots) {
	    	count += s.getItemCount();
	    }
	    return count;
	}

	public static void main(String[] args) {
    	EquippedInventory orig = new EquippedInventory();
    	Item item = new TakeableItem();
    	orig.storeItem(item);
    	EquippedInventory restored = EquippedInventory.fromString(orig.toString());
    	if (orig.toString().equals(restored.toString()) == false) {
    		System.out.println("Serialized strings differ");
    	}
    	if (orig.getItems().get(0).toString().equals(restored.getItems().get(0).toString()) == false) {
    		System.out.println("Items differ");
    	}
    	if (orig.getCapacity() != restored.getCapacity()) {
    		System.out.println("Capacities differ");
    	}
    	if (orig.getSlots().size() != restored.getSlots().size()) {
    		System.out.println("Number of slots differ");
    	}
    }

}
