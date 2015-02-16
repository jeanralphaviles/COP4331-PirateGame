package model;

import model.entity.occupation.Occupation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.entity.Entity;
import model.map.Map;
import model.map.Maptile;
import utility.ItemGenerator;
import utility.LoadSave;
import utility.MapGenerator;
import utility.UtilityData;
import view.screen.MainScreen;
import view.screen.Screen;
import view.viewport.MainWindow;

public class Model extends Thread {
	/**
	 *Model runs main game loop. Holds all relevant game logic and state(via gameObject)
	 */
    /*Properties*/
    private GameObject gameObject;
    private UtilityData utilityData;
    private LoadSave loadsave;
    private Screen currentScreen;
    private int updatesPerSecond;
    //
    private MainWindow mainWindow;

    /*Constructors*/
    public Model() {
        this.gameObject = new GameObject();
        this.setUtilityData(new UtilityData());
    }

    /*Methods*/
    /**Launch launches the proram and starts it running
     * @param updatesPerSecond - how many times per second it needs to update
     * @param loadSave - load save object. pass file to load save and load game.
     */
    public void launch(int updatesPerSecond, LoadSave loadSave) {
        this.updatesPerSecond = updatesPerSecond;
        this.loadsave = loadSave;

        launchFirstScreen();
        this.start(); //automagically calls run (thread thing)
    }

    /**runs main game loop. Uses threads
     */
    @Override
    public void run() {
        System.out.println("Model Started ");
        int sleepTime = 1000 / updatesPerSecond; //milliseconds to sleep
        while (true) {
            mainGameLoop();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    /**Load old game
     * @param gameObjectFilename - load from old class
     */
    public void load(String gameObjectFilename) {
        loadsave.loadGameObject(gameObjectFilename);
    }
    
    /** save current game
     * @param gameObject - get state from gameObject
     * @param gameObjectFilename - create file name and save state in directory.
     */
    private void save(GameObject gameObject, String gameObjectFilename) {
        loadsave.saveGameObject(gameObject, gameObjectFilename);
    }
    
    public void setAvatarNickname(String nickname) {
        this.gameObject.getAvatar().setNickname(nickname);
    }
    
    public void setAvatarOccupation(Occupation occupation) {
        this.gameObject.getAvatar().setOccupation(occupation);
    }
    
//    public void save() {
//        save(gameObject);
//    }
    
    /**
     * Updateview sends message to view n times per second as identified in launch method
     */
    public void updateView() {
        currentScreen.updateView(this.gameObject);
        System.out.println("Model updated view");
    }
    
    /**
     * Main game loop updates view with updated model state. If game is paused then state is put on hold, and view is (as a result) not updated.
     */
    public void mainGameLoop() {
        //ultimately, more logic in here (e.g. paused)
        updateView();
    }
/** launch main screen to start game*/
    private void launchFirstScreen() {
        mainWindow = new MainWindow();
        launchScreen(new MainScreen(this));
    }
/**Launch a specific screen
 * @param screen is the specific screen to launch*/
    public void launchScreen(Screen screen) {
        mainWindow.displayScreen(screen);
        this.currentScreen = screen;
    }
    
    private void loadLevel() {
        //
    }

    public void loadNextLevel() {
        //
    }
    
    public void loadFirstLevel() {
    	loadLevel(1);
    }

	public void loadLevel(int levelNum) {
		if (levelNum < 1) {
			return;
		}
		Map map;
		try {
			map = MapGenerator.generateMap(new File("Levels/Map" + levelNum + ".csv"));
			ItemGenerator.generateItems(new File("Levels/Items" + levelNum + ".csv"), map);
			Maptile avatarMapTile = map.getMapTile(map.getWidth()/2, map.getHeight()/2);
			Level level = new Level(new ArrayList<Entity>(), map);
			this.gameObject.setLevel(level);
			this.gameObject.getAvatar().move(avatarMapTile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    /*Get-Sets*/

	public UtilityData getUtilityData() {
		return utilityData;
	}

	public void setUtilityData(UtilityData utilityData) {
		this.utilityData = utilityData;
	}    
	
	public String toString() {
		return "[" + gameObject.toString() + "]";
	}
	
	public static Model fromString(String string) throws IOException {
		System.out.print(string);
		if (string.length() == 0) {
			return new Model();
		}
		String stripped = string.substring(1, string.length() - 1);
		Model model = new Model();
		model.gameObject = GameObject.fromString(stripped);
		return model;
	}
}
