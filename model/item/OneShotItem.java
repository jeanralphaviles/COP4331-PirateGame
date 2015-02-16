package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;

   /* Dissapears off map after activation. Does something one time.
     */
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
    public OneShotItem( Decal decal, String name){

        super( decal, Category.ONE_SHOT_ITEM, name);

    }
    // Constructor II
    public OneShotItem( Decal decal, String name, Statistics statistics){

        super( decal, Category.ONE_SHOT_ITEM, name, statistics);

    }

    // ---------- METHODS IMPLEMENTATION ---------
    // ----------                         ---------

    // Accessor Methods:
    // --------------------------------------------

    // Mutator Methods:
    //---------------------------------------------

    // Abstract Methods:
    // --------------------------------------------
    @Override
	public void triggerProximity(Entity entity){
    	entity.getStatistics().addExperience(150);
    }

}
