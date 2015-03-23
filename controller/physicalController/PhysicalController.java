package controller.physicalController;

import controller.Controller;
import controller.Intent;
import controller.control.Control;
import controller.IntentMap.IntentMap;
import controller.virtualController.VirtualController;

import java.util.ArrayList;

import model.Model;
import view.screen.GameScreen;
import view.screen.Screen;

public class PhysicalController extends Controller {

    /*Properties*/
    protected VirtualController virtualController;
    protected boolean enabled = true;
    
    /*Constructors*/
    
    public PhysicalController(Model model) {
        super(model);
    }
    
    /*Methods*/
    
    public void adaptForScreen(ArrayList<IntentMap> ims, VirtualController screenController) {
        if (ims == null) {
            enabled = false; //Disable          
        } else {
            enabled = true;
            this.intentMaps = ims;
        }
        this.virtualController = screenController;
    }
    
    public void reassignControlWithIntent(Control control, Object object, Intent intent) {
        //get screen of interest
        ArrayList<IntentMap> ims = Screen.getPhysicalControlIMs();
        
        //remove any old ims with that control
        removeICMForControl(ims, control);
        
        //add icm
        IntentMap im = new IntentMap(control, null, object, intent, "");
        ims.add(im);
    }
    
    private void removeICMForControl(ArrayList<IntentMap> ims, Control control) {
        if (ims == null) {
            return;
        }
        IntentMap im;
        for (int i=0; i<ims.size(); i++) {
            im = ims.get(i);
            if (im.hasControl(control)) {
                ims.remove(im);
            }
        }
    }
    
    /*Get-Sets*/
    
    
    
}
