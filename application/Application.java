package application;

import java.io.IOException;

import utility.LoadSave;
import controller.NumpadController;
import model.Model;

public class Application {

    /*Properties*/
    private static Model model;
    private static final String modelFilename = "MODEL_FILE.txt";
    private static LoadSave loadsave;
    private static NumpadController auxController;
    private static final int updatesPerSecond = 1;
    
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
        Model m = null; //loadsave.loadModel(modelFilename);
        if (m == null) { //load failed
            m = new Model(); //create Default
        }
       return m;
    }

    public static void initAuxiliaryController() {
        auxController = new NumpadController(model);
        model.setAuxController(auxController);
    }

    public static void launchModel(int updatesPerSecond) {
        model.launch(updatesPerSecond, loadsave);
    }
    
    private static void testSave() {
        Model m = new Model();
        loadsave.saveModel(m, modelFilename);
    }
    
    /*Get-Sets*/

}
