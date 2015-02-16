package view.screen;

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

    public void init() {
        //new AreaViewport();
        viewPort = new GameScreenViewport();
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new ScreenController(model, icms);
    }

}
