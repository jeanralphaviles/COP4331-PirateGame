package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.awt.KeyEventPostProcessor;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import model.Model;
import model.entity.Avatar;
import utility.Course;
import view.viewport.MainWindow;

public class RunGame {

    /*Properties*/
    private static Model model;
    private static final String modelFilename = "MODEL_FILE.txt";
    private static KeyboardFocusManager auxController;
    private static final int updatesPerSecond = 10;
    public static MainWindow mainWindow;

    /*Constructors*/
    /*Methods*/
    public static void main(String[] args) throws IOException {
        model = initModel(modelFilename);

        initAuxiliaryController();
        initMainWindow();

        launchModel(updatesPerSecond); //execution goes to main game loop
    }

    public static Model initModel(String modelFilename) {
        try {
            URL resource = ClassLoader.getSystemClassLoader().getResource(modelFilename);
            File file = new File(resource.toURI());
            if (file != null && file.exists()) {
                StringBuilder fileContents = new StringBuilder((int) file.length());
                Scanner scanner = new Scanner(file);
                String lineSeparator = System.getProperty("line.separator");
                while (scanner.hasNextLine()) {
                    fileContents.append(scanner.nextLine() + lineSeparator);
                }
                scanner.close();
                if (fileContents.toString().trim().length() != 0) {
                    return Model.fromString(fileContents.toString());
                } else {
                    Model m = new Model();
                    m.loadLevels(1, new Avatar());
                }
            }
        } catch (URISyntaxException | FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
        return new Model();
    }

    public static void initAuxiliaryController() {
        auxController = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        auxController.addKeyEventPostProcessor(new EnterKeyListener(model, modelFilename));
    }

    public static void initMainWindow() {
        mainWindow = new MainWindow();
    }

    public static void launchModel(int updatesPerSecond) {
        model.launch(updatesPerSecond, null);
    }
}

class EnterKeyListener implements KeyEventPostProcessor {

    private Model model;
    private String modelFilename;

    public EnterKeyListener(Model model, String modelFilename) {
        this.model = model;
        this.modelFilename = modelFilename;
    }

    @Override
    public boolean postProcessKeyEvent(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if (keyCode == KeyEvent.VK_NUMPAD1) {
                System.out.println("you pressed 1");
                model.moveAvatar(new Course(-1, -1));
            } else if (keyCode == KeyEvent.VK_NUMPAD2 || keyCode == KeyEvent.VK_DOWN) {
                System.out.println("pressed 2 move down");
                model.moveAvatar(new Course(0, -1));
            } else if (keyCode == KeyEvent.VK_NUMPAD3) {
                System.out.println("pressed 3 move left down");
                model.moveAvatar(new Course(1, -1));
            } else if (keyCode == KeyEvent.VK_NUMPAD4 || keyCode == KeyEvent.VK_LEFT) {
                System.out.println("you pressed 4 move left");
                model.moveAvatar(new Course(-1, 0));
            } else if (keyCode == KeyEvent.VK_NUMPAD6 || keyCode == KeyEvent.VK_RIGHT) {
                System.out.println("you pressed 6 move right");
                model.moveAvatar(new Course(1, 0));
            } else if (keyCode == KeyEvent.VK_NUMPAD7) {
                System.out.println("you pressed 7 move top left");
                model.moveAvatar(new Course(-1, 1));
            } else if (keyCode == KeyEvent.VK_NUMPAD8 || keyCode == KeyEvent.VK_UP) {
                System.out.println("you pressed 8 move top");
                model.moveAvatar(new Course(0, 1));
            } else if (keyCode == KeyEvent.VK_NUMPAD9) {
                System.out.println("you pressed 9 move top right");
                model.moveAvatar(new Course(1, 1));
            } else if (keyCode == KeyEvent.VK_P) {
                JOptionPane.showMessageDialog(null, "You've Paused the Game!");
            } else if (keyCode == KeyEvent.VK_BACK_SLASH) {
                System.out.println("Saving Model");
                saveModel();
            } else if (keyCode == KeyEvent.VK_ENTER) {
                System.out.println("Loading Model");
                loadModel();
            } else {
                System.out.println("You pressed an invalid control");
            }
        }
        return true;
    }

    public void saveModel() {
        if (model != null) {
            try {
                File file = new File(modelFilename);
                if (file.exists() == false) {
                    file.createNewFile();
                }

                String savedModel = model.toString();
                FileOutputStream fstream = new FileOutputStream(file, false);
                fstream.write(savedModel.getBytes());
                fstream.flush();
                fstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadModel() {
        try {
            File file = new File(modelFilename);
            if (file != null && file.exists()) {
                StringBuilder fileContents = new StringBuilder((int) file.length());
                Scanner scanner = new Scanner(file);
                String lineSeparator = System.getProperty("line.separator");
                while (scanner.hasNextLine()) {
                    fileContents.append(scanner.nextLine() + lineSeparator);
                }
                scanner.close();
                Model newModel;

                if (fileContents.toString().trim().length() != 0) {
                    System.out.println("Loading Model from DISK");
                    newModel = Model.fromString(fileContents.toString());
                } else {
                    newModel = new Model();
                    newModel.loadLevels(1, new Avatar());
                }
                model.setGameObject(newModel.getGameObject());
                model.setUtilityData(newModel.getUtilityData());
            }
        } catch (FileNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
    }

}
