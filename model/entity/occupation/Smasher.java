package model.entity.occupation;

import utility.decal.Decal;
import model.entity.Statistics;

/**
 * @author Jean-Ralph Aviles
 */
public class Smasher extends Occupation {

    public Smasher() {
        super("Smasher");
        this.setPicture(new Decal(Decal.smasherPicture));
    }

    @Override
    public void augmentStatistics(Statistics statistics) {
        int strength = statistics.getStrength() * 2;
        statistics.setStrength(strength);
        int hardiness = statistics.getHardiness() * 2;
        statistics.setHardiness(hardiness);
    }

}
