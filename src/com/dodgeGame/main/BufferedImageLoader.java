package com.dodgeGame.main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BufferedImageLoader {

    private BufferedImage image;

    public BufferedImage loadImage(String path){

        try {
            System.out.println(getClass().getResource(path));
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

}
