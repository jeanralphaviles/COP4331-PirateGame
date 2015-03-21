package controller.virtualController;

import controller.Controller;
import java.awt.Color;
import java.awt.Component;
import model.Model;
import controller.IntentMap.IntentMap;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VirtualController extends Controller {

    /*Properties*/
    protected ArrayList<IntentMap> ims;
    protected Color SELECTED_COLOR = Color.BLUE;    

    /*Constructors*/
    public VirtualController(Model model, ArrayList<IntentMap> ims) {
        super(model);
        this.ims = ims;
        setListenersForIMs();
    }

    /*Methods*/
    
    //for use by the physical controllers
    public void executeAction(IntentMap im) {
        action(im);
    }
    
    private final void setListenersForIMs() {
        int numims = ims.size();
        IntentMap im;
        for (int i=0; i<numims ; i++) {
            im = ims.get(i);
            setListenerForIM(im);
        }
    }

    private void setListenerForIM(IntentMap im) {
        JComponent component = (JComponent)im.getComponent();
        final IntentMap im2 = im;
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(im2);
            }
        };
        setActionListener(al, component);
    }
    
    private void setActionListener(ActionListener al, Component component) {
        if (component instanceof AbstractButton) {
            ((AbstractButton)component).addActionListener(al);
        } else if (component instanceof JTextField) {
            ((JTextField)component).addActionListener(al);
        }
    }

}
