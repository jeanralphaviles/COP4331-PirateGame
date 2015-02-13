package utility.decal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Jean-Ralph Aviles
 */
public class Decal {
	private BufferedImage image;

	public Decal() {

	}
	
	/**
	 * @param image Image to set Decal to
	 */
	public Decal(BufferedImage image) {
		setImage(image);
	}
	
	/**
	 * @param file File to load decal from
	 */
	public Decal(File file) {
		try {
			setImage(ImageIO.read(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(File file) {
		try {
			setImage(ImageIO.read(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
