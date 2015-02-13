package utility.decal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ItemDefaultDecal extends Decal {
	private static final int spriteHeightpx = 32;
	private static final int spriteWidthpx = 32;

	public ItemDefaultDecal() {
		File areaEffectFile = new File("./../Sprites/item/W_Mace010.png");
		try {
			BufferedImage itemDecal = ImageIO.read(areaEffectFile);
			setImage(itemDecal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ItemDefaultDecal(BufferedImage image) {
		super(image);
	}
	
	public ItemDefaultDecal(File file) {
		super(file);
	}

	public static int getSpriteheightpx() {
		return spriteHeightpx;
	}

	public static int getSpritewidthpx() {
		return spriteWidthpx;
	}

}
