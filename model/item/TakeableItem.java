package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;

public class TakeableItem extends Item{

    // Atrributes
    protected Category category;

    // Default Constructor
    public TakeableItem(){

        super();
        category = null;

    }
    // Constructor I
    public TakeableItem( Category category ){

        super();
        this.category = category;


    }
    // Constructor I
    public TakeableItem( Category category, Decal decal ){

        super(decal);
        this.category = category;



    }
    // Constructor III
    public TakeableItem( Category category, Decal decal, Statistics statistics){

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
    public void setCategory( Category category){ this.category = category;}

    @Override
    // --------------------------------------------
    public void setAugmentStatistics( Statistics stats){

        // Todo-Code
    }

    // --------------------------------------------

    // Abstract Methods:
    // --------------------------------------------
    public void triggerProximity(Entity entity){

        // Todo-Code
    }

}
