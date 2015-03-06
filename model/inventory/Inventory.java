package model.inventory;


import model.item.Item;
import model.item.TakeableItem;

import java.util.ArrayList;

/**
 * Author: Carlos Vizcaino
 * Date: 2/12/2015
 * Date: 2/13/2015
 * Date: 2/14/2015
 * Date: 2/15/2015
 *
 *Holds items entity owns/took
 *
 */
public class Inventory {
    protected ArrayList<Item> items;
    protected int capacity;

    public Inventory() {
        items = new ArrayList<Item>(0);
        capacity = 0;
    }
    
    public Inventory(int capacity) {
        items = new ArrayList<Item>(capacity);
        this.capacity = capacity;
    }

    public boolean storeItem(Item item) { 
	    if (!isFull()) {
	        items.add(item);
	        return true;
	    }
	    return false;
	}

	public boolean removeItem(Item item) {
	    if (items.contains(item)) {
	        items.remove(item);
	        return true;
	    }
	    return false;
	}

	public boolean hasItem(Item item) {
	    return items.contains(item);
	}

	public boolean isFull() {
		return items.size() >= capacity;
	}

	public ArrayList<Item> getItems() {
        return items;
    }
    
    public int getItemCount() {
        return items.size();
    }

    
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
    	this.capacity = capacity;
    }
    
    @Override
	public String toString() {
    	return "[" + items.toString() + "," + capacity + "]";
    }

	public static Inventory fromString(String string) {
		String stripped = string.substring(1, string.length() - 1);
		Inventory inventory = new Inventory();
		int bracketCount = 0;
		int start = 0;
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				ArrayList<Item> items = new ArrayList<Item>();
				int start2 = 0;
				String arrayListStripped = stripped.substring(start + 1, i - 1);
				for (int j = 0; j < arrayListStripped.length(); ++j) {
					if ((bracketCount == 0 && arrayListStripped.charAt(j) == ',') || j == arrayListStripped.length() - 1) {
						Item item;
						if (arrayListStripped.charAt(j) == ',') {
							item = Item.fromString(arrayListStripped.substring(start2, j));
						} else {
							item = Item.fromString(arrayListStripped.substring(start2, j + 1));
						}
						items.add(item);
						start2 = j + 1;
					} else if (arrayListStripped.charAt(j) == '[') {
						++bracketCount;
					} else if (arrayListStripped.charAt(j) == ']') {
						--bracketCount;
					}
				}
				start = i + 1;
				inventory.items = items;
				break;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		inventory.capacity = Integer.parseInt(stripped.substring(start, stripped.length()));
		return inventory;
	}

    public static void main(String[] args) {
		Inventory orig = new Inventory(10);
		orig.storeItem(new TakeableItem());
		Inventory restored = Inventory.fromString(orig.toString());
		
		if (orig.toString().equals(restored.toString()) == false) {
			System.out.println("Serialized Strings are different");
		}
		if (orig.getCapacity() != restored.getCapacity()) {
			System.out.println("Capacities are different");
		}
		if (orig.getItems().get(0).toString().equals(restored.getItems().get(0).toString()) == false) {
			System.out.println("Items differ");
		}
	}
}
