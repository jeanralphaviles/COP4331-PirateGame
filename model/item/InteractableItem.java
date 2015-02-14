package model.item;

import java.lang.Override;

import model.entity.Entity;
import model.entity.Statistics;


public class InteractableItem extends Item{

    // Atrributes

    // Default Constructor
    public InteractableItem(){

        super();

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
