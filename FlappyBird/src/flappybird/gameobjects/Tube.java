/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.gameobjects;

import flappybird.enums.TubeType;
import flappybird.handlers.ObjectHandler;
import flappybird.loaders.AudioLoader;
import flappybird.loaders.GraphicsLoader;
import flappybird.main.Game;
import flappybird.supers.GameObject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author ASUS
 */
public class Tube extends GameObject {

    TubeType type;
    BufferedImage tubeBlock; // Khởi tạo miệng ống cống
    BufferedImage tube;// Khởi tạo ống cống

    public Tube(int x, int y, int width, int height, TubeType type) {
        super(x, y, width, height);

        this.type = type;
        this.velX = 3;

        //import ảnh
        tube = GraphicsLoader.loadGraphics("tube.png");

        if (type == TubeType.BOTTOM) {
            tubeBlock = GraphicsLoader.loadGraphics("tubebottomdown.png");
        } else if (type == TubeType.TOP) {
            tubeBlock = GraphicsLoader.loadGraphics("tubebottomtop.png");
        }
    }

    @Override
    public void tick() {
        x -= velX;
        if (x + width < 0) {
            ObjectHandler.removeObject(this);
            if (type == TubeType.TOP) {
                Game.score += 1;
                AudioLoader.playTingTing();
                // Nếu điểm lớn hơn rank thì cong don rank
                if (Game.score > Game.ranking) {
                    Game.ranking += 1;
                }
            }

        }
    }

    @Override
    public void render(Graphics g) {
        if (type == TubeType.BOTTOM) {
            g.drawImage(tube, x, y, 72, height, null);
            g.drawImage(tubeBlock, x - 3, y, null);
        } else if (type == TubeType.TOP) {
            g.drawImage(tube, x, y, 72, height, null);
            g.drawImage(tubeBlock, x - 3, y + height - 36, null);
        }
    }

}
