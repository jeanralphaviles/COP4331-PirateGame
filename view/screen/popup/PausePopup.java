package view.screen.popup;

import controller.screenController.ScreenController;
import java.util.ArrayList;
import model.Model;
import controller.controllerMap.IntentComponentMap;
import view.viewport.PausePopupViewport;

public class PausePopup extends Popup {

    public PausePopup(Model model) {
        super(model);
    }
    
    @Override
    public void createView() {
        viewPort = new PausePopupViewport();
    }
    
    @Override
    public void createController() {
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new ScreenController(model, icms);
    }

}
