package utility;

import java.util.ArrayList;

public class UtilityData {

    /*Properties*/
    private ArrayList<String> gameObjectFilenames = new ArrayList<String>(1);
    
    /*Constructors*/
    public UtilityData() {
        //default values
    }
    
    /*Methods*/
    
    public void addGameObjectFilename(String filename) {
        this.gameObjectFilenames.add(filename);
    }
    
    /*Get-Sets*/

    public ArrayList<String> getGameObjectFilenames() {
        return gameObjectFilenames;
    }

    public void setGameObjectFilenames(ArrayList<String> gameObjectFilenames) {
        this.gameObjectFilenames = gameObjectFilenames;
    }
    
}
