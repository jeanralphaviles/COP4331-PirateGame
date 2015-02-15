package model.item;

import model.entity.Entity;
import utility.decal.Decal;

/**
 * Created by Calos Vizcaino
 *  2/14/15.
 */
public class PowerUP extends InteractiveItem{

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
    public PowerUP(Decal decal, String name, int intellect, int agility, int experience){

        super(decal,name);
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

    // --------------------------------------------
    @Override
    public void triggerProximity(Entity entity){
    	if (entity.getEffectiveStatistics().getLevel() >= 2) {
    		entity.getStatistics().setAgility( getAgility() + this.agility );
    		entity.getStatistics().setIntellect( getIntellect() + this.intellect );
    		entity.getStatistics().setExperience( getExperience() + this.experience);
        }
    }
}
