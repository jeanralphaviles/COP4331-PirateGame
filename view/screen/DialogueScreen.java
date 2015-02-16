package view.screen;

import controller.ScreenController;
import java.util.ArrayList;
import model.Model;
import utility.IntentComponentMap;
import view.viewport.DialogueScreenViewport;
import view.viewport.FlavorImageViewport;

public class DialogueScreen extends Screen {

    public DialogueScreen(Model model) {
        super(model);
    }

    public void init() {
        viewPort = new DialogueScreenViewport();
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new ScreenController(model, icms);
    }

}
