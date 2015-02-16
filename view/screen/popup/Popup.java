package view.screen.popup;

import view.screen.Screen;
import model.Model;

public abstract class Popup extends Screen {

    private Screen parentScreen;

    public Popup(Model model) {
        super(model);
    }

    @Override
	public abstract void init();

    public Screen getParentScreen() {
        return parentScreen;
    }

    public void setParentScreen(Screen parentScreen) {
        this.parentScreen = parentScreen;
    }

}
