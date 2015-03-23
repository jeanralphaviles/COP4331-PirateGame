package view.screen.popup;

import controller.virtualController.LoadSaveVirtualController;
import java.util.ArrayList;
import model.Model;
import controller.IntentMap.IntentMap;
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
        ArrayList<IntentMap> ims = viewPort.generateIntentMapping();
        virtualController = new LoadSaveVirtualController(model, ims);
        
        
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
        ArrayList<IntentComponentMap> ims = viewPort.generateIntentMapping();
        virtualController = new NewGameVirtualController(model, ims);
    }

}
*/
