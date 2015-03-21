package view.screen.popup;

import controller.virtualController.NewGameVirtualController;
import java.util.ArrayList;
import model.Model;
import controller.IntentMap.IntentMap;
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
        ArrayList<IntentMap> ims = viewPort.generateIntentMapping();
        virtualController = new NewGameVirtualController(model, ims);
    }

}
