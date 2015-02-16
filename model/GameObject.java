package model;

import java.io.IOException;
import java.util.ArrayList;

import model.entity.Avatar;
import model.entity.Entity;
import model.map.Map;
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
        this.avatar = new Avatar(); //still making sure this serializes
        this.level = new Level();
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
    
    public String toString() {
    	return "[" + avatar.toString() + "]";
    }

	public static GameObject fromString(String string) throws IOException {
		String stripped = string.substring(1, string.length() - 1);
		GameObject obj = new GameObject();
		obj.avatar = Avatar.fromString(stripped);
		return obj;
	}
    
}
