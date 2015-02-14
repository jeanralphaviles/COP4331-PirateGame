package inventory;

import java.util.ArrayList;

/**
 * Author: Carlos Vizcaino
 * Date: 2/13/2015
 */

public class Inventory{

    // Attributes
    protected ArrayList<Item> items;
    protected  int capacity;


    // Default Constructor
    public Inventory(){

        items = null;
        capacity = -1;
    }

    // Constructor
    public Inventory(int capacity){

        items = null;
        this.capacity = capacity;
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // Accessors
    // --------------------------------------------------------
    public ArrayList<Item> getItems(){return items;}

    // --------------------------------------------------------
    public int getCapacity(){return capacity;}


    // Mutators
    // --------------------------------------------------------
    public void setCapacity(int capacity){ this.capacity = capacity; }

    //-------------------------------------------------------
    public boolean storeItem(Item item){

        if ( items.size() < capacity){

            items.add(items);
            return true;
        }

        return false;
    }

    //-------------------------------------------------------
    public boolean removeItem(Item item){

        // Check if item exists
        for ( Item c: this.items){

            if ( c == item){

                // Remove
                this.items.remove(item);
                return true;
            }
        }

        return false;
    }

    // --------------------------------------------------------
    public boolean hasItem(Item item){

        // Check if item exists
        for ( Item c: this.items){

            if ( c == item){

                return true;
            }
        }

        return false;
    }


    // Abstract

}
