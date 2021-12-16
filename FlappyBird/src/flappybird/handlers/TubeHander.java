/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.handlers;

import flappybird.enums.TubeType;
import flappybird.gameobjects.Tube;
import flappybird.main.Game;
import java.util.Random;

/**
 *
 * @author ASUS
 */
public class TubeHander {

    private static Random random = new Random();

    public static int groundSize = 168; //Khởi tạo kích cỡ ground
    public static int area = Game.HEIGHT - groundSize; //  Khoảng cách hoạt dộng của ống cống
    public static int spacing = 120; // khoảng cách ống cống trên và ống cống dưới
    public static int minSize = 40;
    public static int maxSize = area - spacing - minSize;
    public static int delay = 3;
    public static int now;

    // Hàm random ống cống
    public static void spawnTube() {
        int heightTop = random.nextInt(maxSize) + 1; // độ cao của ống top

        while (heightTop < minSize) {
            heightTop = random.nextInt(maxSize) + 1; // Lấy ngẫu nhiên độ cao
        }

        int heightBottom = area - spacing - heightTop;

        Tube tubeTop = new Tube(500, 0, 78, heightTop, TubeType.TOP);
        Tube tubeBottom = new Tube(500, heightTop + spacing, 78, heightBottom, TubeType.BOTTOM);

        ObjectHandler.addObject(tubeTop); // Thêm ông cống top
        ObjectHandler.addObject(tubeBottom); //Thêm ống cống bottom
    }

    // Thời gian ống công tiếp theo xuất hiện
    public static void tick() {
        if (now < delay) {
            // Co thể tăng độ khó bằng cách cộng nhiều hơn hoặc thay đổi delay
            if(Game.score < 10){
                now +=1;
            }
            else if(Game.score < 20){
                now += 2;
            }
            else{
                now += 3;
            }
        }
        else{
            spawnTube();
            now = 0;
        }
    }
}
