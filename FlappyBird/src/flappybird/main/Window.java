/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author ASUS
 */
@SuppressWarnings("serial")
public class Window extends JFrame{
    public Window(int width, int height, String title, Game game){
        
        try {
            game.serverSocket = new ServerSocket(9999); // kết nối serveSoker kiểm tra ping
        } catch (IOException ex) {
            System.out.println("Spiel bereits getstartet!");
            System.exit(0);
        }
        
        setTitle(title); // Tạp tiêu đề cho game
        pack();
//        setSize(width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom);
       setSize(width , height );
        
        setLocationRelativeTo(null); // Không cho phóng to bằng nút trên thanh công cụ
        setResizable(false); // Không cho kéo rộng ứng dụng
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setVisible(true); // mở ứng dụng
        
        add(game);
        game.start();
    }
}
