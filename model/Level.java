package model;

import java.util.ArrayList;
import java.util.Random;

import utility.Course;
import utility.decal.Decal;
import model.entity.Avatar;
import model.entity.Entity;
import model.entity.occupation.ability.Ability;
import model.inventory.Slot;
import model.inventory.SlotCategory;
import model.item.Category;
import model.item.Item;
import model.item.OneShotItem;
import model.item.TakeableItem;
import model.map.GridLocation;
import model.map.Map;
import model.map.areaeffect.HealDamageAreaEffect;
import model.map.terrain.Water;
import model.projectile.Projectile;

/**
 * @author Jean-Ralph Aviles
 */
public class Level {
	private Entity[][] entities;
	private Slot[][] slots;
	private Projectile[][] projectiles;
	private Map map;
    // TODO can we remove these please?
    private int dialogueIndex = 0;
    private ArrayList<String> dialogueStrings = new ArrayList<String>(1);
    private String currentDialogue = ""; //let this be set outside

    public Level() {
        map = new Map();
        entities = new Entity[getWidth()][getHeight()];
        slots = new Slot[getWidth()][getHeight()];
        for (int x = 0; x < getWidth(); ++x) {
        	for (int y = 0; y < getHeight(); ++y) {
        		slots[x][y] = new Slot(SlotCategory.ANY_SLOT);
        	}
        }
        projectiles = new Projectile[getWidth()][getHeight()];
        initDialogueStrings(); // TODO remove this
    }
    
    public Level(Map map, Slot[][] slots) {
    	this.map = map;
    	this.slots = slots;
        entities = new Entity[getWidth()][getHeight()];
        projectiles = new Projectile[getWidth()][getHeight()];
        initDialogueStrings(); // TODO remove this
    }
    
    public void gameStep() {
    	Avatar avatar = getAvatar();
    	ArrayList<Entity> entities = getEntities();
    	for (Entity entity : entities) {
    		if (entity != avatar) {
    			advanceEntity(entity);
    		}
    		entity.gameStep(this);
    	}
    }
    
    public void environmentGameStep() {
    	ArrayList<Projectile> projectiles = getProjectiles();
    	for (Projectile projectile : projectiles) {
    		advanceProjectile(projectile);
    	}
	}

	public boolean isValidGridLocation(GridLocation gridLocation) {
    	return map.isValidGridLocation(gridLocation);
    }
    
    public boolean addEntity(Entity entity, GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		if (isPassable(entity, gridLocation)) {
    			entities[gridLocation.getX()][gridLocation.getY()] = entity;
    			return true;
    		}
    	}
    	return false;
    }
    
    public Entity getEntity(GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		return entities[gridLocation.getX()][gridLocation.getY()];
    	}
    	return null;
    }
    
    public ArrayList<Entity> getEntities() {
    	ArrayList<Entity> entities = new ArrayList<Entity>();
    	for (int x = 0; x < getWidth(); ++x) {
    		for (int y = 0; y < getHeight(); ++y) {
    			if (this.entities[x][y] != null) {
    				entities.add(this.entities[x][y]);
    			}
    		}
    	}
    	return entities;
    }
    
    public GridLocation getEntityLocation(Entity entity) {
    	for (int x = 0; x < getWidth(); ++x) {
    		for (int y = 0; y < getHeight(); ++y) {
    			if (entities[x][y] == entity) {
    				return new GridLocation(x, y);
    			}
    		}
    	}
    	return null;
    }
    
    public Avatar getAvatar() {
    	for (int x = 0; x < getWidth(); ++x) {
    		for (int y = 0; y < getHeight(); ++y) {
    			if (entities[x][y] instanceof Avatar) {
    				return (Avatar) entities[x][y];
    			}
    		}
    	}
    	return null;
    }
    
    public GridLocation getAvatarLocation() {
    	for (int x = 0; x < getWidth(); ++x) {
    		for (int y = 0; y < getHeight(); ++y) {
    			if (entities[x][y] != null && entities[x][y] instanceof Avatar) {
    				return new GridLocation(x, y);
    			}
    		}
    	}
    	return null;
    }
    
    public boolean moveEntity(Entity entity, Course course) {
    	GridLocation entityLocation = getEntityLocation(entity);
    	GridLocation destination = entityLocation.nextGridLocation(course);
    	entity.setDirectionFacing(course);
    	return moveEntity(entity, destination);
    }
    
    public boolean moveAvatar(Course course) {
    	Avatar avatar = getAvatar();
    	return moveEntity(avatar, course);
    }
    
    public void advanceEntity(Entity entity) {
    	Random rand = new Random();
    	if (rand.nextDouble() < 0.3 || entity.isFriendly() == false) { // Entities have 30% chance to take an action or if they're angry
    		if (rand.nextDouble() < 0.5 || entity.isFriendly()) { // 50% Chance to move, or 100% if friendly
    			if (rand.nextDouble() < 0.5 || !entity.isFriendly()) { // 50% towards Avatar, or if unfriendly
    				faceEntity(entity, getAvatarLocation());
    				moveEntity(entity, entity.getDirectionFacing());
    			} else { // Move in a random direction
    				moveEntity(entity, new Course(rand.nextInt(2), rand.nextInt(2)));
    			}
    		} else { // 50% Chance to use an Ability, won't use abilities if friendly
    			int randomAbilityIndex = rand.nextInt(entity.getAbilities().size());
    			Ability ability = entity.getAbilities().get(randomAbilityIndex);
    			entity.getStatistics().changeCurrentMana(100); // Ensure entities always have mana
    			entity.activateAbility(ability, this);
    		}
    	}
    }
    
    public boolean moveEntity(Entity entity, GridLocation destination) {
    	if (isValidGridLocation(destination)) {
  			if (isPassable(entity, destination)) {
  				removeEntity(entity);
  				addEntity(entity, destination);
  				pickUpItems(entity);
  				triggerProximityEffect(entity);
  				return true;
  			}
    	}
    	return false;
    }
    
    public void faceEntity(Entity entity, GridLocation target) {
		GridLocation entityLocation = getEntityLocation(entity);
		int x = 0, y = 0;
		if (target.getX() > entityLocation.getX()) {
			x = 1;
		} else if (target.getX() < entityLocation.getX()) {
			x = -1;
		}
		if (target.getY() > entityLocation.getY()) {
			y = 1;
		} else if (target.getY() < entityLocation.getY()) {
			y = -1;
		}
		entity.setDirectionFacing(new Course(x, y));
	}

	public boolean isPassable(Entity entity, GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		if (getEntity(gridLocation) != null) {
    			return false;
    		} else if (getItem(gridLocation) != null) {
    			if (getItem(gridLocation).getCategory() == Category.OBSTACLE_ITEM) {
    				return false;
    			}
    		}
    		return map.getMaptile(gridLocation).isPassable(entity);
    	}
    	return false;
	}

	public void triggerProximityEffect(Entity entity) {
    	GridLocation entityLocation = getEntityLocation(entity);
    	if (isValidGridLocation(entityLocation)) {
    		int x = entityLocation.getX();
    		int y = entityLocation.getY();
    		if (slots[x][y] != null && slots[x][y].hasItem()) {
    			slots[x][y].getItem().triggerProximity(entity, this);
    			if (slots[x][y].getItem().getCategory() == Category.ONE_SHOT_ITEM) {
    				removeItem(new GridLocation(x, y));
    			}
    		}
    		map.getMaptile(entityLocation).triggerProximityEffect(entity, this);
    	}
    }
    
    public Entity removeEntity(Entity entity) {
    	GridLocation entityLocation = getEntityLocation(entity);
    	if (isValidGridLocation(entityLocation)) {
    		entities[entityLocation.getX()][entityLocation.getY()] = null;
    		return entity;
    	}
    	return null;
    }
    
    public Entity removeEntity(GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		Entity entity = entities[gridLocation.getX()][gridLocation.getY()];
    		entities[gridLocation.getX()][gridLocation.getY()] = null;
    		return entity;
    	}
    	return null;
    }
    
    public boolean addItem(Item item, GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		return slots[gridLocation.getX()][gridLocation.getY()].storeItem(item);
    	}
    	return false;
    }
    
    public Item getItem(GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		return slots[gridLocation.getX()][gridLocation.getY()].getItem();
    	}
    	return null;
    }
    
    public void pickUpItems(Entity entity) {
    	GridLocation entityLocation = getEntityLocation(entity);
    	if (isValidGridLocation(entityLocation)) {
    		Item item = getItem(entityLocation);
    		if (item != null && item.getCategory() == Category.TAKEABLE_ITEM) {
    			if (entity.storeItem(item)) {
    				removeItem(entityLocation);
    			}
    		}
    	}
    }
    
    public Item removeItem(Item item) {
    	for (int x = 0; x < getWidth(); ++x) {
    		for (int y = 0; y < getHeight(); ++y) {
    			if (slots[x][y].getItem() == item) {
    				removeItem(new GridLocation(x, y));
    				return item;
    			}
    		}
    	}
    	return null;
    }
    
    public Item removeItem(GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		Item item = slots[gridLocation.getX()][gridLocation.getY()].getItem();
    		slots[gridLocation.getX()][gridLocation.getY()].removeItem();
    		return item;
    	}
    	return null;
    }
    
    public boolean addProjectile(Projectile projectile, GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		if (projectiles[gridLocation.getX()][gridLocation.getY()] == null) {
    			projectiles[gridLocation.getX()][gridLocation.getY()] = projectile;
    			return true;
    		}
    	}
    	return false;
    } 
    
    public Projectile getProjectile(GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		return projectiles[gridLocation.getX()][gridLocation.getY()];
    	}
    	return null;
    }
    
    public ArrayList<Projectile> getProjectiles() {
    	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    	for (int x = 0; x < getWidth(); ++x) {
    		for (int y = 0; y < getHeight(); ++y) {
    			if (this.projectiles[x][y] != null) {
    				projectiles.add(this.projectiles[x][y]);
    			}
    		}
    	}
    	return projectiles;
    }
    
    public GridLocation getProjectileLocation(Projectile projectile) {
    	for (int x = 0; x < getWidth(); ++x) {
    		for (int y = 0; y < getHeight(); ++y) {
    			if (projectiles[x][y] == projectile) {
    				return new GridLocation(x, y);
    			}
    		}
    	}
    	return null;
    }
    
    public void advanceProjectile(Projectile projectile) {
    	GridLocation destination = getProjectileLocation(projectile).nextGridLocation(projectile.getCourse());
    	moveProjectile(projectile, destination);
    }
    
    public void moveProjectile(Projectile projectile, GridLocation destination) {
    	if (isValidGridLocation(destination)) {
    		removeProjectile(projectile);
    		addProjectile(projectile, destination);
    		if (!isPassable(projectile, destination) || getEntity(destination) != null) {
    			triggerProjectileEffect(projectile);
    			removeProjectile(projectile);
    		}
    	}
	}

	public boolean isPassable(Projectile projectile, GridLocation gridLocation) {
		if (isValidGridLocation(gridLocation)) {
			if (map.getMaptile(gridLocation) != null) {
				return map.getMaptile(gridLocation).isPassable(projectile);
			}
		}
		return false;
	}

	public void triggerProjectileEffect(Projectile projectile) {
    	GridLocation projectileLocation = getProjectileLocation(projectile);
    	if (projectileLocation != null) {
    		int radius = projectile.getEffectRadius() - 1;
    		for (int x = projectileLocation.getX() - radius; x <= projectileLocation.getX() + radius; ++x) {
    			for (int y = projectileLocation.getY() - radius; y < projectileLocation.getY() + radius; ++y) {
    				GridLocation target = new GridLocation(x, y);
    				if (isValidGridLocation(target)) {
    					if (getEntity(target) != null) {
    						int distance = Math.max(Math.abs(x - projectileLocation.getX()), Math.abs(y - projectileLocation.getY()));
    						projectile.triggerEffect(getEntity(target), distance);
    						getEntity(target).setFriendly(false);
    					}
    				}
    			}
    		}
    	}
    }
    
    public Projectile removeProjectile(Projectile projectile) {
    	GridLocation projectileLocation = getProjectileLocation(projectile);
    	if (isValidGridLocation(projectileLocation)) {
    		projectiles[projectileLocation.getX()][projectileLocation.getY()] = null;
    		return projectile;
    	}
    	return null;
    }
    
    public Projectile removeProjectile(GridLocation gridLocation) {
    	if (isValidGridLocation(gridLocation)) {
    		Projectile projectile = projectiles[gridLocation.getX()][gridLocation.getY()];
    		projectiles[gridLocation.getX()][gridLocation.getY()] = null;
    		return projectile;
    	}
    	return null;
    }
    
    public void activateAvatarAbility(Ability ability) {
    	this.getAvatar().activateAbility(ability, this);
	}

	public ArrayList<Decal> getDecals(GridLocation gridLocation) {
    	ArrayList<Decal> decals = new ArrayList<Decal>();
    	if (!isValidGridLocation(gridLocation)) {
    		return decals;
    	}
    	if (getEntity(gridLocation) != null) {
    		decals.add(getEntity(gridLocation).getDecal());
    	}
    	if (getProjectile(gridLocation) != null) {
    		decals.add(getProjectile(gridLocation).getDecal());
    	}
    	if (getItem(gridLocation) != null && getItem(gridLocation).getDecal() != null) {
    		decals.add(getItem(gridLocation).getDecal());
    	}
    	decals.addAll(map.getMaptile(gridLocation).getDecals());
    	return decals;
    }
    
    @Override
	public String toString() {
    	StringBuilder entitiesBuilder = new StringBuilder();
    	StringBuilder slotsBuilder = new StringBuilder();
    	StringBuilder projectilesBuilder = new StringBuilder();

    	entitiesBuilder.append("[");
    	slotsBuilder.append("[");
    	projectilesBuilder.append("[");
    	for (int y = 0; y < getHeight(); ++y) {
    		entitiesBuilder.append("[");
    		slotsBuilder.append("[");
    		projectilesBuilder.append("[");
    		for (int x = 0; x < getWidth(); ++x) {
    			if (entities[x][y] == null) {
    				entitiesBuilder.append("[null]");
    			} else {
    				entitiesBuilder.append(entities[x][y].toString());
    			}
    			if (slots[x][y] == null) {
    				slotsBuilder.append("[null]");
    			} else {
    				slotsBuilder.append(slots[x][y].toString());
    			}
    			if (projectiles[x][y] == null) {
    				projectilesBuilder.append("[null]");
    			} else {
    				projectilesBuilder.append(projectiles[x][y].toString());
    			}
    			if (x < getWidth() - 1) {
                    entitiesBuilder.append(",");
                    slotsBuilder.append(",");
                    projectilesBuilder.append(",");
    			}
    		}
     		entitiesBuilder.append("]");
    		slotsBuilder.append("]");
    		projectilesBuilder.append("]");
    		if (y < getHeight() - 1) {
    			entitiesBuilder.append(",");
                slotsBuilder.append(",");
                projectilesBuilder.append(",");
    		}
    	}
     	entitiesBuilder.append("]");
    	slotsBuilder.append("]");
    	projectilesBuilder.append("]");
    	
		return "[" + entitiesBuilder.toString() + "," + slotsBuilder.toString()
				+ "," + projectilesBuilder.toString() + ","
				+ map.toString() + "," + dialogueIndex + ","
				+ currentDialogue + "]";
    }
    
    public static Level fromString(String string) {
    	Level level = new Level();
    	string = string.trim();
    	String stripped = string.substring(1, string.length() - 1);
    	int outerBracketCount = 0;
    	int itemCount = 0;
    	int start = 0;
    	for (int i = 0; i < stripped.length(); ++i) {
    		if (outerBracketCount == 0 && stripped.charAt(i) == ',') {
    			int innerBracketCount = 0;
    			int innerStart = 0;
    			if (itemCount == 0) {
    				// Entities
    				ArrayList<ArrayList<Entity>> entities = new ArrayList<ArrayList<Entity>>();
    				String entityStrip = stripped.substring(start + 1, i - 1);  // All Entity Rows
    				for (int j = 0; j < entityStrip.length(); ++j) { // Building Rows
    					if ((innerBracketCount == 0 && entityStrip.charAt(j) == ',') || j == entityStrip.length() - 1) {
    						// Have Found a row
    						ArrayList<Entity> entityRow = new ArrayList<Entity>();
    						String rowStripped;
    						if (entityStrip.charAt(j) == ',') {
    							rowStripped = entityStrip.substring(innerStart + 1, j - 1); // Individual Entities
    						} else {
    							rowStripped = entityStrip.substring(innerStart + 1, j); // Taking the last row into account
    						}
    						int individualBracketCount = 0;
    						int individualStart = 0;
    						for (int k = 0; k < rowStripped.length(); ++k) {
    							if ((individualBracketCount == 0 && rowStripped.charAt(k) == ',') || k == rowStripped.length() - 1) {
    								// Found an entity
    								Entity entity;
    								if (rowStripped.charAt(k) == ',') {
    									entity = Entity.fromString(rowStripped.substring(individualStart, k));
    								} else {
    									entity = Entity.fromString(rowStripped.substring(individualStart, k + 1));
    								}
    								entityRow.add(entity); // Add it to the Row
    								individualStart = k + 1;
    							} else if (rowStripped.charAt(k) == '[') {
    								++individualBracketCount;
    							} else if (rowStripped.charAt(k) == ']') {
    								--individualBracketCount;
    							}
    						}
    						entities.add(entityRow);
    						innerStart = j + 1;
    					} else if (entityStrip.charAt(j) == '[') {
    						++innerBracketCount;
    					} else if (entityStrip.charAt(j) == ']') {
    						--innerBracketCount;
    					}
    				}
    				level.entities = convertEntityArrayListToArray(entities);
    				++itemCount;
    			} else if (itemCount == 1) {
    				// Slots
    				ArrayList<ArrayList<Slot>> slots = new ArrayList<ArrayList<Slot>>();
    				String slotStrip = stripped.substring(start + 1, i - 1); // All Slot Rows
    				for (int j = 0; j < slotStrip.length(); ++j) { // Building Rows
    					if ((innerBracketCount == 0 && slotStrip.charAt(j) == ',') || j == slotStrip.length() - 1) {
    						// Have found a row
    						ArrayList<Slot> slotRow = new ArrayList<Slot>();
    						String rowStripped;
    						if (slotStrip.charAt(j) == ',') {
    							rowStripped = slotStrip.substring(innerStart + 1, j - 1); // Individual Slot
    						} else {
    							rowStripped = slotStrip.substring(innerStart + 1, j); // Taking the last row into account
    						}
    						int individualBracketCount = 0;
    						int individualStart = 0;
    						for (int k = 0; k < rowStripped.length(); ++k) {
    							if ((individualBracketCount == 0 && rowStripped.charAt(k) == ',') || k == rowStripped.length() - 1) {
    								// Found a slot
    								Slot slot;
    								if (rowStripped.charAt(k) == ',') {
    									slot = Slot.fromString(rowStripped.substring(individualStart, k));
    								} else {
    									slot = Slot.fromString(rowStripped.substring(individualStart, k + 1));
    								}
    								slotRow.add(slot);
    								individualStart = k + 1;
    							} else if (rowStripped.charAt(k) == '[') {
    								++individualBracketCount;
    							} else if (rowStripped.charAt(k) == ']') {
    								--individualBracketCount;
    							}
    						}
    						slots.add(slotRow);
    						innerStart = j + 1;
    					} else if (slotStrip.charAt(j) == '[') {
    						++innerBracketCount;
    					} else if (slotStrip.charAt(j) == ']') {
    						--innerBracketCount;
    					}
    				}
    				level.slots = convertSlotArrayListToArray(slots);
    				++itemCount;
    			} else if (itemCount == 2) {
    				// Projectiles
    				ArrayList<ArrayList<Projectile>> projectiles = new ArrayList<ArrayList<Projectile>>();
    				String projectileStrip = stripped.substring(start + 1, i - 1); // All Projectile Rows
    				for (int j = 0; j < projectileStrip.length(); ++j) {
    					if ((innerBracketCount == 0 && projectileStrip.charAt(j) == ',') || j == projectileStrip.length() - 1) {
    						// Have found a row
    						ArrayList<Projectile> projectileRow = new ArrayList<Projectile>();
    						String rowStripped;
    						if (projectileStrip.charAt(j) == ',') {
    							rowStripped = projectileStrip.substring(innerStart + 1, j - 1);
    						} else {
    							rowStripped = projectileStrip.substring(innerStart + 1, j);
    						}
    						int individualBracketCount = 0;
    						int individualStart = 0;
    						for (int k = 0; k < rowStripped.length(); ++k) {
    							if ((individualBracketCount == 0 && rowStripped.charAt(k) == ',') || k == rowStripped.length() - 1) {
    								// Found a Projectile
    								Projectile projectile;
    								if (rowStripped.charAt(k) == ',') {
    									projectile = Projectile.fromString(rowStripped.substring(individualStart, k));
    								} else {
    									projectile = Projectile.fromString(rowStripped.substring(individualStart, k + 1));
    								}
    								projectileRow.add(projectile);
    								individualStart = k + 1;
    							} else if (rowStripped.charAt(k) == '[') {
    								++individualBracketCount;
    							} else if (rowStripped.charAt(k) == ']') {
    								--individualBracketCount;
    							}
    						}
    						projectiles.add(projectileRow);
    						innerStart = j + 1;
    					} else if (projectileStrip.charAt(j) == '[') {
    						++innerBracketCount;
    					} else if (projectileStrip.charAt(j) == ']') {
    						--innerBracketCount;
    					}
    				}
    				level.projectiles = convertProjectileArrayListToArray(projectiles);
    				++itemCount;
    			} else if (itemCount == 3) {
    				level.map = Map.fromString(stripped.substring(start, i - 1));
    				start = i + 1;
    				break;
    			}
    			start = i + 1;
    		} else if (stripped.charAt(i) == '[') {
    			++outerBracketCount;
    		} else if (stripped.charAt(i) == ']') {
    			--outerBracketCount;
    		}
    	}
    	// Now We handle the Easy stuff
    	String[] rest = stripped.substring(start, stripped.length() - 1).split(",");
    	level.dialogueIndex = Integer.parseInt(rest[0]);
    	if (rest.length == 2) {
    		level.currentDialogue = rest[1];
    	}
    	return level;
    }

    // Java doesn't let you create Generic Arrays, and some witchery with the Method Overloading
    // Man JAVA is really annoying right now, wish we had used C[(++)|#]
	private static Entity[][] convertEntityArrayListToArray(
			ArrayList<ArrayList<Entity>> grid) {
		Entity[][] customGrid = new Entity[grid.get(0).size()][grid.size()];
		for (int y = 0; y < grid.size(); ++y) {
			for (int x = 0; x < grid.get(0).size(); ++x) {
				if (x < grid.get(y).size() && grid.get(y).get(x) != null) {
					customGrid[x][y] = grid.get(y).get(x);
				} else {
					customGrid[x][y] = null;
				}
			}
		}
		return customGrid;
	}

	private static Slot[][] convertSlotArrayListToArray(
			ArrayList<ArrayList<Slot>> grid) {
		Slot[][] customGrid = new Slot[grid.get(0).size()][grid.size()];
		for (int y = 0; y < grid.size(); ++y) {
			for (int x = 0; x < grid.get(0).size(); ++x) {
				if (x < grid.get(y).size() && grid.get(y).get(x) != null) {
					customGrid[x][y] = grid.get(y).get(x);
				} else {
					customGrid[x][y] = new Slot(SlotCategory.ANY_SLOT);
				}
			}
		}
		return customGrid;
	}

	private static Projectile[][] convertProjectileArrayListToArray(
			ArrayList<ArrayList<Projectile>> grid) {
		Projectile[][] customGrid = new Projectile[grid.get(0).size()][grid.size()];
		for (int y = 0; y < grid.size(); ++y) {
			for (int x = 0; x < grid.get(0).size(); ++x) {
				if (x < grid.get(y).size() && grid.get(y).get(x) != null) {
					customGrid[x][y] = grid.get(y).get(x);
				} else {
					customGrid[x][y] = null;
				}
			}
		}
		return customGrid;
	}
    
    public int getHeight() {
    	return map.getHeight();
    }
    
    public int getWidth() {
    	return map.getWidth();
    }
    
    public Map getMap() {
    	return this.map;
    }
    
    public void setMap(Map map) {
    	this.map = map;
    }
    
    // TODO we should remove these functions that are here for legacy reasons
    public void initDialogueStrings() {
        dialogueStrings.add("Ugh, my head...");
        dialogueStrings.add("I must have been knocked out...");
        dialogueStrings.add("I have no idea how long it's been since I've had my last drink...");
        dialogueStrings.add("The accumulated hangover of the past few years could kill me...");
        dialogueStrings.add("Best not to take it all at once.");
    }
    
    public String getNextDialogue() {
        int numDialogueStrings = dialogueStrings.size();
        if (dialogueIndex >= numDialogueStrings) {
            return null; //out of dialogue
        } else {
            String dialogue = dialogueStrings.get(dialogueIndex);
            dialogueIndex++;
            return dialogue;
        }
    }

    public String getCurrentDialogue() {
        return currentDialogue;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
    
    public static void main(String[] args) {
    	Level orig = new Level();
    	orig.addEntity(new Avatar(), new GridLocation(1, 1));
    	orig.addProjectile(new Projectile(), new GridLocation(1, 2));
    	orig.addItem(new OneShotItem(), new GridLocation(2, 2));
    	orig.addItem(new TakeableItem(), new GridLocation(2, 1));
    	orig.map.getMaptile(new GridLocation(2, 2)).setAreaEffect(new HealDamageAreaEffect());
    	orig.map.getMaptile(new GridLocation(2, 2)).setTerrain(new Water());
    	Level restored = Level.fromString(orig.toString());
    	if (orig.dialogueIndex == 0) {
    		return;
    	}
    	
    	if (orig.toString().equals(restored.toString()) == false) {
    		System.out.println("Serialized Strings are different");
    	}
    	if (orig.getHeight() != restored.getHeight()) {
    		System.out.println("Different Heights");
    	}
    	if (orig.getWidth() != restored.getWidth()) {
    		System.out.println("Different Widths");
    	}
    	if (orig.entities.length != restored.entities.length) {
    		System.out.println("Entity Width doesn't line up");
    	}
    	if (orig.entities[0].length != restored.entities[0].length) {
    		System.out.println("Entity Height doesn't line up");
    	}
    	if (orig.projectiles.length != restored.projectiles.length) {
    		System.out.println("Projectile Width doesn't line up");
    	}
    	if (orig.projectiles[0].length != restored.projectiles[0].length) {
    		System.out.println("Projectile Height doesn't line up");
    	}
    	if (orig.slots.length != restored.slots.length) {
    		System.out.println("Slot Width doesn't line up");
    	}
    	if (orig.slots[0].length != restored.slots[0].length) {
    		System.out.println("Slot Height doesn't line up");
    	}
    	if (orig.slots[1][1].toString().equals(restored.slots[1][1].toString()) == false) {
    		System.out.println("Different Slots");
    	}
    	if (orig.dialogueIndex != restored.dialogueIndex) {
    		System.out.println("Dialogue Index is different");
    	}
    	if (orig.currentDialogue.equals(restored.currentDialogue) == false) {
    		System.out.println("Current Dialogue is different");
    	}
    	if (orig.dialogueStrings.equals(restored.dialogueStrings) == false) {
    		System.out.println("Dialogue strings are different");
    	}
    	if (orig.map.toString().equals(restored.map.toString()) == false) {
    		System.out.println("Different Maps");
    	}
    	for (int x = 0; x < orig.getWidth(); ++x) {
    		for (int y = 0; y < orig.getHeight(); ++y) {
    			if (orig.entities[x][y] != null && restored.entities[x][y] != null) {
    				if (orig.entities[x][y].toString().equals(restored.entities[x][y].toString()) == false) {
    					System.out.println("Entities differ");
    				}
    			} else if (orig.entities[x][y] != restored.entities[x][y]) {
   					System.out.println("Entities differ");
    			}
    			if (orig.slots[x][y].toString().equals(restored.slots[x][y].toString()) == false) {
    				System.out.println("Slots differ");
    			}
    			if (orig.projectiles[x][y] != null && restored.projectiles[x][y] != null) {
    				if (orig.projectiles[x][y].toString().equals(restored.projectiles[x][y].toString()) == false) {
    					System.out.println("Projectiles differ");
    				}
    			} else if (orig.projectiles[x][y] != restored.projectiles[x][y]) {
   					System.out.println("Projectiles differ");
    			}
    		}
    	}
    	if (orig.getAvatar().toString().equals(restored.getAvatar().toString()) == false) {
    		System.out.println("Avatars differ");
    	}
    }
}
