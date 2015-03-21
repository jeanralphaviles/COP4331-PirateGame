package controller.physicalController;

import controller.Controller;
import controller.Intent;
import controller.control.Control;
import controller.IntentMap.IntentMap;
import controller.virtualController.VirtualController;
import java.util.ArrayList;
import model.Model;

public class PhysicalController extends Controller {

    /*Properties*/
    protected VirtualController virtualController;
    
    /*Constructors*/
    
    public PhysicalController(Model model) {
        super(model);
    }
    
    /*Methods*/
    
    public void adaptForScreen(ArrayList<IntentMap> ims, VirtualController screenController) {
        this.intentMaps = ims;
        this.virtualController = screenController;
    }
    
    public void reassignControlWithIntent(Control control, Object object, Intent intent) {
        //remove any old ims with that control
        removeICMForControl(control);
        
        //add icm
        IntentMap im = new IntentMap(control, null, object, intent, "");
        intentMaps.add(im);
    }
    
    private void removeICMForControl(Control control) {
        IntentMap im;
        for (int i=0; i<intentMaps.size(); i++) {
            im = intentMaps.get(i);
            if (im.hasControl(control)) {
                intentMaps.remove(im);
            }
        }
    }
    
    /*Get-Sets*/
    
    
    
}
