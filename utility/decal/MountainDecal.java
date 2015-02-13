package utility.decal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class MountainDecal extends Decal {
	private final int spriteWidthpx = 16;
	private final int spriteHeightpx = 16;

	/**
	 * Creates a new default mountain tile
	 */
	public MountainDecal() {
		File mapSpriteSheet = new File("./../../Sprites/map.png");
		try {
			BufferedImage sprites = ImageIO.read(mapSpriteSheet);
			BufferedImage mountainSprite = sprites.getSubimage(
					spriteWidthpx * 11, spriteHeightpx * 4, spriteWidthpx,
					spriteHeightpx);
			setImage(mountainSprite);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MountainDecal(BufferedImage image) {
		super(image);
	}

	public MountainDecal(File file) {
		super(file);
	}

	public int getSpriteWidthpx() {
		return spriteWidthpx;
	}

	public int getSpriteHeightpx() {
		return spriteHeightpx;
	}

}
