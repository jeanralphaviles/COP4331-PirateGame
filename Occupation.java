/*
* Created by Conor Doherty
* on 2/11/2015
* Last edited: 2/11/15 by Conor Doherty
*
* The Occupation interface sets a contract for all Occupations to follow. More methods may be added in
* in the future (as of 2/11/15 - see Changelog for any changes made).
*/

/*
* Changelog:
* 2/11/15 Added file and added defaultStatistics(), toString() - Conor Doherty
*/

public interface Occupation {

    //Default (starting) stats for the character
    public Statistics getDefaultStatistics();

    public String toString();

}
