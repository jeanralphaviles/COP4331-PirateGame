package model.item;

import utility.decal.Decal;

/**
 * Created by Carlos Vizcaino
 * Date 2/14/2015
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
    public Food(Decal decal, int health){

        super(decal);
        this.health = health;
    }

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




}
