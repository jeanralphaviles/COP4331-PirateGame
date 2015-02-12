package map;

import model.Entity;
import inventory.Slot;
import item.Category;
import item.Item;
import item.ObstacleItem;


public class Maptile {
	private Slot itemSlot = new Slot(Category.MAPTILE_SLOT);
	private Entity entity = null;
	private Terrain terrain = new Grass();
	private AreaEffect areaEffect = null;
	
	public Maptile() {
		
	}
	
	public boolean addItem(Item item) {
		return itemSlot.addItem(item);
	}
	
	public boolean addEntity(Entity entity) {
		if (entity != null) { /* Don't overwrite entity on tile */
			return false;
		}
        this.entity = entity;
        return true;
	}

	public void removeItem() {
		itemSlot.removeItem();
	}
	
	public boolean isPassable() {
		if (this.entity != null) { /* Entity already there */
			return false;
		} else if (terrain.isPassable() == false) {
			return false;
		} else if (itemSlot.getItem() instanceof ObstacleItem) {
			return false;
		} else{
			return true;
		}
	}
	
	public void removeEntity() {
		entity = null;
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
