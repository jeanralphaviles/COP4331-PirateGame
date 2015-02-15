package utility.decal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class LevelUpAreaEffectDecal extends Decal {
	private static final int spriteWidthpx = 32;
	private static final int spriteHeightpx = 32;

	public LevelUpAreaEffectDecal() {
		File areaEffectFile = new File("./Sprites/item/S_Magic04.png");
		try {
			BufferedImage levelUpDecal = ImageIO.read(areaEffectFile);
			setImage(levelUpDecal);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public LevelUpAreaEffectDecal(BufferedImage image) {
		super(image);
	}

	public LevelUpAreaEffectDecal(File file) {
		super(file);
	}

	public static int getSpritewidthpx() {
		return spriteWidthpx;
	}

	public static int getSpriteheightpx() {
		return spriteHeightpx;
	}

}
