package controller.virtualController;

import application.RunGame;
import model.Model;
import controller.IntentMap.IntentMap;
import controller.Intent;
import controller.physicalController.KeyboardController;
import controller.physicalController.PhysicalController;
import controller.physicalController.RebindInfo;
import java.util.ArrayList;
import javax.swing.JButton;

public final class OptionsVirtualController extends VirtualController {
    
    /*Properties*/
    
    private RebindInfo rebindInfo;
     
    /*Constructors*/
    
    public OptionsVirtualController(Model model, ArrayList<IntentMap> ims) {
        super(model, ims);
    }
    
    /*Methods*/
    
    @Override
    protected void action(IntentMap im) {
        
        Intent intent = im.getIntent();
        switch(intent) {
            case LISTEN:
                KeyboardController controller = (KeyboardController)RunGame.getAuxController();
                
                //Make the button blank
                JButton button = (JButton)im.getComponent();
                button.setText("");
                //Pass the controller controller rebinding info
                rebindInfo = (RebindInfo)im.getObject(); //aux will retrieve it
                //Tell the keyboard controller to capture key to rebind to next key pressed
                controller.activateRebindMode(); //sets to rebind mode
                //
                break;
            default:
                break;
            
        }
        super.action(im);
    }

    /*Get-Sets*/
    
    public RebindInfo getRebindInfo() {
        return rebindInfo;
    }

    public void setRebindInfo(RebindInfo rebindInfo) {
        this.rebindInfo = rebindInfo;
    }
    
}
