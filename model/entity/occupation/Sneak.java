package model.entity.occupation;

import model.entity.Statistics;
import model.entity.occupation.ability.instantAbility.itemAbility.SetTrap;
import model.entity.occupation.ability.instantAbility.projectile.ThrowKnife;
import model.entity.occupation.ability.toggleAbility.DetectTraps;

/**
 * @author Jean-Ralph Aviles
 */
public class Sneak extends Occupation {

    public Sneak() {
        super("Sneak");
        this.getAbilities().add(new SetTrap());
        this.getAbilities().add(new DetectTraps());
        this.getAbilities().add(new ThrowKnife());
    }

    @Override
    public void augmentStatistics(Statistics statistics) {
        int agility = statistics.getAgility() * 4;
        statistics.setAgility(agility);
    }

}
