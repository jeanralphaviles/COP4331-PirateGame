/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.physicalController;

import controller.Intent;
import java.awt.Component;

/**
 *
 * @author Doherty
 */
public class RebindInfo {
    
    /*Properties*/
    
    public Intent intent;
    public Object object;
    public Component component;
    
    /*Constructor*/

    public RebindInfo(Intent intent, Object object, Component component) {
        this.intent = intent;
        this.object = object;
        this.component = component;
    }
    
    
}
