package model.item;

import utility.decal.Decal;
import model.Level;
import model.entity.Entity;
import model.inventory.Inventory;
import model.map.GridLocation;

public final class EntitySpawner extends InteractiveItem {

	public EntitySpawner() {
		super(Decal.createDecal(Decal.entity_spawner));
	}
	
	@Override
	public final void triggerProximity(Entity entity, Level level) {
		Inventory inventory = entity.getInventory();
		for (Item item : inventory.getItems()) {
			if (item.getName().equals("Conjured Shoes")) {
				inventory.removeItem(item);
				GridLocation entityLocation = level.getEntityLocation(entity);
				GridLocation newEntityLocation = entityLocation.nextGridLocation(entity.getDirectionFacing());
				level.addEntity(new Entity(), newEntityLocation);
				return;
			}
		}
	}
}
