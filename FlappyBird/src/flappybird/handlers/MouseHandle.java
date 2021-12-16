/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.handlers;

import flappybird.gameobjects.Bird;
import flappybird.main.Game;
import flappybird.supers.Button;
import flappybird.supers.Pause;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author ASUS
 */
public class MouseHandle implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (Button.checkCollision(e.getX(), e.getY(), Game.startButton)) {
            if (Game.gameover) {
                Game.startButton.pressed = true;
                ObjectHandler.list.clear();
//                ObjectHandler.addObject(Game.bird);
                Game.gameover = false;
                Game.score = 0;
                Game.startButton.pressed = true;
                Game.bird = new Bird(50, 50, 51, 36);
            }

        }

        if (Pause.checkPause(e.getX(), e.getY(), Game.paused)) {
//            Game.paused.pressed = true;
            // Khi người dung nhấn pause
//            Game.clickPaused = true;
            if (Game.clickPaused == true) {
                Game.clickPaused = false;
            } else {
                Game.clickPaused = true;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
