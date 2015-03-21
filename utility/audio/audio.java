/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.audio;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import static utility.decal.Decal.extractImage;

/**
 *
 * @author Juhi
 */
public class audio {
    private String fileName;
    private URL url;
    private AudioInputStream audioIn;
    private Clip clip;
    public static final String fightingTheStorm = "C:/Users/Juhi/Documents/OOP/Iter2/HelloWorld/COP4331-PirateGame/Songs/FightingTheStorm.au";
    //C:/Users/Juhi/Documents/OOP/Iter2/HelloWorld/COP4331-PirateGame/Songs/FightingTheStorm.au
    
    
    public audio() {
        
    }

    public audio(URL url) {
        try {
            // Open an audio input stream.
            audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(audio.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    public audio(String filename) {
        
        try {
            // Open an audio input stream.
            url = this.getClass().getResource(fileName);
            audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(audio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(audio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public void playMusic(){
        clip.start();
    }
     public void stopMusic(){
        clip.stop();
     }
   
}
