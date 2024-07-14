package org.zimmi.entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int entityXPos, entityYPos;
    public int entitySpeed;

    //Images are stored in a BufferedImage variable
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    //SpriteNum variable controls which sprite will be shown, fx. When simulating a walking sprite, you switch between 2 sprites to simulate walking.
    //This variable controls which sprite is to be shown
    public int spriteNum = 1;
}
