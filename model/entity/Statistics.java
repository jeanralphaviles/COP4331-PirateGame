package model.entity;
/*
* Created by Conor Doherty
* on 2/11/2015
* Last edited: 2/11/15 by Conor Doherty
*
* The Statistics class used to bundle all statistics together. All Entities and Items have Statistics which can
* be added using the this.combine(Statistics other) function. This is how total stats will be calculated.
* All Primary Stats are private fields with public accessors and all Derived Stats are public methods.
*/

/*
* Changelog:
* 2/11/15 Added file and started skeleton - Conor Doherty
*/

import model.entity.occupation.Occupation;
import model.inventory.EquippedInventory;

public class Statistics {

    /* Attributes */

    private int livesLeft;
    private int strength;
    private int agility;
    private int intellect;
    private int hardiness;
    private int experience;
    private int movement;

    private final int EXP_PER_LVL = 1;

    private int health;
    private int mana;

    /* Constructors */

    //Default
    Statistics() {
        //Do nothing
    }

    //Initialization - What you should use!
    Statistics(Occupation occupation) {
        this(occupation.getDefaultStatistics());
    }

    //Copy
    Statistics(Statistics other) {

        //Copy over Primary Stats
        this.livesLeft = other.livesLeft;
        this.agility = other.agility;
        this.experience = other.experience;
        this.hardiness = other.hardiness;
        this.intellect = other.intellect;
        this.movement = other.movement;
        this.strength = other.strength;
    }

    /* Getters for Primary Stats (Auto-generated) */

    public int getLivesLeft() {
        return livesLeft;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getIntellect() {
        return intellect;
    }

    public int getHardiness() {
        return hardiness;
    }

    public int getExperience() {
        return experience;
    }

    public int getMovement() {
        return movement;
    }

    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    /* Setters for Primary Stats (Auto-generated) */

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setHardiness(int hardiness) {
        this.hardiness = hardiness;
    }

    public void setIntellect(int intellect) {
        this.intellect = intellect;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setLivesLeft(int livesLeft) {
        this.livesLeft = livesLeft;
    }

    /* Getters for Secondary Stats */

    //Starts at level 1
    public int getLevel() {
        //Should absolutely never happen, but just in case
        if (EXP_PER_LVL == 0) {
            return 1;
        }
        return (int)Math.floor(experience / EXP_PER_LVL) + 1;
    }

    //These all depend on equipment so may need additional parameters tying that in
    public int getOffensiveRating( EquippedInventory eInv ) {
        return 0;
    }

    public int getDefensiveRating() {
        return 0;
    }

    public int getArmorRating( EquippedInventory eInv ) {
        return 0;
    }

    public int maxOffensiveItems() {
        return 0;
    }


    //Other needed functions
    public Statistics combine(Statistics other) {
        other.livesLeft += this.livesLeft;
        other.agility += this.agility;
        other.experience += this.experience;
        other.hardiness += this.hardiness;
        other.intellect += this.intellect;
        other.movement += this.movement;
        other.strength += this.strength;

        return other;
    }

    //Returns true if the player should die after
    //Account
    public void changeHealth(int amount) {
        if (health + amount <= 0) {
            health = 0;
        } else if (health + amount > getMaxHealth()) {
            health = getMaxHealth();
        } else {
            health += amount;
        }
        return;
    }

    public void setHealth(int amount) {
        health = amount;
    }

    //Returns false if not enough mana left
    public boolean changeMana(int amount) {
        if (mana <= 0) {
            return false;
        } else if (mana + amount > getMaxMana()) {
            mana = getMaxMana();
            return true;
        } else {
            mana += amount;
        }
        return true;
    }

    public int getMaxHealth() {
        return (hardiness * 10 + getLevel() * 10);
    }

    public int getMaxMana() {
        return (intellect * 10 + getLevel() * 10);
    }
}
