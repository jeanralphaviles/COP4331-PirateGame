package view.screen;

import controller.virtualController.VirtualController;
import java.util.ArrayList;
import model.Model;
import controller.IntentMap.IntentMap;
import view.viewport.MainScreenViewport;

public class MainScreen extends Screen {

    public MainScreen(Model model) {
        super(model);
    }
        
    @Override
    public void createView() {
        viewPort = new MainScreenViewport();
    }
    
    @Override
    public void createController() {
        ArrayList<IntentMap> ims = viewPort.generateIntentMapping();
        virtualController = new VirtualController(model, ims);
    }

}
