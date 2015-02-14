package model.item;

import java.util.ArrayList;

public class TakeableItem extends Item{

    // Atrributes
    protected Category category;

    // Default Constructor
    public TakeableItem(){

        super();
        category = null;

    }
    // Constructor I
    public Item( Category category ){

        super();
        this.category = category;


    }
    // Constructor I
    public Item( Category category, Decal decal ){

        super(decal);
        this.category = category;



    }
    // Constructor III
    public Item( Category category, Decal decal, Statistics statistics){

        super(decal, statistics);
        this.category = category;

    }

    // ---------- METHODS IMPLEMENTATION ---------
    // ----------                         ---------

    // Accessor Methods:
    // --------------------------------------------
    public Category getCategory(){return category; }

    // --------------------------------------------


    // Mutator Methods:
    // --------------------------------------------
    public void setCategory( Category){ this.category = category;}

    @Override
    // --------------------------------------------
    public void setAugementStatistics( Statistics stats){

        // Todo-Code
    }

    // --------------------------------------------

    // Abstract Methods:
    // --------------------------------------------
    public void triggerProximity(Entity entity){

        // Todo-Code
    }

}
