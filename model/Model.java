package model;

import utility.LoadSave;
import utility.UtilityData;
import view.screen.MainScreen;
import view.screen.Screen;

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

    /*Constructors*/
    public Model() {
        this.gameObject = new GameObject();
        this.utilityData = new UtilityData();
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
        launchScreen(new MainScreen(this));
    }
/**Launch a specific screen
 * @param screen is the specific screen to launch*/
    public void launchScreen(Screen screen) {
        this.currentScreen = screen;
        screen.init();
    }
    
    private void loadLevel() {
        //
    }

    public void loadNextLevel() {
        //
    }

    /*Get-Sets*/    

}
