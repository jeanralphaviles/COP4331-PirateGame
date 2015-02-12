/*
* Created by Conor Doherty
* on 2/11/2015
* Last edited: 2/11/15 by Conor Doherty
*
* The Statistics class used to bundle all statistics together. All Entities and Items have Statistics which can
* be added using the this.combine(Statistics other) function. This is how total stats will be calculated.
* All Primary Stats are private fields with public accessors and all Derived stats are public methods.
*/

/*
* Changelog:
* 2/11/15 Added file and started skeleton - Conor Doherty
*/

public class Statistics {

    /* Constructors */

    //Default
    Statistics() {
        //Do nothing
    }

    //Initialization
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

    /* Fields */

    private int livesLeft;
    private int strength;
    private int agility;
    private int intellect;
    private int hardiness;
    private int experience;
    private int movement;

    /* Methods */

    //Getters for Primary Stats (Auto-generated)
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

    //Setters for Primary Stats (Auto-generated)

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

    //Getters for Secondary Stats

    public int getLevel() {
        return 0;
    }

    public int getLife() {
        return 0;
    }

    public int getMana() {
        return 0;
    }

    //These all depend on equipment so may need additional parameters tying that in
    public int getOffensiveRating( /* Need some params here I think */ ) {
        return 0;
    }

    public int getDefensiveRating( /* Need some params here I think */ ) {
        return 0;
    }

    public int getArmorRating( /* Need some params here I think */ ) {
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

}
