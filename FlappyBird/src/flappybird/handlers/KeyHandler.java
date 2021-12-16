/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.handlers;

import flappybird.gameobjects.Bird;
import flappybird.loaders.AudioLoader;
import flappybird.main.Game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ASUS
 */
public class KeyHandler implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) {
            Game.start = true;

            Game.bird.setVelY(-6); // độ cao mà bird có thể nẩy lên khi nhấn phím Space
            if (Game.gameover == false) {
                if (Game.clickPaused != true) {
                    AudioLoader.playAudio();
                }

            }

        }

        //Khi ấn phím ESC thì sẽ dừng game
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (Game.clickPaused == true) {
                Game.clickPaused = false;
            } else {
                Game.clickPaused = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
