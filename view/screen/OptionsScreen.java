/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.screen;

import application.RunGame;
import controller.IntentMap.IntentMap;
import controller.virtualController.OptionsVirtualController;
import java.util.ArrayList;
import model.Model;
import view.viewport.OptionsViewport;

/**
 *
 * @author Doherty
 */
public class OptionsScreen extends Screen {
    
    public OptionsScreen(Model model) {
        super(model);
    }
    
    @Override
    protected void createView() {
        viewPort = new OptionsViewport();
        viewPort.updateView(model.getGameObject());
    }

    @Override
    protected void createController() {
        //Virtual Controller
        ArrayList<IntentMap> virtualIMs = viewPort.generateIntentMapping();
        virtualController = new OptionsVirtualController(model, virtualIMs);
        
        //Physical Controller
        physicalController = RunGame.getAuxController();
        physicalController.adaptForScreen(new ArrayList<IntentMap>(1), virtualController);
    }
    
}
