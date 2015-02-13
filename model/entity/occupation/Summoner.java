package model.entity.occupation;

import model.entity.Statistics;
/*
* Created by Conor Doherty
* on 2/11/2015
* Last edited: 2/11/15 by Conor Doherty
*
* Summoner class that implements the Occupation interface. The occupation is intellect based.
* I am starting all Occupations off with 5 lives and 0 experience to start. The other stats
* will be based on reasonable assumptions about the different occupations.
*/

/*
* Changelog:
* 2/11/15 Added file, implemented interface fully, and added 3 constructors - Conor Doherty
*/

public class Summoner implements Occupation {

    /* Attributes */

    private Statistics defaultStatistics;
    private final String OCCUPATION_NAME = "Summoner";

    /* Constructors */

    //Default
    Summoner() {
        new Summoner(0, 5);
    }

    //Initialization with custom exp and lives
    Summoner(int experience, int livesLeft) {
        defaultStatistics = new Statistics();

        defaultStatistics.setAgility(2);
        defaultStatistics.setMovement(1);
        defaultStatistics.setStrength(1);
        defaultStatistics.setIntellect(3);
        defaultStatistics.setHardiness(2);

        defaultStatistics.setExperience(experience);
        defaultStatistics.setLivesLeft(livesLeft);
    }

    //Copy (Kind of useless in context, but eh - Why not?)
    Summoner(Occupation other) {
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