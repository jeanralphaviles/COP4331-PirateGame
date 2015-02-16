package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;


public class InteractiveItem extends Item{

    // Atrributes

    // Default Constructor
    public InteractiveItem(){

        super( new ItemDefaultDecal(), Category.INTERACTIVE_ITEM);

    }
    // Constructor I
    public InteractiveItem(Decal decal ){

        super( decal, Category.INTERACTIVE_ITEM);

    }
    // Constructor II
    public InteractiveItem(Decal decal, String name){

        super( decal, Category.INTERACTIVE_ITEM, name);
    }
    // Constructor III
    public InteractiveItem(Decal decal, String name, Statistics statistics){

        super( decal, Category.INTERACTIVE_ITEM, name, statistics);
    }
    // ---------- METHODS IMPLEMENTATION ---------
    // ----------                         ---------

    // Accessor Methods:
    // --------------------------------------------

    // Mutator Methods:
    // Abstract Methods:
    // --------------------------------------------
/**
	 * trigger proximity activates when entity tries to step into tile with item
	 * the entity can interact with it
	 * */	
@Override
	public void triggerProximity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

}
