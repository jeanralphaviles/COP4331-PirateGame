package model;

import java.util.ArrayList;

import model.entity.Avatar;
import model.entity.Entity;
import model.map.Map;

public class GameObject {

    /*Properties*/
    
    private Avatar avatar;
    private Level level;

    /*Constructors*/
    
    public GameObject( ) {
//        this.avatar = new Avatar(); //still making sure this serializes
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
    
}
