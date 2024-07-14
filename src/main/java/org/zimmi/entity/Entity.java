package org.zimmi.entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int entityXPos, entityYPos;
    public int entitySpeed;

    //Images are stored in a BufferedImage variable
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
}
