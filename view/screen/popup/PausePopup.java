package view.screen.popup;

import controller.ScreenController;
import java.util.ArrayList;
import model.Model;
import utility.IntentComponentMap;
import view.viewport.PausePopupViewport;

public class PausePopup extends Popup {

    public PausePopup(Model model) {
        super(model);
    }

    @Override
    public void init() {
        viewPort = new PausePopupViewport();
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new ScreenController(model, icms);
    }

}