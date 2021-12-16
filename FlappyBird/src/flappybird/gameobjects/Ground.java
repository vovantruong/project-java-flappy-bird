/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.gameobjects;

import flappybird.loaders.GraphicsLoader;
import flappybird.main.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author ASUS
 */
public class Ground {

    private BufferedImage image;
    private int x1, x2;

    private float velX;

    public Ground() {
        x1 = 0;
        x2 = Game.WIDTH;

        velX = 3;

        image = GraphicsLoader.loadGraphics("ground.png");
    }

    public void tick() {

        x1 -= velX;
        x2 -= velX;

        if (x1 + Game.WIDTH < 0) {
            x1 = Game.WIDTH;
        }

        if (x2 + Game.WIDTH < 0) {
            x2 = Game.WIDTH;
        }
    }

    public void render(Graphics g) {
        g.drawImage(image, x1, Game.HEIGHT - 168, null);
        g.drawImage(image, x2, Game.HEIGHT - 168, null);
    }

}
