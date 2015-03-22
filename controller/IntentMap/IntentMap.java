package controller.IntentMap;

import controller.Intent;
import controller.control.Control;
import java.awt.Component;

/**
 * @author Team Tiger
 * 
 *maps view button with it's action-listener (control)
 *Allows view and controller to be decoupled
 */

public class IntentMap {

    /*Properties*/
    
    private Control control;
    private Component component;
    private Object object;
    private Intent intent;
    private String details;

    /*Constructors*/
    
    public IntentMap(Component component, Intent intent) {
        this.setComponent(component);
        this.setIntent(intent);
    }
    
    public IntentMap(Intent intent, Object object, String details) {
        this.intent = intent;
        this.object = object;
        this.details = details;
    }

    public IntentMap(Component component, Intent intent, String details) {
        this.component = component;
        this.intent = intent;
        this.details = details;
    }
    
    public IntentMap(Component component, Object object, Intent intent) {
        this.component = component;
        this.object = object;
        this.intent = intent;
    }
    

    public IntentMap(Control control, Component component, Object object, Intent intent, String details) {
        this.control = control;
        this.component = component;
        this.object = object;
        this.intent = intent;
        this.details = details;
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
    
    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
    
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    /*Inner classes*/

    
    
}
