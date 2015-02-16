package model;

import java.util.ArrayList;

import model.entity.Entity;
import model.map.Map;
import model.map.Maptile;

/**
 * @author Jean-Ralph Aviles
 */
public class Level {

    private ArrayList<Entity> entities = new ArrayList<Entity>(1);
    private Map map;
    //
    private int dialogueIndex = 0;
    private ArrayList<String> dialogueStrings = new ArrayList<String>(1);
    //
    private String currentDialogue = ""; //let this be set outside

    public Level() {
        map = new Map();
        initDialogueStrings();
    }
    
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

    /**
     * @param entities List of Entities for a Level
     * @param map Level's Map TODO figure out where to place the entities
     */
    public Level(ArrayList<Entity> entities, Map map) {
        setEntities(entities);
        setMap(map);
    }

    /**
     * @param entity Entity to add
     * @return Entity if successful, null if not TODO figure out where to place
     * the entity
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

    public String getCurrentDialogue() {
        return currentDialogue;
    }

    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
    
    

}
