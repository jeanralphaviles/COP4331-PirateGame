package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class GrassDecal extends Decal {
	private final int spriteWidthpx = 16;
	private final int spriteHeightpx = 16;

	/**
	 * Creates a new default grass decal
	 */
	public GrassDecal() {
		File mapSpriteSheet = new File("./../Sprites/map.png");
		try {
			BufferedImage sprites = ImageIO.read(mapSpriteSheet);
			BufferedImage grassSprite = sprites.getSubimage(spriteWidthpx * 4,
					spriteHeightpx * 2, spriteWidthpx, spriteHeightpx);
			setImage(grassSprite);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GrassDecal(File file) {
		super(file);
	}

	public GrassDecal(BufferedImage image) {
		super(image);
	}

	public int getSpriteWidthpx() {
		return spriteWidthpx;
	}

	public int getSpriteHeightpx() {
		return spriteHeightpx;
	}

}
