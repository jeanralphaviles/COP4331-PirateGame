package model.inventory;

import java.util.ArrayList;

import model.item.Category;
import model.item.Item;
import model.item.TakeableItem;


/**
 * Author: Carlos Vizcaino
 * Date: 2/13/2015
 * Date: 2/14/2015
 *
 *Single slot in inventory for a single item
 *
 */
public class Slot extends Inventory {
    private SlotCategory slotCategory;

    public Slot() {
        super(1);
        setSlotCategory(SlotCategory.ANY_SLOT);
    }

	public Slot(SlotCategory slotCategory) {
        super(1);
        setSlotCategory(slotCategory);
	}
	
	public boolean hasItem() {
		return !items.isEmpty();
	}

    public boolean isItemAllowed(Item item) {
		return items.size() <= capacity
				&& item.getCategory().equals(Category.TAKEABLE_ITEM)
				&& ((TakeableItem) item).getSlotCategory().equals(slotCategory);
    }

    public boolean equipItem(Item item) {
        if (isItemAllowed(item)) {
            items.add(item);
            return true;
        }
        return false;
    }

	public boolean removeItem() {
        if (!items.isEmpty()) {
            items.remove(0);
			return true;
		}
		return false;
	}

    public Item unequipItem() {
        if (!items.isEmpty()) {
            Item item = getItem();
            removeItem();
            return item;
        }
        return null;
    }
    
    @Override
	public String toString() {
    	return "[" + items.toString() + "," + slotCategory + "]";
    }

	public static Slot fromString(String string) {
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
		String rest = stripped.substring(start, stripped.length());
		slot.slotCategory = SlotCategory.valueOf(rest);
				
		return slot;
	}

	public void setSlotCategory(SlotCategory slotCategory ) {
        this.slotCategory = slotCategory;
	}

	public SlotCategory getSlotCategory() {
        return slotCategory;
	}

    public Item getItem() {
        if (!items.isEmpty()) {
            return items.get(0);
        }
        return null;
    }
	
	public static void main(String[] args) {
		Slot orig = new Slot(SlotCategory.RING);
		Item item = new TakeableItem(SlotCategory.RING);
		orig.storeItem(item);
		Slot restored = Slot.fromString(orig.toString());
		
		if (orig.toString().equals(restored.toString()) == false) {
			System.out.println("Serialized Strings differ");
		}
		if (orig.hasItem() != restored.hasItem()) {
			System.out.println("Items are different");
		}
		if (!orig.getSlotCategory().equals(restored.getSlotCategory())) {
			System.out.println("Slot Categories Differ");
		}
		if (orig.getCapacity() != restored.getCapacity() || orig.getCapacity() != 1) {
			System.out.println("Capacities are different");
		}
	}

}
