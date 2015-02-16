package model.item;

import model.entity.Entity;
import utility.decal.Decal;

/**
 * Created by Carlos Vizcaino
 * Date 2/14/2015
 * A Food Item is an item which is eaten, does it's thing, and hence is no longer on the map (deleted off map) 
 */


public class Food extends OneShotItem {

    // Atrributes
    protected int health;

    // Deafult Constructor
    public Food(){

        super();
        health = 0;
    }
    // Constructor I
    public Food(Decal decal){

        super(decal);
        health = 0;
    }
    // Constructor II

    public Food(Decal decal, String name){

        super(decal,name);
        health = 0;
    }
    // Constructor III
    public Food(Decal decal, String name, int health){

        super(decal,name);
        this.health = health;
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // Accessors:
    // --------------------------------------------------
    public int getHealth(){

        return health;
    }

    // Mutators:
    // --------------------------------------------------
    public void setHealth( int health){

        this.health = health;
    }

    // --------------------------------------------------
    @Override
    public void triggerProximity(Entity entity){

        int entityHealth = entity.getStatistics().getCurrentHealth();
        entity.getStatistics().setCurrentHealth( entityHealth + this.health );
    }



}
