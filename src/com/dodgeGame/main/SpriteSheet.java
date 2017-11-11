package com.dodgeGame.main;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class SpriteSheet {

    BufferedImage image;

    public SpriteSheet(BufferedImage image){
        this.image = image;
    }

    public BufferedImage grabImage(int col, int row, int width, int height){
        BufferedImage img = image.getSubimage((col * width) - width, (row * height) - height, width, height);
        return img;
    }

}
