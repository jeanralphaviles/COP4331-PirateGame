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
    
    // Icons to from screen to screes    public static final String trade_pic = "./Sprites/backgrounds/gold_coin.jpeg";

    public static final String go_to_gamescreen_pic = "./Sprites/backgrounds/begin_adventure_icon.png";
    public static final String go_to_mainscreen_pic = "./Sprites/backgrounds/ship1.png";

    
    // NewGamePopUp Icons
    public static final String character_parrotmancer = "./Sprites/characters/ParrotMancer_NoBG.png";
    public static final String character_gankplanker = "./Sprites/characters/gangPlanker.jpg";
    public static final String character_turnhat = "./Sprites/characters/turnHat.png";
    public static final String new_game_background = "./Sprites/backgrounds/pirate_wallpaper_nice_boat.jpg";
  
    // Path for characters and backgrounds
    public static final String trader_pic = "./Sprites/characters/DaveOnACamel.png";
    public static final String trader_view_port_background = "./Sprites/backgrounds/pirate_wallpaper_nice_boat.jpg";
    public static final String item = "./Sprites/item/A_Armour03.png";
    public static final String options_viewport_background = "./Sprites/backgrounds/map_theme.jpg";
    public static final String trader_background = "./Sprites/backgrounds/desert_backgrounds.jpg";
    
    // Trade Viewport Items
     public static final String blue_potion_1 = "./Sprites/item/P_Blue01.png";
     public static final String blue_potion_2 = "./Sprites/item/P_Blue02.png";
     public static final String blue_potion_3 = "./Sprites/item/P_Blue03.png";
     public static final String blue_potion_4 = "./Sprites/item/P_Blue04.png";
     
     public static final String red_potion_1 = "./Sprites/item/P_Red01.png";
     public static final String red_potion_2 = "./Sprites/item/P_Red02.png";
     public static final String red_potion_3 = "./Sprites/item/P_Red03.png";
     public static final String red_potion_4 = "./Sprites/item/P_Red04.png";
     
     public static final String fruit_1 = "./Sprites/item/I_C_Pineapple.png";
     public static final String fruit_2 = "./Sprites/item/I_C_Cherry.png";
     public static final String fruit_3 = "./Sprites/item/I_C_Orange.png";
     public static final String fruit_4 = "./Sprites/item/I_C_Banana.png";
     
     public static final String sword_1 = "./Sprites/item/S_Sword09.png";
     public static final String sword_2 = "./Sprites/item/S_Sword10.png";
     public static final String sword_3 = "./Sprites/item/W_Sword013.png";
     public static final String sword_4 = "./Sprites/item/W_Sword004.png";
     
     public static final String book_1 = "./Sprites/item/W_Book_OO.png";
     public static final String book_2 = "./Sprites/item/W_Book02.png";
     public static final String book_3 = "./Sprites/item/W_Book03.png";
     public static final String book_4 = "./Sprites/item/W_Book04.png";

     public static final String booty_pic = "./Sprites/backgrounds/treasure.png";
     public static final String dialogue_viewport_background = "./Sprites/backgrounds/blank_black_background.jpg";

    public static ImageIcon getImage(ImageIcon image, int width, int height){
        
         BufferedImage bufferedImage = (BufferedImage)image.getImage();
         bufferedImage = scale(width,height,bufferedImage);
         return new ImageIcon( bufferedImage );
    }
   
    public static ImageIcon getImage(String fileName){
        
         BufferedImage bufferedImage = Decal.createDecal(fileName).getImage();
         bufferedImage = scale(500,550,bufferedImage);
         return new ImageIcon( bufferedImage );
    }
    
    public static ImageIcon getImage(String fileName, int width, int height){
        
         BufferedImage bufferedImage = Decal.createDecal(fileName).getImage();
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
