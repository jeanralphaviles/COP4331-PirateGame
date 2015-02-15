package utility.decal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class TakeDamageAreaEffectDecal extends Decal {
	private static final int spriteHeightpx = 32;
	private static final int spriteWeightpx = 32;

	public TakeDamageAreaEffectDecal() {
		File areaEffectFile = new File("./Sprites/item/S_Fire02.png");
		try {
			BufferedImage damageDecal = ImageIO.read(areaEffectFile);
			setImage(damageDecal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public TakeDamageAreaEffectDecal(BufferedImage image) {
		super(image);
	}

	public TakeDamageAreaEffectDecal(File file) {
		super(file);
	}

	public static int getSpriteweightpx() {
		return spriteWeightpx;
	}

	public static int getSpriteheightpx() {
		return spriteHeightpx;
	}

}
