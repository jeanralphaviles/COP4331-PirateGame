package view.screen;

import controller.virtualController.VirtualController;
import java.util.ArrayList;
import model.Model;
import controller.IntentMap.IntentMap;
import view.viewport.DialogueScreenViewport;
import view.viewport.TradeScreenViewport;

public class TradeScreen extends Screen {

    public TradeScreen(Model model) {
        super(model);
    }
        
    @Override
    public void createView() {
        viewPort = new TradeScreenViewport();
    }
    
    @Override
    public void createController() {
        ArrayList<IntentMap> ims = viewPort.generateIntentMapping();
        virtualController = new VirtualController(model, ims);
    }

}
