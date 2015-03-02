package controller;

import java.awt.Component;
import model.Model;
import utility.IntentComponentMap;
import utility.IntentComponentMap.Intent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class ScreenController extends Controller {

    protected ArrayList<IntentComponentMap> icms;

    public ScreenController(Model model, ArrayList<IntentComponentMap> icms) {
        super(model);
        this.icms = icms;
        setListenersForICMs();
    }

    public void setListenersForICMs() {
        int numICMs = icms.size();
        IntentComponentMap icm;
        for (int i=0; i<numICMs ; i++) {
            icm = icms.get(i);
            setListenerForICM(icm);
        }
    }

    protected void setListenerForICM(IntentComponentMap icm) {
        JComponent component = (JComponent)icm.getComponent();
        //final Intent intent = icm.getIntent();
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(icm);
            }
        };
        setActionListener(al, component);
//        if (intent != Intent.USER_INPUT && component instanceof AbstractButton) {
//            ((AbstractButton)component).addActionListener(AL);
//        }
    }
    
    private void setActionListener(ActionListener al, Component component) {
        if (component instanceof AbstractButton) {
            ((AbstractButton)component).addActionListener(al);
        } else if (component instanceof JTextField) {
            ((JTextField)component).addActionListener(al);
        }
    }
    
    @Override
    protected void processUserInput(Model model) {
        //empty default implementation
        System.out.println();
    }

}
