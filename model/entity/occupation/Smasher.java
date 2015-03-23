package model.entity.occupation;

import utility.decal.Decal;
import model.entity.Statistics;
import model.entity.occupation.ability.instantAbility.projectile.Glaives;

/**
 * @author Jean-Ralph Aviles
 */
public class Smasher extends Occupation {
    
    public static final String description = "Category: Smasher\nThis the best of the characters\nEnjoy";
    
    public Smasher() {
        super("Smasher");
        this.getAbilities().add(new Glaives());
        this.setPicture(Decal.createDecal(Decal.smasherPicture));
    }

    @Override
    public void augmentStatistics(Statistics statistics) {
        int strength = statistics.getStrength() * 2;
        statistics.setStrength(strength);
        int hardiness = statistics.getHardiness() * 2;
        statistics.setHardiness(hardiness);
    }

}
