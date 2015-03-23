package controller;

import model.Model;
import controller.IntentMap.IntentMap;
import java.util.ArrayList;
import javax.swing.JButton;
import model.Level;
import view.screen.DialogueScreen;
import view.screen.GameScreen;
import view.screen.MainScreen;
import view.screen.OptionsScreen;
import view.screen.Screen;
import view.screen.popup.LoadPopup;
import view.screen.popup.LoadSavePopup;
import view.screen.popup.NewGamePopup;
import view.screen.popup.PausePopup;

public abstract class Controller {

    /*Properties*/
    protected Model model;
    //
    protected ArrayList<IntentMap> intentMaps = new ArrayList<IntentMap>(1);

    /*Constructors*/
    public Controller(Model model) {
        this.model = model;
    }

    /*Methods*/
    /**
     * print; prints out content. Test method.
     *
     * @param content - content to print out
     */
    protected void print(String content) {
        System.out.println(content);
    }

    private void exit() {
        System.exit(0);
    }
    
    private void gotoNewGameMenu() {
        Screen screen = new NewGamePopup(model);
        model.launchScreen(screen);
    }
    
    private void goToLoadSaveScreen(){
        
        Screen screen = new LoadSavePopup(model);
        model.launchScreen(screen);
    }
    
     private void goToLoadScreen(){
        
        Screen screen = new LoadPopup(model);
        model.launchScreen(screen);
    }

    private void gotoGameScreen() {
        Screen screen = new GameScreen(model);
        model.launchScreen(screen);
    }

    private void gotoPauseScreen() {
        Screen screen = new PausePopup(model);
        model.launchScreen(screen);
    }

    private void gotoMainScreen() {
        Screen screen = new MainScreen(model);
        model.launchScreen(screen);
    }
    
    private void gotoOptionsScreen() {
        Screen screen = new OptionsScreen(model);
        model.launchScreen(screen);
    }

    private void begin() {
        Screen screen = new DialogueScreen(model);
        model.launchScreen(screen);
    }
    
    private void resetControlsToDefault() {
        //
    }

    private void showNextDialogue() {
//        String dialogue = model.getNextDialogue();
//        if (dialogue != null) { //there is more dialogue to display
//            model.setDialogue(dialogue);
//        } else { //no more dialogue to display
//            Screen screen = model.getCurrentScreen();
//            if (screen instanceof DialogueScreen) { //done with intro
//                gotoGameScreen();
//            } else { //clear dialogue field
//                model.setDialogue("");
//            }
//        }
        boolean anotherDialogueLine = Level.getResponse().advanceResponse();
        if (!anotherDialogueLine) {
            Screen screen = model.getCurrentScreen();
            if (screen instanceof DialogueScreen) { //done with intro
                gotoGameScreen();
            }
        }
    }
    
    private void activateVirtualKey(IntentMap im) {
        JButton button = (JButton)im.getObject();
        button.doClick();
    }
    
    protected void action(IntentMap im) {
        Intent intent = im.getIntent();
        switch(intent) {
            case ACTIVATE_VIRTUAL_KEY:
                activateVirtualKey(im);
                break;
        }
        action(intent);
    }

    protected void action(Intent intent) {
        
        switch (intent) {
            case SHOW_DIALOGUE:
                showNextDialogue();
                break;
            case GOTO_MAIN:
                gotoMainScreen();
                break;
            case GOTO_PAUSE:
                gotoPauseScreen();
                break;
            case GOTO_GAME:
                gotoGameScreen();
                break;
            case GOTO_OPTIONS:
                gotoOptionsScreen();
                break;
            case NEW:
                gotoNewGameMenu();
                break;
            case GOTO_LOADSAVE:
                goToLoadSaveScreen();
                break;
            case GOTO_LOAD:
                goToLoadScreen();
            case RESET_DEFAULT_CONTROLS:
                resetControlsToDefault();
                break;
            case EXIT:
                exit();
                break;
            case BEGIN:
                begin();
                break;
            default:
                break;
        }
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
