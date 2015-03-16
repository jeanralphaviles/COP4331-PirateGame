package model.entity.occupation;

import model.entity.Statistics;
import model.entity.occupation.ability.instantAbility.itemAbility.ConjureShoes;
import model.entity.occupation.ability.instantAbility.projectile.Firebolt;
import model.entity.occupation.ability.instantAbility.status.Boon;

/**
 * @author Jean-Ralph Aviles
 */
public class Summoner extends Occupation {

    public Summoner() {
        super("Summoner");
        this.getAbilities().add(new Firebolt());
        this.getAbilities().add(new Boon());
        this.getAbilities().add(new ConjureShoes());
    }

    @Override
    public void augmentStatistics(Statistics statistics) {
        int intellect = statistics.getIntellect() * 4;
        statistics.setIntellect(intellect);
    }

}
