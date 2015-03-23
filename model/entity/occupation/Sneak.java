package model.entity.occupation;

import utility.decal.Decal;
import model.entity.Statistics;
import model.entity.occupation.ability.instantAbility.PickPocket;
import model.entity.occupation.ability.instantAbility.Talk;
import model.entity.occupation.ability.instantAbility.itemAbility.SetTrap;
import model.entity.occupation.ability.instantAbility.projectile.ThrowKnife;
import model.entity.occupation.ability.toggleAbility.DetectTraps;

/**
 * @author Jean-Ralph Aviles
 */
public class Sneak extends Occupation {
    
    public static final String description = "Category: Sneak\nThis the second best of the characters\nEnjoy";
    public Sneak() {
        super("Sneak");
        this.getAbilities().clear();
        this.getAbilities().add(new PickPocket());
        this.getAbilities().add(new SetTrap());
        this.getAbilities().add(new DetectTraps());
        this.getAbilities().add(new ThrowKnife());
        this.getAbilities().add(new Talk());
        this.setPicture(Decal.createDecal(Decal.sneakPicture));
    }

    @Override
    public void augmentStatistics(Statistics statistics) {
        int agility = statistics.getAgility() * 4;
        statistics.setAgility(agility);
    }

}
