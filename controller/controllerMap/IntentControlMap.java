/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.controllerMap;

import controller.Intent;
import controller.control.Control;

/**
 *
 * @author comcc_000
 */
public class IntentControlMap extends ControllerMap {
        
    /*Properties*/
    
    private Control control;
    
    /*Constructors*/
    
    public IntentControlMap(Control control, Object object, Intent intent) {
        this.control = control;
        this.object = object;
        this.intent = intent;
    }
    
    /*Methods*/
    
    public boolean hasControl(Control control) {
        return this.control.equals(control);
    }
    
    /*Get-Sets*/

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }
    
    
}
