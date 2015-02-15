package model;

import utility.LoadSave;
import utility.UtilityData;
import view.screen.Screen;

public class Model extends Thread {

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
    public void launch(int updatesPerSecond, LoadSave loadSave) {
        this.updatesPerSecond = updatesPerSecond;
        this.loadsave = loadSave;

        //launchFirstScreen(new MainScreen(this));
        this.start(); //automagically calls run (thread thing)
    }

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

    public void load(String gameObjectFilename) {
        loadsave.loadGameObject(gameObjectFilename);
    }

    private void save(GameObject gameObject, String gameObjectFilename) {
        loadsave.saveGameObject(gameObject, gameObjectFilename);
    }
    
//    public void save() {
//        save(gameObject);
//    }
    
    public void updateView() {
        System.out.println("WEEEEEEEEEEEE");
    }

    public void mainGameLoop() {
        //ultimately, more logic in here (e.g. paused)
        updateView();
    }

    private void launchFirstScreen() {
        //
    }

//    public void launchScreen(Screen screen) {
//        //
//    }
    private void loadLevel() {
        //
    }

    public void loadNextLevel() {
        //
    }

    /*Get-Sets*/    

}
