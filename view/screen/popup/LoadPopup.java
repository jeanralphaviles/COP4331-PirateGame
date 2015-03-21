/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.screen.popup;

import controller.IntentMap.IntentMap;
import controller.virtualController.LoadSaveVirtualController;
import java.util.ArrayList;
import model.Model;
import view.viewport.LoadPopUpViewport;

/**
 *
 * @author darien
 */
public class LoadPopup extends Popup {

    public LoadPopup(Model model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void createView() {
        viewPort = new LoadPopUpViewport();
    }

    @Override
    protected void createController() {
        ArrayList<IntentMap> ims = viewPort.generateIntentMapping();
        virtualController = new LoadSaveVirtualController(model, ims);
    }
}