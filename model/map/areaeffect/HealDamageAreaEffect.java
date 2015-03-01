package model.map.areaeffect;

import utility.decal.Decal;
import model.entity.Entity;

/**
 * @author Jean-Ralph Aviles Heal damage heals damage by 3 points
 */
public final class HealDamageAreaEffect extends AreaEffect {

    private int damageHealed = 3;

    public HealDamageAreaEffect() {
        super(new Decal(Decal.heal_damage));
    }

    public HealDamageAreaEffect(Decal decal) {
        super(decal);
    }

    public final int getDamageHealed() {
        return damageHealed;
    }

    public final void setDamageHealed(int damageHealed) {
        this.damageHealed = damageHealed;
    }

    @Override
    public void triggerProximityEffect(Entity entity) {
        if (isActive()) {
            entity.getStatistics().changeCurrentHealth(damageHealed);
        }
    }
}
