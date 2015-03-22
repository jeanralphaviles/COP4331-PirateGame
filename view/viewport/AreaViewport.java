package view.viewport;

import java.awt.Graphics2D;
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
import utility.decal.Decal;

/**
 *
 * @author comcc_000
 */
public class AreaViewport extends ViewPort {

    private int numTilesWide = 31; //should be odd 19 @ 32,32 scale was good
    private int numTilesHigh = 27; //should be odd 7 @ 32,32 scale was good
    //
    private boolean followAvatar = true;
    //
    private GridLocation oldAvatarGridLocation = null;
    //
    private static boolean gameStepTaken = false;

    /**
     * Creates new form AreaViewport
     */
    public AreaViewport() {
        initComponents();
        //setBackground(Color.RED);
    }

    @Override
    public void updateView(GameObject gameObject) {
        //Get AvatarLocation
        GridLocation currentAvatarGridLocation = getCurrentAvatarGridLocation(gameObject);
        int avatarX = currentAvatarGridLocation.getX();
        int avatarY = currentAvatarGridLocation.getY();
        
        //Determine if Update is needed
        boolean avatarMoved = !currentAvatarGridLocation.equals(oldAvatarGridLocation);
        boolean firstRender = oldAvatarGridLocation == null;   
        boolean gameStep = checkGameStepTaken();
        boolean updateNeeded = avatarMoved || firstRender || gameStep; 
        oldAvatarGridLocation = currentAvatarGridLocation;
        
        //Update if necessary
        if (updateNeeded) {
            this.removeAll();

    //        GridBagConstraints c = new GridBagConstraints();
    //        c.ipadx = 0;
    //        c.ipady = 0;
            GridLayout grid = new GridLayout(numTilesHigh, numTilesWide, 0, 0);
            setLayout(grid);

            Map map = getMap(gameObject);
            Maptile maptile;
            Tile tile;
            if (!followAvatar) {
                numTilesWide = map.getWidth();
                numTilesHigh = map.getHeight();

                for (int x = 0; x < numTilesWide; x++) {
                    for (int y = 0; y < numTilesHigh; y++) {
                        displayTile(map, x, y, gameObject);
                    }
                }
            } else {
                int[] minAndMaxXYs = getMinAndMaxXYs(numTilesWide, numTilesHigh, currentAvatarGridLocation);
                int minX = minAndMaxXYs[0];
                int maxX = minAndMaxXYs[1];
                int minY = minAndMaxXYs[2];
                int maxY = minAndMaxXYs[3];
                for (int x = minY; x <= maxY; x++) {
                    for (int y = minX; y <= maxX; y++) {
                        displayTile(map, x, y, gameObject);
                    }
                }
            }
            System.out.println("View updated");
            this.updateUI();
        }
    }
    
    private Map getMap(GameObject gameObject) {
        Map map = gameObject.getLevel().getMap();
        return map;
    }
    
    private GridLocation getCurrentAvatarGridLocation(GameObject gameObject) {
    	return gameObject.getAvatarLocation();
    }

    private void displayTile(Map map, int x, int y, GameObject gameObject) {
        Maptile maptile = map.getMaptile(new GridLocation(y, x));
        Tile tile;
        if (maptile != null) {
            tile = new Tile(maptile);
            if (!gameObject.getDecals(new GridLocation(y, x)).isEmpty()) {
            	tile.setDecal(gameObject.getDecals(new GridLocation(y, x)).get(0));
            }
            tile.autoScale();
        } else {
            tile = new Tile(); //BlankDecal
        }
        add(tile.getImage());
    }

//    private GridLocation[] getCorners(int width, int height, GridLocation currentAvatarGridLocation) {
//        GridLocation[] corners = new GridLocation[4];
//        //
//        int[] minAndMaxXYs = getMinAndMaxXYs(width, height, currentAvatarGridLocation);
//        //
//        int minX = minAndMaxXYs[0];
//        int maxX = minAndMaxXYs[1];
//        int minY = minAndMaxXYs[2];
//        int maxY = minAndMaxXYs[3];
//        //
//        corners[0] = new GridLocation(minX, minY); //top left
//        corners[1] = new GridLocation(maxX, maxY); //top right
//        corners[2] = new GridLocation(minX, maxY); //bot left
//        corners[3] = new GridLocation(maxX, maxY); //bot right
//        return corners;
//    }

    private int[] getMinAndMaxXYs(int width, int height, GridLocation avatarGridLocation) {
        int[] minAndMaxXYs = new int[4];
        //
        int xBuffer = width / 2; //relies on int truncation
        int yBuffer = height / 2; //relies on int truncation
        int avatarX = avatarGridLocation.getX();
        int avatarY = avatarGridLocation.getY();
        //
        int minX = avatarX - xBuffer; // - 1;
        int maxX = avatarX + xBuffer; // - 1;
        int minY = avatarY - yBuffer; // + 1;
        int maxY = avatarY + yBuffer; //+ 1;
        //
        minAndMaxXYs[0] = minX;
        minAndMaxXYs[1] = maxX;
        minAndMaxXYs[2] = minY;
        minAndMaxXYs[3] = maxY;
        return minAndMaxXYs;
    }
    
    public static void registerGameStep() {
        gameStepTaken = true;
    }
    
    private boolean checkGameStepTaken() {
        if (gameStepTaken) {
            gameStepTaken = false;
            return true;
        } else {
            return false;
        }
    }

    /*Inner classes*/
    private class Tile {

        /*Properties*/
        private ImageIcon imageIcon;// = new ImageIcon("images/grass_tile.jpg"); 
        private Decal decal = new Decal( Decal.blank );
        private boolean tag;
        
        public Decal getDecal() {
            return decal;
        }

        public void setDecal(Decal decal) {
            this.decal = decal;
        }

        /*Constructors*/
        public Tile(Maptile maptile) {
            decal = getLastDecal(maptile);
            if (decal == null) {
                decal = new Decal(Decal.blank);
            }
            autoScale();
        }

        public Tile() {
            decal = new Decal(Decal.blank);
            autoScale();
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

//        public ImageIcon decalToImage(Decal decal) {
//            BufferedImage image = decal.getImage();
//            Image i = (Image) image;
//            i = getScaledImage(i, 30, 30);
//            image = (BufferedImage) i;
//            ImageIcon icon = new ImageIcon(image);
//            return icon;
//        }
        
        private void autoScale() {
            if (followAvatar) {
            	if (decal.getImage().getHeight() != 32 || decal.getImage().getWidth() != 32) {
            		scale(32, 32);
            	} else {
            		imageIcon = new ImageIcon(decal.getImage());
            	}
            } else {
                scale(30, 30);
            }
        }
        
        public void scale(int width, int height) {
            BufferedImage image = decal.getImage();
            Image i = image;
            i = getScaledImage(i, width, height);
            image = (BufferedImage) i;
            imageIcon = new ImageIcon(image);
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
            .addGap(0, 532, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
