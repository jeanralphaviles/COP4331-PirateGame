package utility.decal;

import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringBufferInputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Decal {

    /*Properties*/
    
    private BufferedImage image;
    //Constant Strings by category
        //Entity
    public static final String default_entity = "./Sprites/item/C_Hat02.png";
        //Item
    public static final String item_default = "./Sprites/item/W_Mace010.png"; 
        //Terrain
    private static final String terrain = "./Sprites/map.png";
    public static final String grass = "grass";
    public static final String mountain = "mountain"; 
    public static final String water = "water"; 
        //Area Effect
    public static final String heal_damage = "./Sprites/item/S_Holy03.png";
    public static final String instant_death = "./Sprites/item/S_Death01.png"; 
    public static final String level_up = "./Sprites/item/S_Magic04.png"; 
    public static final String take_damage = "./Sprites/item/S_Fire02.png"; 

    /*Constructors*/
    
    public Decal() {
        
    }

    public Decal(BufferedImage image) {
        setImage(image);
    }
    
    public Decal(String filename) {
        this(extractImage(filename));
    }

    //deprecated
    public Decal(File file) {
        this(extractImage(file));
    }
    
    /*Methods*/
    
    public static final BufferedImage extractImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
            default:
                File file = openFile(filename);
                img = extractImage(file);
                break;
        }
        return img;
    }
    
    //should be in loadsave
    private static final File openFile(String filename) {
        return new File(filename);
    }

    public void setImage(File file) {
        BufferedImage bi = extractImage(file);
        setImage(bi);
    }

    @Override
    public String toString() {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        if (getImage() == null) {
            return "[nullolol]";
        }
        try {
            ImageIO.write(getImage(), ".png", byteStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "[" + byteStream.toString() + "]";
    }

    public static Decal fromString(String string) throws IOException {
        String stripped = string.substring(1, string.length() - 1);
        if (stripped.equals("nullolol")) {
            return new Decal();
        }
        @SuppressWarnings("deprecation")
        StringBufferInputStream sstream = new StringBufferInputStream(stripped);
        Decal decal = new Decal();
        decal.image = ImageIO.read(sstream);
        return decal;
    }
    
    /*Get-Sets*/
    
    public BufferedImage getImage() {
        return image;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    /*Testing*/
    
    public static void main(String[] args){
        
        // Create JFrame
        JFrame window = new JFrame("Decal Test Window");
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create JPanel
        JPanel panel = new JPanel();
        
        // Create Decal Holding Label
        JLabel label = new JLabel("decal label");
        
        //Create Decal
        Decal decal = new Decal(Decal.mountain);
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
    
    //may or may not belong here
    public static BufferedImage scale(Decal decal, int width, int height) {
            BufferedImage image = decal.getImage();
            Image i = (Image) image;
            i = getScaledImage(i, width, height);
            return (BufferedImage) i;
        }
    
    //may or may not belong here
    private static Image getScaledImage(Image srcImg, int w, int h) {
            BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImg.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(srcImg, 0, 0, w, h, null);
            g2.dispose();
            return resizedImg;
        }
}
