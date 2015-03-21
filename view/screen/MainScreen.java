package view.screen;

import controller.screenController.ScreenController;
import java.util.ArrayList;
import model.Model;
import controller.controllerMap.IntentComponentMap;
import view.viewport.MainScreenViewport;

public class MainScreen extends Screen {

    public MainScreen(Model model) {
        super(model);
    }
        
    @Override
    public void createView() {
        viewPort = new MainScreenViewport();
    }
    
    @Override
    public void createController() {
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new ScreenController(model, icms);
    }

}
