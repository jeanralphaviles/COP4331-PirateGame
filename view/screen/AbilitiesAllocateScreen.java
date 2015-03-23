/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.screen;

import application.RunGame;
import controller.IntentMap.IntentMap;
import controller.physicalController.PhysicalController.PhysicalControllerMode;
import controller.virtualController.AbilitiesAllocateVirtualController;
import controller.virtualController.OptionsVirtualController;
import java.util.ArrayList;
import model.Model;
import view.viewport.AbilitiesAllocateViewport;
import view.viewport.OptionsViewport;

/**
 *
 * @author Doherty
 */
public class AbilitiesAllocateScreen extends Screen {
    
    public AbilitiesAllocateScreen(Model model) {
        super(model);
    }
    
    @Override
    protected void createView() {
        viewPort = new AbilitiesAllocateViewport();
    }

    @Override
    protected void createController() {
        //Virtual Controller
        ArrayList<IntentMap> virtualIMs = viewPort.generateIntentMapping();
        virtualController = new AbilitiesAllocateVirtualController(model, virtualIMs);
    }
    
}
