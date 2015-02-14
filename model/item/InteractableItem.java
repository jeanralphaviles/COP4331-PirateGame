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
    public InteractableItem(Decal decal, Statistics statistics){

        super( decal, Category.INTERACTIVE_ITEM, statistics);
    }
    // ---------- METHODS IMPLEMENTATION ---------
    // ----------                         ---------

    // Accessor Methods:
    // --------------------------------------------

    // Mutator Methods:
    @Override
    // --------------------------------------------
    public void setAugmentStatistics( Statistics stats){

        // Todo-Code
    }

    // Abstract Methods:
    // --------------------------------------------
	@Override
	public void triggerProximity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

}
