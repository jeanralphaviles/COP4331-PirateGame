package model.entity.occupation.ability.instantAbility;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.map.GridLocation;

public final class Melee extends InstantAbility {

    public Melee() {
        super();
        this.setManaCost(0);
        this.setName("Melee");
        this.setIcon(Decal.createDecal(Decal.take_damage));
    }

    @Override
    public void activate(Entity caster, Level level) {
        GridLocation entityLocation = level.getEntityLocation(caster);
        GridLocation enemyLocation = entityLocation.nextGridLocation(caster.getDirectionFacing());
        Entity enemy = level.getEntity(enemyLocation);
        if (enemy != null) {
            int damage = caster.getDerivedStatistics().getOffensiveRating();
            enemy.getStatistics().changeCurrentHealth(-1 * damage);
            enemy.setFriendly(false);
        }
    }

}
