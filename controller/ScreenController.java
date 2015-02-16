package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import model.Model;
import utility.IntentComponentMap;
import utility.IntentComponentMap.Intent;

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
        JComponent component = icm.getComponent();
        Intent intent = icm.getIntent();
        ActionListener AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action(intent);
            }
        };
        if (intent != Intent.USER_INPUT && component instanceof AbstractButton) {
            ((AbstractButton)component).addActionListener(AL);
        }
    }
    
    @Override
    protected void processUserInput(Model model) {
        //empty default implementation
        System.out.println();
    }

}
