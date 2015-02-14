package model.map;

import model.entity.Entity;
import model.inventory.Slot;
import model.item.Category;
import model.item.Item;
import model.item.ObstacleItem;
import model.map.areaeffect.AreaEffect;
import model.map.terrain.Grass;
import model.map.terrain.Terrain;

/**
 * @author Jean-Ralph Aviles
 */
public class Maptile {
	private Slot itemSlot = new Slot(Category.MAPTILE_SLOT);
	private Entity entity = null;
	private Terrain terrain = new Grass();
	private AreaEffect areaEffect = null;
	
	public Maptile() {
		
	}
	
	/**
	 * @param item Item to store
	 * @return True if item was stored successfully, False if otherwise 
	 */
	public boolean storeItem(Item item) {
		return itemSlot.storeItem(item);
	}
	
	/**
	 * @param entity Entity to add to Maptile
	 * @return True if entity was added successfully, False if otherwise
	 */
	public boolean addEntity(Entity entity) {
		if (this.entity != null) { /* Don't overwrite entity on tile */
			return false;
		}
		setEntity(entity);
        return true;
	}

	public void removeItem() {
		itemSlot.removeItem();
	}

	public void removeEntity() {
		setEntity(null);
	}
	
	/**
	 * @return True if an entity is allowed to move to this Maptile, False otherwise
	 */
	public boolean isPassable() {
		if (entity != null) { /* Entity already there */
			return false;
		} else if (terrain.isPassable() == false) {
			return false;
		} else if (itemSlot.getItem() instanceof ObstacleItem) { /* Tile contains ObstacleItem */
			return false;
		} else {
			return true;
		}
	}
	
	public Slot getItemSlot() {
		return itemSlot;
	}

	public void setItemSlot(Slot itemSlot) {
		this.itemSlot = itemSlot;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Terrain getTerrain() {
		return terrain;
	}

	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public AreaEffect getAreaEffect() {
		return areaEffect;
	}

	public void setAreaEffect(AreaEffect areaEffect) {
		this.areaEffect = areaEffect;
	}
}