package view.screen;

import controller.IntentMap.IntentMap;
import controller.physicalController.PhysicalController;
import controller.virtualController.VirtualController;
import java.util.ArrayList;
import model.GameObject;
import model.Model;
import view.viewport.ViewPort;

public abstract class Screen {

    protected VirtualController virtualController;
    protected PhysicalController physicalController;
    //
    protected ViewPort viewPort;
    protected Model model;

    public Screen(Model model) {
        this.model = model;
        init();
    }

    private void init() {
        createView();
        createController();
    }
    
    protected abstract void createView();
    protected abstract void createController();
    
    protected ArrayList<IntentMap> generateIntentMapping() {
        return null;
    }

    public void updateView(GameObject gameObject) {
        viewPort.updateView(gameObject);
    }
    
    public void refreshController() {
        ArrayList<ViewPort> viewports = viewPort.getInteriorViewports();
        int size = viewports.size();
        ViewPort v;
        for (int i=0; i<size; i++) {
            v = viewports.get(i);
            if (v.isRefreshControllerNeeded()) {
                createController();
                return;
            }
        }
    }
    
    /*Get-Sets*/

    public ViewPort getViewPort() {
        return viewPort;
    }

    public void setViewPort(ViewPort viewPort) {
        this.viewPort = viewPort;
    }

}
