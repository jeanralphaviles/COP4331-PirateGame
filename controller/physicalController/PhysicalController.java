package controller.physicalController;

import application.RunGame;
import controller.Controller;
import controller.Intent;
import controller.control.Control;
import controller.IntentMap.IntentMap;
import controller.virtualController.VirtualController;

import java.util.ArrayList;

import model.Model;
import view.screen.GameScreen;
import view.screen.Screen;

public abstract class PhysicalController extends Controller {

    /*Properties*/
    protected VirtualController virtualController;
    //protected boolean enabled = true;
    //
    protected PhysicalControllerMode mode = new DisabledMode();
    
    /*Constructors*/
    
    public PhysicalController(Model model) {
        super(model);
    }
    
    /*Methods*/
    
    public abstract void rebind(Control control);
    public abstract void performAction(Control control);
    
    public void adaptForScreen(ArrayList<IntentMap> ims, VirtualController screenController) {
        if (ims == null) {
            setMode(PhysicalControllerMode.DISABLED); //enabled = false; //Disable          
        } else {
            setMode(PhysicalControllerMode.ENABLED); //enabled = true;
            this.intentMaps = ims;
        }
        this.virtualController = screenController;
    }
    
    public void reassignControlWithIntent(Control control, Object object, Intent intent) {
        //get screen of interest
        ArrayList<IntentMap> ims = GameScreen.getPhysicalControlIMs();
        
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
    
    public void setMode(String mode) {
        switch (mode) {
            case PhysicalControllerMode.ENABLED:
                this.mode = new EnabledMode();
                break;
            case PhysicalControllerMode.DISABLED:
                this.mode = new DisabledMode();
                break;
            case PhysicalControllerMode.REBIND:
                this.mode = new RebindMode();
                break;
            default:
                RunGame.showErrorMessage("Invalid mode: " + mode);
                break;
        }
    }
    
    /*Get-Sets*/
    
    /*Inner Classes*//////////////////////////////////////////////////////////////////////////////
    public abstract class PhysicalControllerMode {
        
        /*Properties*/
        
        public static final String ENABLED = "ENABLED";
        public static final String DISABLED = "DISABLED";
        public static final String REBIND = "REBIND";
        
        /*Constructors*/
        
        /*Methods*/

        //Perform tasks on different timings
        public abstract void actionPerformed(Control control);

    }

    private class EnabledMode extends PhysicalControllerMode {

        public EnabledMode() {
            //
        }
        
        @Override
        public void actionPerformed(Control control) {
            performAction(control);
        }

    }

    private class DisabledMode extends PhysicalControllerMode {

        public DisabledMode() {
            //
        }
        
        @Override
        public void actionPerformed(Control control) {
            //do nothing
        }

    }
    
    private class RebindMode extends PhysicalControllerMode {
        
        public RebindMode() {
            //
        }
        
        @Override
        public void actionPerformed(Control control) {
            rebind(control);
        }
        
    }
    
    /*END Inner Classes*//////////////////////////////////////////////////////////////////////////////
    
    
}
