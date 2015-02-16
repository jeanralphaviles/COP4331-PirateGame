package view.viewport;

import java.util.ArrayList;
import javax.swing.JPanel;
import model.GameObject;

import utility.IntentComponentMap;

public abstract class ViewPort extends JPanel {
    
    protected ArrayList<ViewPort> interiorViewports = new ArrayList<ViewPort>(1);

    public ViewPort() {
        // TODO Auto-generated constructor stub
    }
    
    public void addViewport(ViewPort view) {
        //setSize(view);
        this.add(view);

//        setVisible(true);
//        view.updateUI();
//        view.revalidate();
//        view.repaint();
//        this.updateUI();
    }
    
    private void setSize(ViewPort view) {
        JPanel panel = (JPanel)view;
        int width = this.getWidth();
        int height = this.getHeight();
        panel.setBounds(0, 0, width, height); //x,y top right coordinate, then width, height
    }
    

    public abstract void updateView(GameObject gameObject);
    public abstract ArrayList<IntentComponentMap> generateIntentComponentMapping();
    
    /*Get-Sets*/

    public ArrayList<ViewPort> getInteriorViewports() {
        return interiorViewports;
    }

    public void setInteriorViewports(ArrayList<ViewPort> interiorViewports) {
        this.interiorViewports = interiorViewports;
    }
    
    

}
