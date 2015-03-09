package controller;

import model.Model;
import utility.IntentComponentMap;
import utility.IntentComponentMap.Intent;
import view.screen.DialogueScreen;
import view.screen.GameScreen;
import view.screen.MainScreen;
import view.screen.Screen;
import view.screen.popup.NewGamePopup;
import view.screen.popup.PausePopup;

public abstract class Controller {

    /*Properties*/
    protected Model model;

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

    private void load() {
        //
    }

    private void save() {
        //
    }

    private void exit() {
        System.exit(0);
    }

    private void gotoNewGameMenu() {
        Screen screen = new NewGamePopup(model);
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

    private void begin() {
        Screen screen = new DialogueScreen(model);
        model.launchScreen(screen);
    }

    private void showNextDialogue() {
        String dialogue = model.getNextDialogue();
        if (dialogue != null) {
            model.setDialogue(dialogue);
        } else {
            Screen screen = model.getCurrentScreen();
            if (screen instanceof DialogueScreen) {
                gotoGameScreen();
            } else if (screen instanceof GameScreen) {
                gotoMainScreen();
            }
        }
    }
    
    protected void action(IntentComponentMap icm) {
        Intent intent = icm.getIntent();
        action(intent);
    }

    protected void action(Intent intent) {
        //processUserInput(this.model);
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
            case NEW:
                gotoNewGameMenu();
                break;
            case LOAD:
                load();
                break;
            case SAVE:
                save();
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
