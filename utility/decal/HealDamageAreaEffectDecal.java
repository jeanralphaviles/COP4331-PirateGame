package utility.decal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class HealDamageAreaEffectDecal extends Decal {
	private static final int spriteWidthpx = 32;
	private static final int spriteHeightpx = 32;

	public HealDamageAreaEffectDecal() {
		File areaEffectFile = new File("./../Sprites/item/S_Holy03.png");
		try {
			BufferedImage healDecal = ImageIO.read(areaEffectFile);
			setImage(healDecal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HealDamageAreaEffectDecal(BufferedImage image) {
		super(image);
	}

	public HealDamageAreaEffectDecal(File file) {
		super(file);
	}

	public static int getSpriteheightpx() {
		return spriteHeightpx;
	}

	public static int getSpritewidthpx() {
		return spriteWidthpx;
	}

}
