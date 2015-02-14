package utility.decal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DefaultEntityDecal extends Decal {
	private final int spriteHeightpx = 16;
	private final int spriteWidthpx = 16;

	public DefaultEntityDecal() {
		File areaEffectFile = new File("./../../Sprites/item/C_Hat02.png");
		try {
			BufferedImage itemDecal = ImageIO.read(areaEffectFile);
			setImage(itemDecal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DefaultEntityDecal(BufferedImage image) {
		super(image);
	}

	public DefaultEntityDecal(File file) {
		super(file);
	}

	public int getSpriteWidthpx() {
		return spriteWidthpx;
	}

	public int getSpriteHeightpx() {
		return spriteHeightpx;
	}

}
