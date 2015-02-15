package model.item;

import utility.decal.Decal;

/**
 * Created by Calos Vizcaino
 *  2/14/15.
 */
public class PowerUP extends InteractableItem{

    // Attributes
    protected int intellect;
    protected int agility;
    protected int experience;


    // Deafult Constructor
    public PowerUP(){

        super();
        intellect = 0;
        agility = 0;
        experience = 0;
    }
    // Constructor I
    public PowerUP(Decal decal){

        super(decal);
        intellect = 0;
        agility = 0;
        experience = 0;
    }
    // Constructor I
    public PowerUP(Decal decal, int intellect, int agility, int experience){

        super(decal);
        this.intellect = intellect;
        this.agility = agility;
        this.experience = experience;
    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------

    // Accessors:
    // --------------------------------------------
    public int getIntellect(){return intellect;}

    // --------------------------------------------
    public int getAgility(){ return agility;}

    // --------------------------------------------
    public int getExperience(){return experience;}


    //Mutators:
    // --------------------------------------------
    public void setIntellect( int intellect){ this.intellect = intellect;}

    // --------------------------------------------
    public void setAgility( int agility){ this.agility = agility; }

    // --------------------------------------------
    public void setExperience( int experience){ this.experience = experience; }


}
