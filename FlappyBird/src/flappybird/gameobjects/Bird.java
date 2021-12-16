/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.gameobjects;

import flappybird.handlers.ObjectHandler;
import flappybird.loaders.AudioLoader;
import flappybird.loaders.GraphicsLoader;
import flappybird.main.Game;
import flappybird.supers.Animation;
import flappybird.supers.GameObject;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author ASUS
 */
public class Bird extends GameObject {

    Animation animation;

    public float gravity;
    public float maxSpeed;

    public Bird(int x, int y, int width, int height) {
        super(x, y, width, height);

        gravity = 0.3f; // độ nảy của bird || càng lớn độ nảy càng nhỏ và ngược lại
        maxSpeed = 12f; // tốc độ bird rơi xuống dưới

        //import imgae bird từ 1 - 3 tương đương với độ vẫy cánh
        BufferedImage[] images = new BufferedImage[3];

        for (int i = 0; i < images.length; i++) {
            images[i] = GraphicsLoader.loadGraphics("bird" + i + ".png");
        }

        animation = new Animation(this, 100, true, images);
        animation.start();

        ObjectHandler.addObject(this);
    }

    @Override
    public void tick() {
        //Nếu start == flase thi bird sẽ không rơi
        if (Game.start == true) {
            velY += gravity;
            y += velY;

            if (velY > maxSpeed) {
                velY = maxSpeed;

            }
        }

        // Bird sẽ nằm trong khoảng từ top đến mặt cỏ (ground);
        // 166 là độ cao của "ground.png" - 2
        if (y + height > Game.HEIGHT - 166) {
            y = Game.HEIGHT - 166 - height;
            setVelY(0);
            Game.gameover = true; // bird đụng lệ trên => gameover
            AudioLoader.playGameOver();
        }

        if (y < 0) {
            y = 0;
            setVelY(0);
            Game.gameover = true; //bird đụng lề dưới => game over
            AudioLoader.playGameOver();
        }

        GameObject temp = null;
        for (int i = 0; i < ObjectHandler.list.size(); i++) {
            temp = ObjectHandler.list.get(i);

            if (temp instanceof Tube) {
                if (this.getBounds().intersects(temp.getBounds())) {
                    Game.gameover = true;
                    AudioLoader.playGameOver();
                }
            }
        }

        animation.tick();
    }

    @Override
    public void render(Graphics g) {
        animation.render(g);
    }

}
