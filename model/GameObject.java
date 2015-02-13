package model;

import java.util.ArrayList;

import map.Map;

public class GameObject {
	private Avatar avatar;
	private int avatarLevel;
	private ArrayList<Level> levels;
	
	/**
	 * @param avatar Game's Avatar
	 * @param avatarLevel Avatar's current Level
	 * @param levels Ordered List of Levels 1 -> levels.size()
	 */
	public GameObject(Avatar avatar, int avatarLevel, ArrayList<Level> levels) {
		setAvatar(avatar, avatarLevel);
		setLevels(levels);
	}
	
	/**
	 * @param levelNumber Level to get Entities from
	 * @return List of Entities from Level if successful, null if not
	 */
	public ArrayList<Entity> getEntities(int levelNumber) {
		if (isValidLevelNumber(levelNumber)) {
			return levels.get(levelNumber).getEntities();
		} else {
			return null;
		}
	}
	
	/**
	 * @param levelNumber Level to get Map from
	 * @return That Level's Map
	 */
	public Map getMap(int levelNumber) {
		if (isValidLevelNumber(levelNumber)) {
			return levels.get(levelNumber).getMap();
		} else {
			return null;
		}
	}
	
	/**
	 * @param entity Entity to add
	 * @param levelNumber Level to add entity to
	 * @return Returns Entity if successful, null if not
	 * TODO figure out where to place entity
	 */
	public Entity addEntity(Entity entity, int levelNumber) {
		if (isValidLevelNumber(levelNumber)) {
			return levels.get(levelNumber).addEntity(entity);
		} else {
			return null;
		}
	}
	
	/**
	 * @param entity Entity to remove
	 * @param levelNumber LevelNumber of level containing Entity
	 * @return Returns removed Entity if successful, null if not
	 */
	public Entity removeEntity(Entity entity, int levelNumber) {
		if (isValidLevelNumber(levelNumber)) {
			return levels.get(levelNumber).removeEntity(entity);
		}
		return null;
	}
	
	/**
	 * @param levelNumber Level Number to check
	 * @return True if level number exists in levels, false otherwise
	 */
	public boolean isValidLevelNumber(int levelNumber) {
		return levelNumber >= 0 && levelNumber < levels.size();
	}
	
	/**
	 * @param level Level to set
	 * @param levelNumber Level Number to replace
	 * @return Level that was set if successful, null if not
	 */
	public Level setLevel(Level level, int levelNumber) {
		if (isValidLevelNumber(levelNumber)) {
			levels.set(levelNumber, level);
			return level;
		} else {
			return null;
		}
	}
	
	/**
	 * @param levelNumber Level# to get
	 * @return Level #levelNumber if successful, null if not
	 */
	public Level getLevel(int levelNumber) {
		if (isValidLevelNumber(levelNumber)) {
			return levels.get(levelNumber);
		} else {
			return null;
		}
	}

	public Avatar getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar Avatar to set
	 * @param levelNum Level to place Avatar
	 * @return Avatar if successfull, null if not
	 * TODO figure out location of avatar
	 */
	public Avatar setAvatar(Avatar avatar, int levelNum) {
		if (isValidLevelNumber(levelNum)) {
			Avatar placedAvatar = (Avatar) levels.get(levelNum).addEntity(avatar); /* Add Avatar to Level #levelNum */
			if (placedAvatar != null) { /* If adding entity was successful */
				setAvatarLevel(levelNum); /* Set the new avatar's level */
				this.avatar = avatar; /* Update Game Object's Avatar */
				return avatar;
			}
		}
		return null; /* Error occurred */
	}

	/**
	 * @param avatar Avatar to replace current avatar, in-place
	 * @return Avatar that replaced current avatar if successful, null if not
	 */
	public Avatar setAvatar(Avatar avatar) {
		Avatar placedAvatar = (Avatar) levels.get(getAvatarLevel()).replaceEntity(this.avatar, avatar); /* Replace Avatar on it's current level */
		if (placedAvatar != null) { /* If replacing entity was successful */
			this.avatar = avatar; /* Update Game Object's Avatar */
			return avatar;
		} else {
			return null; /* Error occurred */
		}
	}
	
	public ArrayList<Level> getLevels() {
		return levels;
	}
	
	/**
	 * @param levels Levels to set game object to
	 * @return Levels arrayList that was passed
	 */
	public ArrayList<Level> setLevels(ArrayList<Level> levels) {
		this.levels = levels;
		return levels;
	}

	public int getAvatarLevel() {
		return avatarLevel;
	}

	/**
	 * @param avatarLevel Level to change Avatar to
	 * @return Avatar if successful, null if not
	 * TODO figure out location where to put avatar
	 */
	public Avatar setAvatarLevel(int avatarLevel) {
		if (isValidLevelNumber(avatarLevel)) {
			levels.get(getAvatarLevel()).removeEntity(avatar); /* Remove Avatar from old level */
			setAvatarLevel(avatarLevel); /* Change avatar level to new level */
			return (Avatar) levels.get(getAvatarLevel()).addEntity(avatar); /* Add Avatar to new level and return */
		}
		return null; /* Error occurred */
	}

}
