package controller.auxiliaryController;

import controller.Controller;
import controller.Intent;
import controller.control.Control;
import controller.controllerMap.IntentComponentMap;
import controller.controllerMap.IntentControlMap;
import java.util.ArrayList;
import model.Model;

public class AuxiliaryController extends Controller {

    /*Properties*/
    
    protected ArrayList<IntentControlMap> icms = new ArrayList<IntentControlMap>(1);
    
    /*Constructors*/
    
    public AuxiliaryController(Model model, ArrayList<IntentControlMap> icms) {
        super(model);
        this.icms = icms;
    }
    
    /*Methods*/
    
    public void reassignControlWithIntent(Control control, Object object, Intent intent) {
        //remove any old icms with that control
        removeICMForControl(control);
        
        //add icm
        IntentControlMap icm = new IntentControlMap(control, object, intent);
        icms.add(icm);
    }
    
    private void removeICMForControl(Control control) {
        IntentControlMap icm;
        for (int i=0; i<icms.size(); i++) {
            icm = icms.get(i);
            if (icm.hasControl(control)) {
                icms.remove(icm);
            }
        }
    }
    
    /*Get-Sets*/
    
    
    
}
