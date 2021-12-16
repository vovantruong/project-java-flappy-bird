/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.supers;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author ASUS
 */
public class Pause {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean pressed;
    private BufferedImage image;

    public Pause(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public static boolean checkPause(int mouseX, int mouseY, Pause btn) {

        if (mouseX >= btn.x && mouseX <= btn.x + btn.width && mouseY >= btn.y && mouseY <= btn.y + btn.height) {
            return true;
        } else {
            return false;
        }
    }

    public void render(Graphics g) {
        if (pressed) {
            g.drawImage(image, -50, -50, width - 2, height - 2, null);
        } else {
            g.drawImage(image, x, y, null);
        }
    }
}
