/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird.main;

import flappybird.gameobjects.Bird;
import flappybird.gameobjects.Ground;
import flappybird.handlers.KeyHandler;
import flappybird.handlers.MouseHandle;
import flappybird.handlers.ObjectHandler;
import flappybird.handlers.TubeHander;
import flappybird.loaders.AudioLoader;
import flappybird.loaders.GraphicsLoader;
import flappybird.supers.Button;
import flappybird.supers.Pause;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.ServerSocket;
import javax.swing.ImageIcon;

/**
 *
 * @author ASUS
 */
@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 432;
    public static final int HEIGHT = 768;

    public boolean running;
    public static boolean gameover;

    public static BufferedImage img_gameover;
    public static BufferedImage img_message;

    //import background moring & night
    public static BufferedImage background;
    public static BufferedImage background_night;

    public static BufferedImage img_crown;
    public static BufferedImage img_silver;
    public static BufferedImage img_gold;

    public static Ground ground;

    public static boolean start;
    public static BufferedImage img_how_to_play;
    public static BufferedImage img_title_start;

    // goi class bird
    public static Bird bird;

    public static Button startButton;

    //Khai báo biến pause
    public static Pause paused;

    //Khai báo biến contineus
    public static Pause contineus;

    //Set pause
    public static boolean clickPaused;

    public static int score;
    public static int ranking;

    Thread thread;
    ServerSocket serverSocket;

    public static void main(String[] args) {
        new Window(WIDTH, HEIGHT, "Flappy Bird | beta v1.0", new Game());
        
    }
    
    
    

    public synchronized void start() {
        running = true;
        thread = new Thread();
        thread.start();
        run();
    }

    public void init() {
        addKeyListener(new KeyHandler());
        addMouseListener(new MouseHandle());

        //Import image Game over
        img_gameover = GraphicsLoader.loadGraphics("gameover.png");
        img_message = GraphicsLoader.loadGraphics("message.png");

        //import anh huy chuong
        img_crown = GraphicsLoader.loadGraphics("crown.png");
        img_silver = GraphicsLoader.loadGraphics("silver.png");
        img_gold = GraphicsLoader.loadGraphics("gold.png");

        img_how_to_play = GraphicsLoader.loadGraphics("how-to-play.png");
        img_title_start = GraphicsLoader.loadGraphics("title-start.png");

        //Import background
        background = GraphicsLoader.loadGraphics("background.png");
        background_night = GraphicsLoader.loadGraphics("background-dark.png");

        //Khởi tạo mặt cỏ
        ground = new Ground();

        bird = new Bird(50, 350, 51, 36); // (left, top, width, height)

        //Khởi tạo button chơi lại
        startButton = new Button(135, 300, 156, 87, GraphicsLoader.loadGraphics("playbutton.png"));

        contineus = new Pause(10, 10, 30, 30, GraphicsLoader.loadGraphics("contineus.png"));
        paused = new Pause(10, 10, 30, 30, GraphicsLoader.loadGraphics("pause.png"));

    }

    public void tick() {
        if (!gameover) {
            if (clickPaused == false) {
                ObjectHandler.tick();
                ground.tick();
            } else {
//                start = false;
            }

        } else {

        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Tùy thuộc vòa điểm mà chuyển sang ngày hoặc đêm
        if (score <= 5) {
            g.drawImage(background, 0, 0, null);
        } else if (score <= 10) {
            g.drawImage(background_night, 0, 0, null);
        } else if (score <= 20) {
            g.drawImage(background, 0, 0, null);
        } else {
             g.drawImage(background_night, 0, 0, null);
        }

        ground.render(g);

        ObjectHandler.render(g);

        if (gameover) {
            // (imgage, cách lề trái 70px, cách lề trên 50px, null)
            g.drawImage(img_gameover, 70, 230, null);
            g.drawImage(img_message, 80, 400, null);

            //Tùy thuộc điểm đổi huy chương
            if (score < 1) {
                g.drawImage(img_crown, 110, 455, null);
            } else if (score < 3) {
                g.drawImage(img_silver, 110, 455, null);
            } else {
                g.drawImage(img_gold, 110, 455, null);
            }

            g.setFont(new Font("Arial", Font.PLAIN, 28));
            g.setColor(Color.white);
            String s = Integer.toString(score);
            g.drawString(s, 285, 463);

            g.setFont(new Font("Arial", Font.PLAIN, 28));
            g.setColor(Color.white);
            String r = Integer.toString(ranking);
            g.drawString(r, 285, 515);

            // Khi die thì sẽ hiển thị button chơi lại
            Game.startButton.render(g);
        }

        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.setColor(Color.WHITE);
        String s = Integer.toString(score);
        int textWidth = g.getFontMetrics().stringWidth(s);
        g.drawString(s, Game.WIDTH / 2 - textWidth / 2, 60);

        // Draw nút pause & contineus
        if (clickPaused == false) {
            Game.paused.render(g);
        } else {
            Game.contineus.render(g);
        }

        // Nếu như chưa bắt đầu thì hiển thị hướng dẫn chơi
        if (start == false && clickPaused != true) {
            g.drawImage(img_how_to_play, 170, 300, null);
            g.drawImage(img_title_start, 70, 120, null);
        }

        g.dispose();
        bs.show();
    }

    // Hàm run của Thread
    @Override
    public void run() {
        init();
        this.requestFocus();

        long pastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - pastTime) / ns;
            pastTime = now;

            while (delta > 0) {
                tick();
                updates++;

                render();
                frames++;

                delta--;
            }

            // Kiểm tra FPS nd TICKS
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " | TICKS: " + updates);

                //Nếu như bắt đầu thì ống cống sẽ chạy ra
                if (start == true) {
                    if (clickPaused == false) {
                        TubeHander.tick();
                    }
                }

                updates = 0;
                frames = 0;
            }
        }
    }

}
