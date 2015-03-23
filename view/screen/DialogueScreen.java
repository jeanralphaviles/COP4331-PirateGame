package view.screen;

import controller.virtualController.VirtualController;
import java.util.ArrayList;
import model.Model;
import controller.IntentMap.IntentMap;
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
        ArrayList<IntentMap> ims = viewPort.generateIntentMapping();
        virtualController = new VirtualController(model, ims);
    }

}
