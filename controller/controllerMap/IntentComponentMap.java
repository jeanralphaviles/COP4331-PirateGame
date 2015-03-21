package controller.controllerMap;

import controller.Intent;
import java.awt.Component;

/**
 * @author Team Tiger
 * 
 *maps view button with it's action-listener (control)
 *Allows view and controller to be decoupled
 */

public class IntentComponentMap extends ControllerMap {

    /*Properties*/
    
    private Component component;

    /*Constructors*/
    
    public IntentComponentMap(Component component, Intent intent) {
        this.setComponent(component);
        this.setIntent(intent);
    }
    
    public IntentComponentMap(Intent intent, Object object, String details) {
        this.intent = intent;
        this.object = object;
        this.details = details;
    }

    public IntentComponentMap(Component component, Intent intent, String details) {
        this.component = component;
        this.intent = intent;
        this.details = details;
    }
    
    public IntentComponentMap(Component component, Object object, Intent intent) {
        this.component = component;
        this.object = object;
        this.intent = intent;
    }
    
    /*Methods*/
    
    
    /*Get-Sets*/

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    /*Inner classes*/
    
}
