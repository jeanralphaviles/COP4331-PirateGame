package model.entity.occupation.ability.instantAbility;

import java.util.Random;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.item.TakeableItem;
import model.map.GridLocation;

public final class PickPocket extends InstantAbility {
	
	public PickPocket() {
		super(50, "Pick Pocket", Decal.createDecal(Decal.sneak));
	}

	@Override
	public void activate(Entity caster, Level level) {
		GridLocation casterLocation = level.getEntityLocation(caster);
		GridLocation enemyLocation = casterLocation.nextGridLocation(caster.getDirectionFacing());
		Entity enemy = level.getEntity(enemyLocation);
		if (enemy != null) {
			Random rand = new Random();
			if (rand.nextDouble() < 0.5 || !enemy.getDirectionFacing().equals(caster.getDirectionFacing())) {
				level.faceEntity(enemy, casterLocation);
				caster.getStatistics().changeCurrentHealth(-99);
				enemy.setFriendly(false);
			} else {
				int randomItemIndex = rand.nextInt(enemy.getInventory().getItems().size());
				TakeableItem item = (TakeableItem) enemy.getInventory().getItems().get(randomItemIndex);
				enemy.getInventory().removeItem(item);
			}
		}
	}

}
