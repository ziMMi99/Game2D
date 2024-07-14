package org.zimmi.entity;

import org.zimmi.main.GamePanel;
import org.zimmi.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {
    //The player needs to know which panel it is in, and what keyhandler its using
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        //Set the game panel and the key handler that the player is using
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage() {

        try {

            up1    = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            up2    = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            down1  = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            down2  = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            left1  = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            left2  = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        //Even though these variables aren't initialized in this class, they can still be set. Because they are a part of the entity class.
        entityXPos = 100;
        entityYPos = 100;
        entitySpeed = 4;
        direction = "down";
    }

    //Make player specific update and draw methods, to save alot of code in the GamePanel's update and draw method. Now only a method call is needed, and it makes the code more readable
    public void update() {
        if (keyHandler.upPressed) {
            direction = "up";
            entityYPos = entityYPos - entitySpeed;
        } else if (keyHandler.downPressed) {
            direction = "down";
            entityYPos = entityYPos + entitySpeed;
        } else if (keyHandler.leftPressed) {
            direction = "left";
            entityXPos = entityXPos - entitySpeed;
        } else if (keyHandler.rightPressed) {
            direction = "right";
            entityXPos = entityXPos + entitySpeed;
        }
    }

    public void draw(Graphics2D g2D) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;

        }
        g2D.drawImage(image, entityXPos, entityYPos, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
