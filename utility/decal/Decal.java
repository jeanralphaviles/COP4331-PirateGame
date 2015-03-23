package utility.decal;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import utility.Course;

public class Decal {
    private BufferedImage image;

    public static final String default_entity = "./Sprites/item/C_Hat02.png";
    public static final String summoner = "./Sprites/item/S_Magic01.png";
    public static final String smasher = "./Sprites/item/W_Sword006.png";
    public static final String sneak = "./Sprites/item/Ac_Ring01.png";
    public static final String summonerPicture = "./Sprites/characters/parrotMancer.jpg";
    public static final String smasherPicture = "./Sprites/characters/gangPlanker.jpg";
    public static final String sneakPicture = "./Sprites/characters/turnHat.png";
    public static final String item_default = "./Sprites/item/W_Mace010.png"; 
    private static final String terrain = "./Sprites/map.png";
    public static final Decal grass = new Decal("grass");
    public static final Decal mountain = new Decal("mountain");
    public static final Decal water = new Decal("water");
    public static final String heal_damage = "./Sprites/item/S_Holy03.png";
    public static final String instant_death = "./Sprites/item/S_Death01.png"; 
    public static final String level_up = "./Sprites/item/S_Magic04.png"; 
    public static final String take_damage = "./Sprites/item/S_Fire02.png"; 
    public static final String fire = "./Sprites/item/S_Fire02.png";
    public static final String map = "./Sprites/item/I_Map.png";
    public static final String entity_spawner = "./Sprites/item/S_Holy02.png";
    public static final String shoes = "./Sprites/item/A_Shoes02.png";
    public static final String interactive_item = "./Sprites/item/S_Water07.png";
    public static final String obstacle_item = "./Sprites/item/I_Rock05.png";
    public static final String one_shot_item ="./Sprites/item/I_C_Banana.png";
    public static final String takeable_item = "./Sprites/item/W_Mace004.png";
    public static final String trader = "./Sprites/item/A_Armour03.png";
    public static final String glaives = "./Sprites/item/W_Throw004.png";
    public static final String life = "./Sprites/item/life.png";
        //Misc
    public static final String blank = "blank"; 
    private String fileName = UUID.randomUUID().toString(); // Don't Worry About it, I just need a random string here

    public Decal() {
        
    }

    public Decal(BufferedImage image) {
        setImage(image);
    }
    
    public Decal(String filename) {
        this(extractImage(filename));
        this.setFileName(fileName);
    }
    
    public Decal(File decalFile) {
    	this();
    	BufferedImage image = extractImage(decalFile);
    	if (image != null) {
    		setImage(image);
    	}
	}

	public static final BufferedImage extractImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public ImageIcon getImageIcon() {
		return new ImageIcon(getImage());
	}
        
    public static Decal getDecalFromImageIcon(ImageIcon imageIcon){
        BufferedImage bufferImage = (BufferedImage)imageIcon.getImage();
        return new Decal( bufferImage );
    }
    
    public static final BufferedImage extractImage(String filename) {
        BufferedImage img;
        int spriteWidthpx = 16; //not always applied
        int spriteHeightpx = 16; //not always applied
        switch(filename) {
            case "grass":
                img = extractImage(terrain);
                img = img.getSubimage(spriteWidthpx * 4, spriteHeightpx * 2, spriteWidthpx, spriteHeightpx);
                break;
            case "water":
                img = extractImage(terrain);
                img = img.getSubimage(spriteWidthpx * 4, spriteHeightpx * 8, spriteWidthpx, spriteHeightpx);
                break;
            case "mountain":
                img = extractImage(terrain);
                img = img.getSubimage(spriteWidthpx * 11, spriteHeightpx * 4, spriteWidthpx, spriteHeightpx);
                break;
            case "blank":
                img = new BufferedImage(spriteWidthpx, spriteHeightpx, BufferedImage.TYPE_INT_RGB);
                break;
            default:
                File file = openFile(filename);
                img = extractImage(file);
                break;
        }
        return img;
    }
    
    public Decal getRotatedDecal(Course course) {
    	int x = course.getXDisplacement();
    	int y = course.getYDisplacement();
    	double theta; // Angle to rotate image
    	if (x == -1 && y == 1) { // Facing Down-Left, Rotate pi/4 radians
    		theta = Math.PI/4;
    	} else if (x == 1 && y == 0) { // Facing Left, Rotate pi/2 radians
    		theta = Math.PI/2;
    	} else if (x == -1 && y == -1) { // Facing Up-Left, Rotate 3*pi/4 radians
    		theta = 3*Math.PI/4;
    	} else if (x == 0 && y == -1) { // Facing Up, Rotate pi radians
    		theta = Math.PI;
    	} else if (x == 1 && y == -1) { // Facing Up-Right, Rotate 5*pi/4 radians
    		theta = 5*Math.PI/4;
    	} else if (x == -1 && y == 0) { // Facing Right, Rotate 3*pi/2 radians
    		theta = 3*Math.PI/2;
    	} else if (x == 1 && y == 1) { // Facing Down-Right, Rotate 7*pi/4 radians
    		theta = 7*Math.PI/4;
    	} else { // Facing Down, do not rotate
    		theta = 0;
    	}
    	BufferedImage rotatedImg = new BufferedImage(getImage().getWidth(), getImage().getHeight(), getImage().getType());
    	Graphics2D g2d = rotatedImg.createGraphics();
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	g2d.rotate(theta, getImage().getHeight()/2, getImage().getHeight()/2);
    	g2d.drawImage(getImage(), null, 0, 0);
    	return new Decal(rotatedImg);
    }
    
    private static final File openFile(String filename) {
        return new File(filename);
    }

    public void setImage(File file) {
        BufferedImage bi = extractImage(file);
        setImage(bi);
    }
    
    public boolean equals(Decal decal) {
    	return this.fileName.equals(decal.getFileName());
    }

    @Override
    public String toString() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        if (getImage() == null) {
            return "[null]";
        }
        try {
            ImageIO.write(getImage(), "png", byteStream);
            byteStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder b64enc = new BASE64Encoder();
        String imageString = b64enc.encodeBuffer(byteStream.toByteArray());
        return "[" + imageString + "]";
    }

    public static Decal fromString(String string) {
        String stripped = string.substring(1, string.length() - 1);
        if (stripped.equals("null")) {
            return new Decal();
        }
        Decal decal = new Decal();
        try {
        	BASE64Decoder b64dec = new BASE64Decoder();
        	ByteArrayInputStream bstream = new ByteArrayInputStream(b64dec.decodeBuffer(stripped));
			decal.image = ImageIO.read(bstream);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return decal;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public static void main(String[] args) {
    	Decal orig = createDecal(Decal.grass);
    	Decal restored = Decal.fromString(orig.toString());
    	
    	if (orig.toString().equals(restored.toString()) == false) {
    		System.out.println("Failed cased #1");
    	}
    	
    	// This is dumb, why is it here? jraviles
        // Create JFrame
        JFrame window = new JFrame("Decal Test Window");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create JPanel
        JPanel panel = new JPanel();
        
        // Create Decal Holding Label
        JLabel label = new JLabel("decal label");
        
        //Create Decal
        Decal decal = createDecal(Decal.mountain);
        BufferedImage image = scale(decal, 200, 200);
        decal.setImage(image);
        
        //Create Icon, insert decal
        ImageIcon icon = new ImageIcon(decal.getImage());
        
        //Display icon in label
        label.setIcon(icon);
        
        //Integrate all components
        panel.add(label);
        window.add(panel);
        
        window.setExtendedState(Frame.MAXIMIZED_BOTH); //fullscreen
    } 
    
    
    public static BufferedImage scale(Decal decal, int width, int height) {
            BufferedImage image = decal.getImage();
            Image i = image;
            i = getScaledImage(i, width, height);
            return (BufferedImage) i;
    }
    
    
    private static Image getScaledImage(Image srcImg, int w, int h) {
            BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(srcImg, 0, 0, w, h, null);
            g2.dispose();
            return resizedImg;
    }

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public static Decal createDecal(String filename) {
		return new Decal(filename);
	} 
	
	public static Decal createDecal(Decal decal) { // This is here for reasons
		return decal;
	}
}
