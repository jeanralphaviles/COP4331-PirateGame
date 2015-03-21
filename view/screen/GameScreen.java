package view.screen;

import controller.screenController.GameScreenController;
import java.util.ArrayList;
import model.Model;
import controller.controllerMap.IntentComponentMap;
import view.viewport.GameScreenViewport;

public class GameScreen extends Screen {

    public GameScreen(Model model) {
        super(model);
    }
    
    @Override
    public void createView() {
        viewPort = new GameScreenViewport();
        //viewPort = new AreaViewport();
        viewPort.updateView(model.getGameObject());
    }
    
    @Override
    public void createController() {
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new GameScreenController(model, icms);
        
    }
    
}
