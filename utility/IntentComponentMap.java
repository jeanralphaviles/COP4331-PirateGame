package utility;

import javax.swing.JComponent;
import controller.Intent;

/**
 * @author Team Tiger
 * 
 *maps view button with it's action-listener (control)
 *Allows view and controller to be decoupled
 */

public class IntentComponentMap {

    private JComponent component;
    private Object object;
    private Intent intent;
    private String details;

    public IntentComponentMap(JComponent jComponent, Intent intent) {
        this.setComponent(jComponent);
        this.setIntent(intent);
    }
    
    public IntentComponentMap(Intent intent, Object object, String details) {
        this.intent = intent;
        this.object = object;
        this.details = details;
    }

    public IntentComponentMap(JComponent component, Intent intent, String details) {
        this.component = component;
        this.intent = intent;
        this.details = details;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
    
    
    

    /*Inner classes*/
    public enum Intent {
        LOAD, SAVE, EXIT, NEW, USER_INPUT, BEGIN, GOTO_GAME, GOTO_PAUSE, GOTO_MAIN
    }
}
