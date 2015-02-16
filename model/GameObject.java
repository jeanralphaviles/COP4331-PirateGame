package model;

import java.io.IOException;

import model.entity.Avatar;
import model.map.Maptile;
/**
 * GameObject saves state of game.
 * Little to no logic needed
 * (state = Avatar and Level.
 */
public class GameObject {

    /*Properties*/
    
    private Avatar avatar;
    private Level level;

    /*Constructors*/
    
    public GameObject( ) {
        this.avatar = new Avatar();
        this.level = new Level();
        Maptile avatarTile = this.level.getMap().getMapTile(1, 1);
        this.avatar.move(avatarTile);
    }

    /*Methods*/
    //Shouldn't have much logic, if any!!!
    
    /*Get-Sets*/

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
    
    @Override
	public String toString() {
    	return "[" + avatar.toString() + "]";
    }

	public static GameObject fromString(String string) throws IOException {
		String stripped = string.substring(1, string.length() - 1);
		GameObject obj = new GameObject();
		obj.avatar = Avatar.fromString(stripped);
		obj.getLevel().getMap().addEntity(obj.avatar);
		// TODO ENTITIESSSSSSSSSSS!@#!@#@!#@!#@!#!@#!@
		return obj;
	}
    
}
