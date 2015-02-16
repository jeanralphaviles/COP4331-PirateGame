/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.viewport;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import model.GameObject;
import model.map.GridLocation;
import model.map.Map;
import model.map.Maptile;
import utility.IntentComponentMap;
import utility.decal.BlankDecal;
import utility.decal.Decal;

/**
 *
 * @author comcc_000
 */
public class AreaViewport extends ViewPort {

    private int numTilesWide = 5;
    private int numTilesHigh = 5;

    /**
     * Creates new form AreaViewport
     */
    public AreaViewport() {
        initComponents();
        //setBackground(Color.RED);
    }

    @Override
    public void updateView(GameObject gameObject) {
        //setBackground(Color.RED);
        this.removeAll();

        GridLayout grid = new GridLayout(numTilesWide, numTilesHigh, 0, 0);
        setLayout(grid);

        Maptile avatarMaptile = gameObject.getAvatar().getMaptile();
        Map map = gameObject.getLevel().getMap();
        GridLocation gridLocation = map.getGridLocation(avatarMaptile);
        GridBagConstraints c = new GridBagConstraints();
        c.ipadx = 0;
        c.ipady = 0;
        int avatarX = gridLocation.getX();
        int avatarY = gridLocation.getY();

        Maptile maptile;
        Tile tile;
        numTilesWide = map.getWidth();
        numTilesHigh = map.getHeight();

        for (int x = 0; x < numTilesWide; x++) {
            for (int y = 0; y < numTilesHigh; y++) {
                maptile = map.getMapTile(y, x);
                if (maptile != null) {
                    tile = new Tile(maptile);
                } else {
                    tile = new Tile(); //BlankDecal
                }
                add(tile.getImage(), c);
            }
        }

        this.updateUI();
    }

    @Override
    public ArrayList<IntentComponentMap> generateIntentComponentMapping() {
        return new ArrayList<IntentComponentMap>(1);
    }

    /*Inner classes*/
    private class Tile {

        /*Properties*/
        private ImageIcon imageIcon;// = new ImageIcon("images/grass_tile.jpg"); 
        private boolean tag;

        /*Constructors*/
        public Tile(Maptile maptile) {
            Decal decal = getLastDecal(maptile);
            imageIcon = decalToImage(decal);
        }

        public Tile() {
            Decal decal = new BlankDecal();
            imageIcon = decalToImage(decal);
        }

        public Tile(Maptile maptile, boolean tag) {
            this(maptile);
            this.tag = tag;
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

        public ImageIcon decalToImage(Decal decal) {
            BufferedImage image = decal.getImage();
            Image i = (Image)image;
            i = getScaledImage(i, 30, 30);
            image = (BufferedImage)i;
            ImageIcon icon = new ImageIcon(image);
            return icon;
        }

        private Image getScaledImage(Image srcImg, int w, int h) {
            BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(srcImg, 0, 0, w, h, null);
            g2.dispose();
            return resizedImg;
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
            .addGap(0, 868, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
