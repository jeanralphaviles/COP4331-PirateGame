package view.viewport;

import java.util.ArrayList;
import javax.swing.JPanel;
import model.GameObject;

import controller.IntentMap.IntentMap;

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
    
    public ArrayList<IntentMap> generateIntentMapping() {
        ArrayList<IntentMap> ims = new ArrayList<IntentMap>(1);
        ArrayList<IntentMap> temp_ims = new ArrayList<IntentMap>(1);
        int numInteriorViewports = interiorViewports.size();
        ViewPort viewport;
        for (int i=0; i<numInteriorViewports; i++) {
            viewport = interiorViewports.get(i);
            temp_ims = viewport.generateIntentMapping();
            aggregateims(ims, temp_ims);
        }
        return ims;
    }
    
    private void aggregateims(ArrayList<IntentMap> ims1, ArrayList<IntentMap> ims2) {
        int numims2 = ims2.size();
        for (int i=0; i<numims2; i++) {
            ims1.add(ims2.get(i));
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
