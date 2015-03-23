package view.viewport;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import model.GameObject;
import model.map.GridLocation;
import utility.decal.Decal;

import java.awt.ComponentOrientation;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author comcc_000
 */
public class AreaViewport extends ViewPort {

    private int numTilesWide = 31; //should be odd 19 @ 32,32 scale was good
    private int numTilesHigh = 27; //should be odd 7 @ 32,32 scale was good
    private Tile[][] viewTiles = new Tile[numTilesWide][numTilesHigh];
    public static boolean updateNeeded = true;
    
    public AreaViewport() {
    	setLayout(new GridLayout(numTilesHigh, numTilesWide));
    	setBorder(LineBorder.createGrayLineBorder());
    	this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	initComponents();

    	for (int y = 0; y < numTilesHigh; ++y) {
    		for (int x = 0; x < numTilesWide; ++x) {
    			Decal decal = Decal.createDecal(Decal.grass);
    			viewTiles[x][y] = new Tile(decal);
    			add(viewTiles[x][y].getJButton());
    		}
    	}
    }

	@Override
	public void updateView(GameObject gameObject) {
		if (updateNeeded == false) {
			return;
		}
		this.removeAll();
		GridLocation avatarLocation = gameObject.getAvatarLocation();
		for (int y = 0; y < numTilesHigh; ++y) {
			for (int x = 0; x < numTilesWide; ++x) {
				GridLocation mapLocation = translateViewLocationToMapLocation(new GridLocation(x, y), avatarLocation);
				Decal newDecal = getDecalFromGridLocation(gameObject, mapLocation);
				viewTiles[x][y].changeDecal(newDecal);
				add(viewTiles[x][y].getJButton());
			}
		}
		updateNeeded = false;
		this.updateUI();
	}
	
	public ImageIcon autoScale(Decal decal) {
		Image i = decal.getImage();
		BufferedImage resizedImg = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(i, 0, 0, 32, 32, null);
		g2.dispose();
		return new ImageIcon(resizedImg);
	}
	
	public GridLocation translateViewLocationToMapLocation(GridLocation viewLocation, GridLocation avatarLocation) {
		return new GridLocation(avatarLocation.getX() - numTilesWide/2 + viewLocation.getX(), avatarLocation.getY() - numTilesHigh/2 + viewLocation.getY());
	}
	
	public Decal getDecalFromGridLocation(GameObject gameObject, GridLocation gridLocation) {
		if (!gameObject.getLevel().isValidGridLocation(gridLocation)) {
			return Decal.createDecal(Decal.water);
		} else {
			return gameObject.getDecals(gridLocation).get(0);
		}
	}
	
	private class Tile {
		private Decal decal;
		private JButton button;
		private HashMap<String, ImageIcon> scaledImages = new HashMap<String, ImageIcon>();
		
		public Tile(Decal decal) {
			this.setDecal(decal);
			this.button = new JButton(decal.getImageIcon());
			this.button.setToolTipText("Sup!!");
			this.button.setBorderPainted(true);
			this.button.setContentAreaFilled(false);
			this.button.setBorder(LineBorder.createBlackLineBorder());
		}
		
		public JButton getJButton() {
			return button;
		}
		
		public void changeDecal(Decal decal) {
			this.setDecal(decal);
			if (!hasScaledImage(decal)) {
				addScaledImage(decal);
			}
			button.setIcon(getScaledImage(decal));
		}
		
		private boolean hasScaledImage(Decal decal) {
			return scaledImages.containsKey(decal.getFileName());
		}
		
		private ImageIcon getScaledImage(Decal decal) {
			return scaledImages.get(decal.getFileName());
		}
		
		private void addScaledImage(Decal decal) {
			ImageIcon scaled = autoScale(decal);
			scaledImages.put(decal.getFileName(), scaled);
		}

		public Decal getDecal() {
			return decal;
		}

		public void setDecal(Decal decal) {
			this.decal = decal;
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
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
