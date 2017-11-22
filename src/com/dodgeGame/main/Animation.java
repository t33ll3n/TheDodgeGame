package com.dodgeGame.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed;
    private int frames;

    private int index = 0;
    private int count = 0;

    private BufferedImage[] images;
    private BufferedImage currentImg;

    public Animation(int speed, BufferedImage[] args){ // int, infinite amount of parameters
        this.speed = speed;
        images = new BufferedImage[args.length];

        for (int i = 0; i < args.length; i++){
            images[i] = args[i];
        }
        frames = args.length;
    }

    public void runAnimation(){
        index++;
        if (index > speed){
            index = 0;
            nextFrame();
        }
    }

    public int runAnimationOnce(){
        index++;
        if (index > speed){
            index = 0;
            return nextFrameOnce();
        }
        return 0;
    }

    private void nextFrame(){
        for (int i = 0; i < frames; i++){
            if (count == i){
                currentImg = images[i];
            }
        }

        count++;

        if (count > frames){
            count = 0;
        }
    }

    private int nextFrameOnce(){
        for (int i = 0; i < frames; i++){
            if (count == i){
                currentImg = images[i];
            }
        }

        count++;

        if (count > frames){
            return 1;
        }
        return 0;
    }

    public void drawAnimation(Graphics g, int x, int y){
        g.drawImage(currentImg, x, y, null);
    }


    public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY){
        g.drawImage(currentImg, x, y, scaleX, scaleY, null);
    }

}
