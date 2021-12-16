/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.loaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author ASUS
 */
public class AudioLoader {

    //Phát nhạc khi bird bay lên
    public static void playAudio() {
        InputStream in;
        try {
            in = new FileInputStream(new File("src\\assets\\jump.wav"));
            AudioStream audio = new AudioStream(in);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
        }
    }
    
     public static void playTingTing() {
        InputStream in;
        try {
            in = new FileInputStream(new File("src\\assets\\ting-ting.wav"));
            AudioStream audio = new AudioStream(in);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
        }
    }
     
      public static void playGameOver() {
        InputStream in;
        try {
            in = new FileInputStream(new File("src\\assets\\game-over.wav"));
            AudioStream audio = new AudioStream(in);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
        }
    }
}
