package model.entity.occupation;

import model.entity.Statistics;
/*
* Created by Conor Doherty
* on 2/11/2015
* Last edited: 2/11/15 by Conor Doherty
*
* Custom Occupation class that implements the Occupation interface. The occupation is for entites that do not
* have an occupation, or potentially if a player wanted to customize stats their own way in the future.
* As with the other classes, livesLeft starts at 5 and experience starts at 0.
*/

/*
* Changelog:
* 2/11/15 Added file, implemented interface fully, and added 3 constructors - Conor Doherty
*/

public class CustomOccupation implements Occupation {

    /* Attributes */
    
    private Statistics defaultStatistics;
    private final String OCCUPATION_NAME = "Custom";

    /* Constructors */

    //Initialize all Primary Stats; other parameters organized in alphabetical order
    //Useful for creating other types of entities as a "Default" Occupation.
    CustomOccupation(int agility, int experience, int hardiness, int intellect,
                     int livesLeft, int movement, int strength) {
        defaultStatistics = new Statistics();

        defaultStatistics.setAgility(agility);
        defaultStatistics.setMovement(movement);
        defaultStatistics.setStrength(strength);
        defaultStatistics.setIntellect(intellect);
        defaultStatistics.setExperience(experience);
        defaultStatistics.setHardiness(hardiness);
        defaultStatistics.setLivesLeft(livesLeft);
    }

    //Copy (Kind of useless in context, but eh - Why not?)
    CustomOccupation(CustomOccupation other) {
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