/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.loaders;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ASUS
 */
public class GraphicsLoader {
    public static BufferedImage loadGraphics(final String path){
        BufferedImage image = null; //Gán imag ban đầu bằng null
        
        try {
            // sử dụng thư viên imageIO để đọc path từ "resourceLoader qua"
            image = ImageIO.read(ResourceLoader.load("/" + path)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return image;
    }
}
