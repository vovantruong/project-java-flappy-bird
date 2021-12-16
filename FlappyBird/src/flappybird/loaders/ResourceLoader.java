/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.loaders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author ASUS
 */
public class ResourceLoader {

    public static InputStream load(final String path) throws IOException {
        InputStream input = ResourceLoader.class.getResourceAsStream(path);
        if (input == null) {
            //Lấy đường dẫn ảnh bằng getResourceAsStream.
            input = ResourceLoader.class.getResourceAsStream("/assets/" + path);
        }
        return input;
    }
}
