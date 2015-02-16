package model.item;


import model.entity.Entity;
import model.entity.Statistics;
import model.inventory.SlotCategory;

import utility.decal.Decal;

/**
 * Created by Carlos:
 * Date: 2/14/2014
 *
 */
public class Weapon extends TakeableItem {

    // Atrributes

    protected int strength;
    protected int hardiness;

    // Default constructor
    public Weapon(){

        super();

        strength = 0;
        hardiness = 0;
        slotCategory = SlotCategory.HAND;

        strength = 0;
        hardiness = 0;

    }
    // Constructor I
    public Weapon(Decal decal,String name){

        super(decal,name);


        slotCategory = SlotCategory.HAND;
        hardiness = 0;
       strength = 0;
    }
    // Constructor II
    public Weapon(Decal decal, String name,int strength, int hardiness){

        super(decal,name);

        slotCategory = SlotCategory.HAND;
        hardiness = 0;
    }
    // Constructor II
    public Weapon(Decal decal, String name, int damage, int strength, int hardiness){

        super(decal,name);
        this.strength = strength;
        this.hardiness = hardiness;

    }

    // ----------- METHODS IMPLEMENTATION -------------------
    // -----------                        -------------------


    // Accessors:
    // ---------------------------------------------------
    public int getStrength(){

        return strength;
    }
    // ---------------------------------------------------
    public int getHardiness(){

        return hardiness;
    }

    // Mutators:
    // ---------------------------------------------------
    public void setStrength(int strength){

        this.strength = strength;
    }
    // ---------------------------------------------------
    public void setHardiness(int hardiness){

        this.hardiness = hardiness;
    }
    // ---------------------------------------------------
   
/**Weapon augments stats*/
 @Override
    public void triggerProximity(Entity entity){

        Statistics entityStats = entity.getStatistics();

        entity.getStatistics().setHardiness( entityStats.getHardiness() + this.hardiness );
        entity.getStatistics().setStrength( entityStats.getStrength() + this.strength );
    }


}
