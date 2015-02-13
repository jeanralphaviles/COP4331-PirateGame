package utility.decal;

import java.awt.image.BufferedImage;

public final class BlankDecal extends Decal {
	private static final int spriteWidthpx = 16;
	private static final int spriteHeightpx = 16;

	public BlankDecal() {
		super(new BufferedImage(getSpriteWidthpx(), getSpriteHeightpx(),
				BufferedImage.TYPE_INT_RGB));
	}

	public static int getSpriteHeightpx() {
		return spriteHeightpx;
	}

	public static int getSpriteWidthpx() {
		return spriteWidthpx;
	}

}
