package model.entity.occupation.ability.instantAbility;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.item.Item;
import model.map.GridLocation;
import utility.response.Response;

public final class Talk extends InstantAbility {

    public Talk() {
        super();
        this.setManaCost(0);
        this.setName("Talk");
        this.setIcon(Decal.createDecal(Decal.take_damage));
    }

    @Override
    public void activate(Entity caster, Level level) {
        GridLocation entityLocation = level.getEntityLocation(caster);
        GridLocation enemyLocation = entityLocation.nextGridLocation(caster.getDirectionFacing());
        Entity entity = level.getEntity(enemyLocation);
        if (entity == null) { //if no entity, look for item
            Item item = level.getItem(enemyLocation);
            if (item == null) {
                return;
            } else { //talk to the item...
                Response response = item.getResponse();
                level.setResponse(response);
            }
        } else { //talk to the entity
            Response response = entity.getResponse();
            level.setResponse(response);
        }
    }

}
