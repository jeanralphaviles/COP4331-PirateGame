package view.screen;

import controller.GameScreenController;
import controller.ScreenController;
import java.util.ArrayList;
import model.Model;
import utility.IntentComponentMap;
import view.viewport.AreaViewport;
import view.viewport.GameScreenViewport;

public class GameScreen extends Screen {

    public GameScreen(Model model) {
        super(model);
    }

    @Override
    public void init() {
        viewPort = new GameScreenViewport();
        //viewPort = new AreaViewport();
        viewPort.updateView(model.getGameObject());
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new GameScreenController(model, icms);
    }
    
    

}
