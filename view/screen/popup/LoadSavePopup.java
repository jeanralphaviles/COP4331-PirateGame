package view.screen.popup;

import controller.screenController.LoadSaveController;
import controller.screenController.NewGamePopupController;
import controller.screenController.ScreenController;
import java.util.ArrayList;
import model.Model;
import controller.controllerMap.IntentComponentMap;
import view.viewport.LoadSavePopUpViewport;

public class LoadSavePopup extends Popup {

    public LoadSavePopup(Model model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void createView() {
        viewPort = new LoadSavePopUpViewport();
    }

    @Override
    protected void createController() {
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new LoadSaveController(model, icms);
    }
}

/*
public class NewGamePopup extends Popup {

    public NewGamePopup(Model model) {
        super(model);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void createView() {
        viewPort = new NewGamePopupViewport();
    }
    
    @Override
    public void createController() {
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new NewGamePopupController(model, icms);
    }

}
*/
