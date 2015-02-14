package item;

import java.lang.Override;


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
    public void setAugementStatistics( Statistics stats){

        // Todo-Code
    }

    // Abstract Methods:
    // --------------------------------------------
    public void triggerProximity(Entity entity){

        // Todo-Code
    }

}
