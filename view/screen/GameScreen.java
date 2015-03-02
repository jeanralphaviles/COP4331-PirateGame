package view.screen;

import controller.GameScreenController;
import controller.ScreenController;
import java.util.ArrayList;
import model.GameObject;
import model.Model;
import utility.IntentComponentMap;
import view.viewport.AreaViewport;
import view.viewport.GameScreenViewport;
import view.viewport.InventoryViewportConnor;
import view.viewport.ViewPort;

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
