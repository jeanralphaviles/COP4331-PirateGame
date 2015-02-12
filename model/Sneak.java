package model;
/*
* Created by Conor Doherty
* on 2/11/2015
* Last edited: 2/11/15 by Conor Doherty
*
* Sneak class that implements the Occupation interface. The occupation is agility based.
* I am starting all Occupations off with 5 lives and 0 experience to start. The other stats
* will be based on reasonable assumptions about the different occupations.
*/

/*
* Changelog:
* 2/11/15 Added file, implemented interface fully, and added 3 constructors - Conor Doherty
*/

public class Sneak implements Occupation {

    /* Attributes */

    private Statistics defaultStatistics;
    private final String OCCUPATION_NAME = "Sneak";

    /* Constructors */

    //Default
    Sneak() {
        new Sneak(0, 5);
    }

    //Initialization with custom exp and lives
    Sneak(int experience, int livesLeft) {
        defaultStatistics = new Statistics();

        defaultStatistics.setAgility(3);
        defaultStatistics.setMovement(3);
        defaultStatistics.setStrength(1);
        defaultStatistics.setIntellect(1);
        defaultStatistics.setHardiness(1);

        defaultStatistics.setExperience(experience);
        defaultStatistics.setLivesLeft(livesLeft);
    }

    //Copy (Kind of useless in context, but eh - Why not?)
    Sneak(Occupation other) {
        defaultStatistics = new Statistics(other.getDefaultStatistics());
    }

    //Default (starting) stats for the character
    public Statistics getDefaultStatistics() {
        return defaultStatistics;
    }

    public String toString() {
        return OCCUPATION_NAME;
    }
}