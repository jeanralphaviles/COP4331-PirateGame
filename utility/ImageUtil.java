/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import utility.decal.Decal;

/**
 *
 * @author darien
 */
public class ImageUtil {
    
    // Path for characters and backgrounds
    public static final String trader_pic = "./Sprites/characters/DaveOnACamel.jpg";
    public static final String trader_view_port_background = "./Sprites/backgrounds/pirate_wallpaper_nice_boat.jpg";
    public static final String item = "./Sprites/item/A_Armour03.png";
    public static final String options_viewport_background = "./Sprites/backgrounds/map_theme.jpg";
    
    public static ImageIcon getImage(ImageIcon image, int width, int height){
        
         BufferedImage bufferedImage = (BufferedImage)image.getImage();
         bufferedImage = scale(width,height,bufferedImage);
         return new ImageIcon( bufferedImage );
    }
   
    public static ImageIcon getImage(String fileName){
        
         BufferedImage bufferedImage = new Decal(fileName).getImage();
         bufferedImage = scale(500,550,bufferedImage);
         return new ImageIcon( bufferedImage );
    }
    
    public static ImageIcon getImage(String fileName, int width, int height){
        
         BufferedImage bufferedImage = new Decal(fileName).getImage();
         bufferedImage = scale(width,height,bufferedImage);
         return new ImageIcon( bufferedImage );
    }
    
    private static BufferedImage scale(int width, int height, BufferedImage image) {
            
            Image i = image;
            i = getScaledImage(i, width, height);
            return (BufferedImage) i;
        }

    private static  Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}
