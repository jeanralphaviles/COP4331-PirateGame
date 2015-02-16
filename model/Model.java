package model;

import model.entity.occupation.Occupation;
import utility.LoadSave;
import utility.UtilityData;
import view.screen.MainScreen;
import view.screen.Screen;
import view.viewport.MainWindow;

public class Model extends Thread {

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
        this.utilityData = new UtilityData();
    }

    /*Methods*/
    public void launch(int updatesPerSecond, LoadSave loadSave) {
        this.updatesPerSecond = updatesPerSecond;
        this.loadsave = loadSave;

        launchFirstScreen();
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
    
    public void setAvatarNickname(String nickname) {
        this.gameObject.getAvatar().setNickname(nickname);
    }
    
    public void setAvatarOccupation(Occupation occupation) {
        this.gameObject.getAvatar().setOccupation(occupation);
    }
    
//    public void save() {
//        save(gameObject);
//    }
    
    public void updateView() {
        currentScreen.updateView(this.gameObject);
        System.out.println("Model updated view");
    }

    public void mainGameLoop() {
        //ultimately, more logic in here (e.g. paused)
        updateView();
    }

    private void launchFirstScreen() {
        mainWindow = new MainWindow();
        launchScreen(new MainScreen(this));
    }

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

    /*Get-Sets*/    

}
