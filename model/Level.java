package model;

import java.util.ArrayList;

import map.Map;
import map.Maptile;

/**
 * @author Jean-Ralph Aviles
 */
public class Level {
	private ArrayList<Entity> entities;
	private Map map;
	
	public Level() {
		entities = new ArrayList<Entity>();
		map = new Map();
	}
	
	/**
	 * @param entities List of Entities for a Level
	 * @param map Level's Map
	 * TODO figure out where to place the entities
	 */
	public Level(ArrayList<Entity> entities, Map map) {
		setEntities(entities);
		setMap(map);
	}
	
	/**
	 * @param entity Entity to add
	 * @return Entity if successful, null if not
	 * TODO figure out where to place the entity
	 */
	public Entity addEntity(Entity entity) {
		entities.add(entity);
		return entity;
	}
	
	/**
	 * @return A list of Entities in a Level
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * @param entity Remove an Entity from the Level completely 
	 * @return Entity that was deleted if successful, null if not
	 */
	public Entity removeEntity(Entity entity) {
		if (entities.contains(entity)) {
			Maptile entityTile = entity.getMaptile();
			entityTile.removeEntity();
			entities.remove(entity);
			return entity;
		}
		return null;
	}
	
	/**
	 * @param entities Entities to set
	 * @return Entities that were set
	 */
	public ArrayList<Entity> setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
		return entities;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * @param oldEntity Entity to be replaced
	 * @param newEntity Entity to replace with
	 * @return newEntity if successful, null if not
	 */
	public Entity replaceEntity(Entity oldEntity, Entity newEntity) {
		if (entities.contains(oldEntity)) { /* Look for Old Entity */
			Maptile entityTile = oldEntity.getMaptile(); /* Get Old Entity's current Maptile */
			entityTile.removeEntity(); /* Remove Old Entity from Maptile */
			entities.remove(oldEntity); /* Remove Old Entity from entities list */
			entities.add(newEntity); /* Add New Entity to entities list */
			entityTile.addEntity(newEntity); /* Add New Entity onto Old Entity's Maptile */
			return newEntity;
		}
		return null; /* Couldn't find Old Entity */
	}

}
