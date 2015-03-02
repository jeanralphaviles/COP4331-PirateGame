package view.screen.popup;

import controller.NewGamePopupController;
import java.util.ArrayList;
import model.Model;
import utility.IntentComponentMap;
import view.viewport.NewGamePopupViewport;

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
