package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Decal {
	private BufferedImage image;
	
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
