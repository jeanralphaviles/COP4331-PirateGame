package application;

import java.io.IOException;

import utility.LoadSave;
import java.awt.KeyEventDispatcher;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import model.Model;
import utility.Course;

public class RunGame {

    /*Properties*/
    private static Model model;
    private static final String modelFilename = "MODEL_FILE.txt";
    private static LoadSave loadsave;
    private static KeyboardFocusManager auxController;
    private static final int updatesPerSecond = 3;

    /*Constructors*/
    /*Methods*/
    public static void main(String[] args) throws IOException {
        loadsave = new LoadSave();
        model = initModel(modelFilename, loadsave);
        loadsave.saveModel(model, modelFilename); //for testing

        initAuxiliaryController();

        launchModel(updatesPerSecond); //execution goes to main game loop
    }

    public static Model initModel(String modelFilename, LoadSave loadSave) throws IOException {
        Model m = loadsave.loadModel(modelFilename);
        if (m == null) { //load failed
            m = new Model(); //create Default
        }
        return m;
    }

    public static void initAuxiliaryController() {
        auxController = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        auxController.addKeyEventPostProcessor(new EnterKeyListener(model));
    }

    public static void launchModel(int updatesPerSecond) {
        model.launch(updatesPerSecond, loadsave);
    }

    private static void testSave() {
        Model m = new Model();
        loadsave.saveModel(m, modelFilename);
    }

    /*Get-Sets*/
    /*Inner classes*/
    private class ExitKeyEventDispatcher implements KeyEventDispatcher {

        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
                e.consume();
            }
            return false;
        }
    }
}

class EnterKeyListener implements KeyEventPostProcessor {

    private Model model;

    public EnterKeyListener(Model model) {
        this.model = model;
    }

    @Override
    public boolean postProcessKeyEvent(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if (keyCode == KeyEvent.VK_NUMPAD1) {
                System.out.println("you pressed 1");
                model.move(new Course(-1, -1));
            } else if (keyCode == KeyEvent.VK_NUMPAD2 || keyCode == KeyEvent.VK_DOWN) {
                System.out.println("pressed 2 move down");
                model.move(new Course(0, -1));
            } else if (keyCode == KeyEvent.VK_NUMPAD3) {
                System.out.println("pressed 3 move left down");
                model.move(new Course(1, -1));
            } else if (keyCode == KeyEvent.VK_NUMPAD4 || keyCode == KeyEvent.VK_LEFT) {
                System.out.println("you pressed 4 move left");
                model.move(new Course(-1, 0));
            } else if (keyCode == KeyEvent.VK_NUMPAD6 || keyCode == KeyEvent.VK_RIGHT) {
                System.out.println("you pressed 6 move right");
                model.move(new Course(1, 0));
            } else if (keyCode == KeyEvent.VK_NUMPAD7) {
                System.out.println("you pressed 7 move top left");
                model.move(new Course(-1, 1));
            } else if (keyCode == KeyEvent.VK_NUMPAD8 || keyCode == KeyEvent.VK_UP) {
                System.out.println("you pressed 8 move top");
                model.move(new Course(0, 1));
            } else if (keyCode == KeyEvent.VK_NUMPAD9) {
                System.out.println("you pressed 9 move top right");
                model.move(new Course(1, 1));
            } else if (keyCode == KeyEvent.VK_P) {
                JOptionPane.showMessageDialog(null, "You've Paused the Game!");
            } else {
                System.out.println("You pressed an invalid control");
            }
        }
        return true;
    }
}
