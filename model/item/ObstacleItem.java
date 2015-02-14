package model.item;

import model.entity.Entity;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;
import model.entity.Statistics;


public class ObstacleItem extends Item{

    // Atrributes

    // Default Constructor
    public ObstacleItem(){

       super( new ItemDefaultDecal(), Category.OBSTACLE_ITEM );

    }
    // Constructor I
     public ObstacleItem( Decal decal){

       super( decal, Category.OBSTACLE_ITEM);

     }
     // Constructor II
     public ObstacleItem( Decal decal, Statistics statistics){

       super( decal, Category.OBSTACLE_ITEM, statistics);

     }

    // ---------- METHODS IMPLEMENTATION ---------
    // ----------                         ---------

    // Accessor Methods:
    // --------------------------------------------

    // Mutator Methods:
    // --------------------------------------------


    // Abstract Methods:
    // --------------------------------------------
    public void triggerProximity(Entity entity){

        // Todo-Code
    }

}
