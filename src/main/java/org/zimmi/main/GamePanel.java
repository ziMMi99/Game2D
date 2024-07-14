package org.zimmi.main;

import org.zimmi.entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile

    //The game is tile-based, so by setting a max col- and row-tile size, you can multiply by the tile size to set the screen size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixel
    final int screenHeight = tileSize * maxScreenRow; // 576 pixel

    // FPS
    int FPS = 60;

    //Player default position
    int playerXPos = 100;
    int playerYPos = 100;
    int playerSpeed = 4;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyHandler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        //Add the key listener to the GamePanel
        this.addKeyListener(keyHandler);
        //This makes the GamePanel focused, so it can receive key input
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        //Divide 1 second in nanoseconds by FPS to get a draw interval
        double drawInterval = (double) 1000000000 / FPS; // 0.0166666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                // Update: Character position
                update();

                // Draw: Draw character at updated position
                // repaint() method, calls "painComponent" method
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {

        player.update();
    }

    //Override existing JPanel method, and call the parent class method to paint the component.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D) g;

        player.draw(g2D);

        //Dispose of this graphics context and release any system resources that its using
        g2D.dispose();
    }

}
