package model.entity.occupation;

import model.entity.Statistics;

/**
 * @author Jean-Ralph Aviles
 */
public class Summoner extends Occupation {

    public Summoner() {
        super("Summoner");
    }

    @Override
    public void augmentStatistics(Statistics statistics) {
        int intellect = statistics.getIntellect() * 4;
        statistics.setIntellect(intellect);
    }

}
