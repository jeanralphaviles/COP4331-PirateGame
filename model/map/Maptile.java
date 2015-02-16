package model.map;

import java.io.IOException;
import java.util.ArrayList;

import model.entity.Entity;
import model.inventory.Slot;
import model.inventory.SlotCategory;
import model.item.Category;
import model.item.Item;
import model.item.ObstacleItem;
import model.map.areaeffect.AreaEffect;
import model.map.areaeffect.NullAreaEffect;
import model.map.terrain.Grass;
import model.map.terrain.Terrain;
import utility.decal.Decal;

/**
 * @author Jean-Ralph Aviles
 */
public class Maptile {

    private Slot itemSlot = new Slot(SlotCategory.ANY_SLOT);
    private Entity entity = null;
    private Terrain terrain;
    private AreaEffect areaEffect = new NullAreaEffect();

    public Maptile() {
        terrain = new Grass();
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
     * @return True if an entity is allowed to move to this Maptile, False
     * otherwise
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
    
    
    /**
     * @param entity Entity triggering effect
     */
    public void triggerProximityEffect(Entity entity) {
    	if (entity != null) {
    		if (areaEffect != null) {
    			areaEffect.triggerProximityEffect(entity);
    		}
    		if (!entity.getEffectiveStatistics().isDead()) {
    			Item item = itemSlot.getItem();
    			if (item != null && item.getCategory() == Category.TAKEABLE_ITEM) {
    				if (entity.storeItem(item)) {
    					itemSlot.removeItem();
    				}
    			}
    		}
    	}
    }

    /**
     * @return A list of decals associated with the map tile in decreasing order
     * of importance
     */
    public ArrayList<Decal> getDecals() {
        ArrayList<Decal> decals = new ArrayList<Decal>(1);
        if (entity != null) {
            decals.add(entity.getDecal());
        }
        if (itemSlot != null && itemSlot.getItem() != null) {
            decals.add(itemSlot.getItem().getDecal());
        }
        if (areaEffect != null) {
            //decals.add(areaEffect.getDecal());
        }
        if (terrain != null) {
            decals.add(terrain.getDecal());
        }
        return decals;
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

    @Override
	public String toString() {
    	return "[" + itemSlot.toString() + "," + terrain.toString() + "," + areaEffect.toString() + "]";
    }
    
	public static Maptile fromString(String string) throws IOException {
		String stripped = string.substring(1, string.length() - 1);
		int bracketCount = 0;
		int start = 0;
		int itemCount = 0;
		Maptile mapTile = new Maptile();
		
		for (int i = 0; i < stripped.length(); ++i) {
			if (bracketCount == 0 && stripped.charAt(i) == ',') {
				if (itemCount == 0) {
					Slot itemSlot = Slot.fromString(stripped.substring(start, i));
					mapTile.itemSlot = itemSlot;
				} else if (itemCount == 1) {
					Terrain terrain = Terrain.fromString(stripped.substring(start, i));
					mapTile.terrain = terrain;
				} else if (itemCount == 2) {
					AreaEffect areaEffect = AreaEffect.fromString(stripped.substring(start, i));
					mapTile.areaEffect = areaEffect;
					break;
				} 
				++itemCount;
				start = i + 1;
			} else if (stripped.charAt(i) == '[') {
				++bracketCount;
			} else if (stripped.charAt(i) == ']') {
				--bracketCount;
			}
		}
		
		return null;
	}
}
