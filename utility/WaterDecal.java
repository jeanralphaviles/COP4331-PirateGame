package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class WaterDecal extends Decal {
	private final int spriteWidthpx = 16;
	private final int spriteHeightpx = 16;

	public WaterDecal() {
		File mapSpriteSheet = new File("./../Sprites/map.png");
		try {
			BufferedImage sprites = ImageIO.read(mapSpriteSheet);
			BufferedImage waterSprite = sprites.getSubimage(spriteWidthpx*4, spriteHeightpx*8,
															spriteWidthpx, spriteHeightpx);
			setImage(waterSprite);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WaterDecal(BufferedImage image) {
		super(image);
	}

	public WaterDecal(File file) {
		super(file);
	}

}
