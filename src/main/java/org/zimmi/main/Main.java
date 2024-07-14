package org.zimmi.main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame gameFrame = new JFrame();


        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setTitle("2D Adventure");

        GamePanel gamePanel = new GamePanel();

        gameFrame.add(gamePanel);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        gamePanel.startGameThread();
    }
}