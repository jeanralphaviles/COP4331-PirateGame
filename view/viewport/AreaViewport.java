/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.GameObject;
import model.map.GridLocation;
import model.map.Map;
import model.map.Maptile;
import utility.IntentComponentMap;
import utility.decal.Decal;

/**
 *
 * @author comcc_000
 */
public class AreaViewport extends ViewPort {

    private int numTilesWide = 5;
    private int numTilesHight = 5;
    
    /**
     * Creates new form AreaViewport
     */
    public AreaViewport() {
        initComponents();
        //setBackground(Color.RED);
    }
    
    @Override
    public void updateView(GameObject gameObject) {
        GridLayout grid = new GridLayout(numTilesWide, numTilesHight);
        setLayout(grid);
        
        Maptile avatarMaptile = gameObject.getAvatar().getMaptile();
        Map map = gameObject.getLevel().getMap();
        GridLocation gridLocation = map.getGridLocation(avatarMaptile);
        int avatarX = gridLocation.getX();
        int avatarY = gridLocation.getY();
        
        Maptile maptile;
        Tile tile;
        for (int x=0; x < ( (avatarX-numTilesWide) /2); x++) {
            for (int y=0; y < ( (avatarY-numTilesWide) /2); y++) {
                maptile = map.getMapTile(x, y);
                tile = new Tile(maptile);
                add(tile.getImage());
            }
        }
    }

    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        return new ArrayList<IntentComponentMap>(1);
    }
    
    /*Inner classes*/
    
    private class Tile {
        
        /*Properties*/
        private ImageIcon imageIcon;// = new ImageIcon("images/grass_tile.jpg"); 
        
        /*Constructors*/
        public Tile(Maptile maptile) {
            Decal decal = getLastDecal(maptile);
            imageIcon = new ImageIcon(decal.getImage());
        }
        
        /*Methods*/
        
        public Decal getLastDecal(Maptile maptile) {
            ArrayList<Decal> decals = maptile.getDecals();
            return decals.get(0);
        }
        
        public JLabel getImage() {
            JLabel label = new JLabel(imageIcon);
            return label;
        }
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    
}
