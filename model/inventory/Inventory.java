package model.inventory;


import model.item.Item;
import model.item.Weapon;
import utility.decal.ItemDefaultDecal;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Author: Carlos Vizcaino
 * Date: 2/12/2015
 * Date: 2/13/2015
 * Date: 2/14/2015
 * Date: 2/15/2015
 *
 *
 *
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
    // --------------------------------------------
    public int getTotalItems(){

        return items.size();
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


        if ( items.size() < capacity ) {

            items.add(item);
            return true;
        }

        return false;
    }

    //-------------------------------------------------------
    public boolean removeItem(Item item){

        if ( items.contains(item) ){

            this.items.remove(item);
            return true;
        }

        return false;
    }

    // ------------------ TESTING INVENTORY ------------------------
    // ------------------                   ------------------------
    public static void main(String[] args){

        System.out.println("Hello World from Inventory");

        // Create Inventory:
        Inventory inventory = new Inventory();

        // When inventory has size 0
        testingTakeableItems( inventory );

        // When inventory size is 1
        inventory = new Inventory(5);
        testingTakeableItems( inventory );
    }
    // -------------------------------------------------------------

    private static void testingTakeableItems(Inventory inventory ){

        // Weapon List:
        Weapon pistol = new Weapon( new ItemDefaultDecal(), "Pistol",  5, 5);
        Weapon spade = new Weapon( new ItemDefaultDecal(), "Spade", 5, 5);
        Weapon stick = new Weapon( new ItemDefaultDecal(), "Stick", 5, 5);
        Weapon arch = new Weapon( new ItemDefaultDecal(), "Arch", 5, 5);

        // Full or not
        if ( inventory.isFull() )
            System.out.println("Inventory is Full");
        else
            System.out.println("Inventory is Full");

        // When storing
        if (inventory.storeItem(pistol))
            System.out.println("Item was added successfully");
        else
            System.out.println("Item cannot be added");

        if ( inventory.getCapacity() > 0){

            // Equipped Inventory
            EquippedInventory equippedInventory = new EquippedInventory();
            inventory.storeItem(spade);
            inventory.storeItem(stick);
            inventory.storeItem(arch);

            // Get item
            ArrayList<Item> items = inventory.getItems();

            // Add two items from inventory to equipped inventory
            int half = inventory.getTotalItems()/2;
            for ( int i = 0 ; i < half; i++ ){

                // Add to Equipped Inventory
               boolean itemSuccessfullyEquipped = equippedInventory.storeItem( items.get(0) );


                // Remove from inventory
                if (itemSuccessfullyEquipped )
                    inventory.removeItem( items.get(0) );
            }
            // Print result
            printInventory(inventory);
            printEquippedInventory(equippedInventory);
        }
    }
    // ------------------------------------------------------------
    private static void printInventory(Inventory inventory){

        // Printing items
        System.out.println("Printing Inventory...");
        ArrayList<Item> items = inventory.getItems();
        for ( Item i : items){

            System.out.println( i.getName() );
        }
    }
    // ------------------------------------------------------------
    private static void printEquippedInventory(EquippedInventory inventory){

        // Printing items
        System.out.println("Printing EquipedIventory Inventory...");
        ArrayList<Slot> slots = inventory.getSlots();
      try{
          for (Slot s : slots) {

              if ( s == null )
                  throw new IOException("Something went wrong!");

              try {

                  if (s.isFull()) {

                      if ( s.getItem() == null)
                          throw new IOException("Item is null");

                      System.out.println(s.getItem().getName());
                  }
              }
              catch (IOException e){

                  System.out.println(e.getMessage() );
              }
          }
      }
      catch (IOException e ){

          System.out.print(e.getMessage());
      }
    }

} // End of Inventory class
