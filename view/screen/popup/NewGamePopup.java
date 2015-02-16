package view.screen.popup;

import controller.NewGamePopupController;
import controller.ScreenController;
import java.util.ArrayList;
import model.Model;
import utility.IntentComponentMap;
import view.viewport.MainScreenViewport;
import view.viewport.NewGamePopupViewport;

public class NewGamePopup extends Popup {

    public NewGamePopup(Model model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() {
        viewPort = new NewGamePopupViewport();
        ArrayList<IntentComponentMap> icms = viewPort.generateIntentComponentMapping();
        controller = new NewGamePopupController(model, icms);
    }

}
