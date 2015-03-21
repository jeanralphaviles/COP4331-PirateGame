/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.control;

/**
 *
 * @author comcc_000
 */
public class KeyboardControl extends Control {
        
    /*Properties*/
    
    private int keyCode;
    //
    
    /*Constructors*/
    
    public KeyboardControl(int keyCode) {
        super();
        this.keyCode = keyCode;
    }
    
    /*Methods*/
    
    public boolean representsKey(int keyCode) {
        return this.keyCode == keyCode;
    }
    
    public boolean equals(KeyboardControl control) {
        return representsKey(control.getKeyCode());
    }
    
    /*Get-Sets*/
    
    public int getKeyCode() {
        return keyCode;
    }
    
}
