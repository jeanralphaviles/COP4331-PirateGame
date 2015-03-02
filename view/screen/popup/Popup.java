package view.screen.popup;

import view.screen.Screen;
import model.Model;

public abstract class Popup extends Screen {

    private Screen parentScreen;

    public Popup(Model model) {
        super(model);
    }

    public Screen getParentScreen() {
        return parentScreen;
    }

    public void setParentScreen(Screen parentScreen) {
        this.parentScreen = parentScreen;
    }

}
