package view.screen.popup;

import controller.virtualController.VirtualController;
import java.util.ArrayList;
import model.Model;
import controller.IntentMap.IntentMap;
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
        ArrayList<IntentMap> ims = viewPort.generateIntentMapping();
        virtualController = new VirtualController(model, ims);
    }

}
