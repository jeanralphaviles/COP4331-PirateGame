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
     public ObstacleItem( Decal decal, String name){

       super( decal, Category.OBSTACLE_ITEM, name);

     }
    // Constructor II
    public ObstacleItem( Decal decal, String name, Statistics statistic){

        super( decal, Category.OBSTACLE_ITEM, name, statistic);

     }

    // ---------- METHODS IMPLEMENTATION ---------
    // ----------                         ---------

    // Accessor Methods:
    // --------------------------------------------

    // Mutator Methods:
    // --------------------------------------------


    // Abstract Methods:
    // --------------------------------------------
 /** makes tile impassible.
     */
    @Override
	public void triggerProximity(Entity entity){

        // Todo-Code
    }

}
