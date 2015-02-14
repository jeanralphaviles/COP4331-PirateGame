package application;

import utility.LoadSave;
import controller.Controller;
import model.Model;

public class Application {

    /*Properties*/
    private static Model model;
    private static final String modelFilename = "MODEL_FILE.txt";
    private static LoadSave loadSave;
    private static Controller auxController;
    private static int updatesPerSecond;
    
    /*Constructors*/
    
    /*Methods*/

    public static void main(String[] args) {

        loadSave = new LoadSave();
	model = initModel(modelFilename, loadSave);

	initAuxiliaryController();

	launchModel(updatesPerSecond); //execution goes to main game loop
    }

    public static Model initModel(String modelFilename, LoadSave loadSave) {
//        Model m = loadsave.loadModel(modelFileName);
//        if (m == null) { //load failed
//            m = Model.createDefault();
//        }
//        return m;
        return null;
    }

    public static void initAuxiliaryController() {
//        auxController = new NumPadController();
    }

    public static void launchModel(int updatesPerSecond) {
//        model.mainGameLoop(updatesPerSecond);
    }
    
    /*Get-Sets*/

}
