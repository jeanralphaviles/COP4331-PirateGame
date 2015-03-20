package view.screen;

import controller.screenController.ScreenController;
import java.util.ArrayList;
import model.Model;
import controller.controllerMap.IntentComponentMap;
import view.viewport.DialogueScreenViewport;

public class DialogueScreen extends Screen {

    public DialogueScreen(Model model) {
        super(model);
    }
        
    @Override
    public void createView() {
        viewPort = new DialogueScreenViewport();
    }
    
    @Override
    public void createController() {
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new ScreenController(model, icms);
    }

}
