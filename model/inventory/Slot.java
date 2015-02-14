package model.inventory;

import model.item.Category;
import model.item.Item;


/**
 * Author: Carlos Vizcaino
 * Date: 2/13/2015
 */

public class Slot extends Inventory {

    // Attributes
    private Category category;

    // Default Constructor
    public Slot(){

        super();
        setCategory(null);
        setCapacity(1);
    }
    // Constructor
	public Slot(Category category) {
        super();
        setCategory(category);
        setCapacity(1);
	}

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // Accessors
    // --------------------------------------------------------
	public Category getCategory() {
		return category;
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

             items.get(0);
        }

        return null;
    }

    // Mutators
    // --------------------------------------------------------
	public void setCategory(Category category) {
		this.category = category;
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
