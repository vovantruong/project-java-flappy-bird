/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.handlers;

import flappybird.supers.GameObject;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 *
 * @author ASUS
 */
public class ObjectHandler {

    public static LinkedList<GameObject> list = new LinkedList<GameObject>();

    public static void addObject(GameObject o) {
        list.add(o);
    }

    public static void removeObject(GameObject o) {
        list.remove(o);
    }

    public static void render(Graphics g) {
        GameObject temp = null;

        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            temp.render(g);
        }
    }

    public static void tick() {
        GameObject temp = null;

        for (int i = 0; i < list.size(); i++) {
            temp = list.get(i);
            temp.tick();
        }

    }
}
