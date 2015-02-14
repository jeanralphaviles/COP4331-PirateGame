package model.inventory;


import model.item.Item;
import model.item.Weapon;
import utility.decal.ItemDefaultDecal;

import java.util.ArrayList;

/**
 * Author: Carlos Vizcaino
 * Date: 2/12/2015
 * Date: 2/13/2015
 * Date: 2/14/2015
 */

public class Inventory{

    // Attributes
    protected ArrayList<Item> items;
    protected  int capacity;


    // Default Constructor
    public Inventory(){

        items = new ArrayList<Item>();
        capacity = 0;
    }

    // Constructor
    public Inventory(int capacity){

        items = new ArrayList<Item>();
        this.capacity = capacity;
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // Accessors
    // --------------------------------------------------------
    public ArrayList<Item> getItems(){

        return items;
    }
    // --------------------------------------------------------
    public int getCapacity(){

        return capacity;
    }
    // --------------------------------------------------------
    public boolean hasItem(Item item){

        return items.contains(item);
    }
    // --------------------------------------------------------
    public boolean isFull(){

        if ( items.size() >= capacity )
            return true;

        return false;
    }
    // Mutators
    // --------------------------------------------------------
    public void setCapacity(int capacity){ this.capacity = capacity; }

    //-------------------------------------------------------
    public boolean storeItem(Item item){

        if ( items.size() < capacity){

            items.add(item);
            return true;
        }

        return false;
    }

    //-------------------------------------------------------
    public boolean removeItem(Item item){

        if ( items.contains(item)){

            this.items.remove(item);
            return true;
        }

        return false;
    }

    // ------------------ TESTING INVENTORY ------------------------
    // ------------------                   ------------------------
    public static void main(String[] args){

        System.out.println("Hello World from Inventory");
        testingTakeableItems();

    }

    // -------------------------------------------------------------
    public static void testingTakeableItems(){

        // Weapon List:

        Weapon pistol = new Weapon( new ItemDefaultDecal(), "Pistol", 5, 5, 5);
        /*
        Weapon spade = new Weapon( new ItemDefaultDecal(), "Spade", 5, 5, 5);
        Weapon stick = new Weapon( new ItemDefaultDecal(), "Stick", 5, 5, 5);
        Weapon arch = new Weapon( new ItemDefaultDecal(), "Arch", 5, 5, 5);
        */
        // Create Inventory:
        Inventory inventory = new Inventory();

        if ( inventory.isFull() )
            System.out.println("Inventory is Full");



    }



}
