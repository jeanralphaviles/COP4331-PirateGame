package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;


public class OneShotItem extends Item{

    // Atrributes

    // Default Constructor
    public OneShotItem(){

        super( new ItemDefaultDecal(), Category.ONE_SHOT_ITEM);

    }
    // Constructor I
    public OneShotItem( Decal decal){

        super( decal, Category.ONE_SHOT_ITEM);

    }
    // Constructor II
    public OneShotItem( Decal decal, Statistics statistics){

        super( decal, Category.ONE_SHOT_ITEM, statistics);

    }

    // ---------- METHODS IMPLEMENTATION ---------
    // ----------                         ---------

    // Accessor Methods:
    // --------------------------------------------

    // Mutator Methods:
    //---------------------------------------------

    // Abstract Methods:
    // --------------------------------------------
    public void triggerProximity(Entity entity){

        // Todo-Code
    }

}
