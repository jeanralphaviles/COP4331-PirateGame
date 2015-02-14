package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;


public class InteractableItem extends Item{

    // Atrributes

    // Default Constructor
    public InteractableItem(){

        super( new ItemDefaultDecal(), Category.INTERACTIVE_ITEM);

    }
    // Constructor I
    public InteractableItem(Decal decal ){

        super( decal, Category.INTERACTIVE_ITEM);

    }
    // Constructor II
    public InteractableItem(Decal decal, String name){

        super( decal, Category.INTERACTIVE_ITEM, name);
    }
    // Constructor III
    public InteractableItem(Decal decal, String name, Statistics statistics){


        super( decal, Category.INTERACTIVE_ITEM, name, statistics);
    }
    // ---------- METHODS IMPLEMENTATION ---------
    // ----------                         ---------

    // Accessor Methods:
    // --------------------------------------------

    // Mutator Methods:
    // Abstract Methods:
    // --------------------------------------------
	@Override
	public void triggerProximity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

}
