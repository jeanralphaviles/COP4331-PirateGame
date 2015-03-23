package view.screen;

import controller.virtualController.VirtualController;
import java.util.ArrayList;
import model.Model;
import controller.IntentMap.IntentMap;
<<<<<<< Updated upstream
import view.viewport.DialogueScreenViewport;
=======
>>>>>>> Stashed changes
import view.viewport.TradeViewport;

public class TradeScreen extends Screen {

    public TradeScreen(Model model) {
        super(model);
    }
        
    @Override
    public void createView() {
        viewPort = new TradeViewport();
    }
    
    @Override
    public void createController() {
        ArrayList<IntentMap> ims = viewPort.generateIntentMapping();
        virtualController = new VirtualController(model, ims);
    }

}
