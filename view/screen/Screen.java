package view.screen;

import controller.Controller;
import model.GameObject;
import model.Model;
import utility.IntentComponentMap;
import view.viewport.ViewPort;

import java.util.ArrayList;

public abstract class Screen {

    protected Controller controller;
    protected ViewPort viewPort;
    protected Model model;

    public Screen(Model model) {
        this.model = model;
        init();
    }

    public abstract void init();

    public final void updateView(GameObject gameObject) {
        viewPort.updateView(gameObject);
    }
    
    /*Get-Sets*/

    public ViewPort getViewPort() {
        return viewPort;
    }

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }
    
    

}
