/*
* Created by Conor Doherty
* on 2/11/2015
* Last edited: 2/11/15 by Conor Doherty
*
* Smasher class that implements the Occupation interface. The occupation is Strength based.
* I am starting all Occupations off with 5 lives and 0 experience to start. The other stats
* will be based on reasonable assumptions about the different occupations.
*/

/*
* Changelog:
* 2/11/15 Added file, implemented interface fully, and added 3 constructors - Conor Doherty
*/

public class Smasher implements Occupation {

    private Statistics defaultStatistics;
    private final String occupationName = "Smasher";

    //Default
    Smasher() {
        new Smasher(0, 5); //Create new instance with 0 exp and 5 lives
    }

    //Initialization with custom exp and lives
    Smasher(int experience, int livesLeft) {
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
    Smasher(Occupation other) {
        defaultStatistics = new Statistics(other.getDefaultStatistics());
    }

    //Default (starting) stats for the character
    public Statistics getDefaultStatistics() {
        return defaultStatistics;
    }

    public String toString() {
        return occupationName;
    }
}
