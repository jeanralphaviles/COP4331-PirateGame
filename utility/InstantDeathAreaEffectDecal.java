package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class InstantDeathAreaEffectDecal extends Decal {
	private static final int spriteHeightpx = 32;
	private static final int spriteWidthpx = 32;

	public InstantDeathAreaEffectDecal() {
		File decalFile = new File("./../Sprites/item/S_Death01.png");
		try {
			BufferedImage deathDecal = ImageIO.read(decalFile);
			setImage(deathDecal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public InstantDeathAreaEffectDecal(BufferedImage image) {
		super(image);
	}

	public InstantDeathAreaEffectDecal(File file) {
		super(file);
	}

	public static int getSpritewidthpx() {
		return spriteWidthpx;
	}

	public static int getSpriteheightpx() {
		return spriteHeightpx;
	}

}
