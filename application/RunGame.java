package application;

import controller.physicalController.PhysicalController;
import controller.physicalController.KeyboardController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JOptionPane;
import model.Model;
import model.entity.Avatar;
import view.viewport.MainWindow;

public class RunGame {

    /*Properties*/
    
    private static Model model;
    private static final String modelFilename = "MODEL_FILE.txt";
    private static PhysicalController auxController;
    private static final int viewUpdatesPerSecond = 10;
    public static MainWindow mainWindow;

    /*Constructors*/
    
    /*Methods*/
    
    public static void main(String[] args) throws IOException {
        model = initModel(modelFilename);

        initAuxiliaryController();
        initMainWindow();

        launchModel(viewUpdatesPerSecond); //execution goes to main game loop
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
        auxController = new KeyboardController(model);
    }

    public static void initMainWindow() {
        mainWindow = new MainWindow();
    }

    public static void launchModel(int updatesPerSecond) {
        model.launch(updatesPerSecond, null);
    }
    
    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message,"ERROR MESSAGE",JOptionPane.WARNING_MESSAGE);
    }
    
    /*Get-Sets*/

    public static PhysicalController getAuxController() {
        return auxController;
    }
    
}
