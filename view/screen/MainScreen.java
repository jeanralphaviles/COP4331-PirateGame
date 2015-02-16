package view.screen;

import controller.ScreenController;
import java.util.ArrayList;
import model.Model;
import utility.IntentComponentMap;
import view.viewport.MainScreenViewport;

public class MainScreen extends Screen {

    public MainScreen(Model model) {
        super(model);
    }

    @Override
	public void init() {
        viewPort = new MainScreenViewport();
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new ScreenController(model, icms);
    }

}
