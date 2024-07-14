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
    //This methods gets called 60 times per second
    public void update() {

        //If true
        if (keyHandler.upPressed || keyHandler.downPressed ||
                keyHandler.leftPressed || keyHandler.rightPressed) {
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

            //Update the sprite counter 60 times per second
            spriteCounter++;
            //When sprite counter reaches over 15, it will switch the sprite image to the other walking sprite, simulating walking
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                //Reset sprite counter when switching sprite number
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2D) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;

        }
        g2D.drawImage(image, entityXPos, entityYPos, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
