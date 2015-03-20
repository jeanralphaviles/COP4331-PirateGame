package view.viewport;

import java.util.ArrayList;
import javax.swing.JPanel;
import model.GameObject;

import utility.IntentComponentMap;

public abstract class ViewPort extends JPanel {
    
    protected ArrayList<ViewPort> interiorViewports = new ArrayList<>(1);
    ErrorMessageFrame errorMessageFrame;
    //
    protected boolean refreshControllerNeeded = false;

    public ViewPort() {
        // TODO Auto-generated constructor stub
    }
    
//    private void setSize(ViewPort view) {
//        JPanel panel = view;
//        int width = this.getWidth();
//        int height = this.getHeight();
//        panel.setBounds(0, 0, width, height); //x,y top right coordinate, then width, height
//    }
    
    protected void addViewport(ViewPort viewport, String layout) {
        setProperties();
        interiorViewports.add(viewport);
        add(viewport, layout);
    }
    
    protected void addViewport(ViewPort viewport) {
        setProperties();
        interiorViewports.add(viewport);
        add(viewport);
    }
    
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        ArrayList<IntentComponentMap> icms = new ArrayList<IntentComponentMap>(1);
        ArrayList<IntentComponentMap> temp_icms = new ArrayList<IntentComponentMap>(1);
        int numInteriorViewports = interiorViewports.size();
        ViewPort viewport;
        for (int i=0; i<numInteriorViewports; i++) {
            viewport = interiorViewports.get(i);
            temp_icms = viewport.generateIntentComponentMapping();
            aggregateICMs(icms, temp_icms);
        }
        return icms;
    }
    
    private void aggregateICMs(ArrayList<IntentComponentMap> icms1, ArrayList<IntentComponentMap> icms2) {
        int numICMs2 = icms2.size();
        for (int i=0; i<numICMs2; i++) {
            icms1.add(icms2.get(i));
        }
    }
    

    public abstract void updateView(GameObject gameObject);
    
    /*Get-Sets*/

    public ArrayList<ViewPort> getInteriorViewports() {
        return interiorViewports;
    }

    public void setInteriorViewports(ArrayList<ViewPort> interiorViewports) {
        this.interiorViewports = interiorViewports;
    }
    
    public boolean isRefreshControllerNeeded() {
        return refreshControllerNeeded;
    }
    
    protected void setProperties(){
        
        this.setSize( 1500, 1500);
    }
    
    

}
