package controller;

import java.awt.Component;
import model.Model;
import utility.IntentComponentMap;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScreenController extends Controller {

    /*Properties*/
    protected ArrayList<IntentComponentMap> icms;

    /*Constructors*/
    public ScreenController(Model model, ArrayList<IntentComponentMap> icms) {
        super(model);
        this.icms = icms;
        setListenersForICMs();
    }

    /*Methods*/
    private final void setListenersForICMs() {
        int numICMs = icms.size();
        IntentComponentMap icm;
        for (int i=0; i<numICMs ; i++) {
            icm = icms.get(i);
            setListenerForICM(icm);
        }
    }

    private void setListenerForICM(IntentComponentMap icm) {
        JComponent component = (JComponent)icm.getComponent();
        final IntentComponentMap icm2 = icm;
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(icm2);
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
