package model.item;

import model.inventory.SlotCategory;
import utility.decal.Decal;

/**
 * Created by Carlos:
 * Date: 2/14/2014
 *
 */
public class Weapon extends TakeableItem {

    // Atrributes
    protected int damage;
    protected int strength;
    protected int hardiness;

    // Default constructor
    public Weapon(){

        super();
        damage = 0;
        strength = 0;
        hardiness = 0;
        slotCategory = SlotCategory.HAND;

    }
    // Constructor I
    public Weapon(Decal decal,String name){

        super(decal,name);
        slotCategory = SlotCategory.HAND;
        hardiness = 0;

    }
    // Constructor II
    public Weapon(Decal decal, String name, int damage, int strength, int hardiness){

        super(decal,name);
        slotCategory = SlotCategory.HAND;
        this.damage = damage;
        this.strength = strength;
        this.hardiness = hardiness;

    }

    // Accessors:
    // ---------------------------------------------------
    public int getDamage(){

        return damage;
    }
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
    public void setDamage(int damage){

        this.damage = damage;
    }
    // ---------------------------------------------------
    public void setStrength(int strength){

        this.strength = strength;
    }
    // ---------------------------------------------------
    public void setHardiness(int hardiness){

        this.hardiness = hardiness;
    }


}
