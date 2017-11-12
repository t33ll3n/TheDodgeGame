package com.dodgeGame.main;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet ss;
    public BufferedImage[] item = new BufferedImage[17];
    private BufferedImage item_sheet;


    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            item_sheet = loader.loadImage("item_sheet.png");
        } catch (Exception e){
            e.printStackTrace();
        }

        ss = new SpriteSheet(item_sheet);

        getTexture();
    }

    private void getTexture(){
        //item[0] = ss.grabImage(1, 1, 16, 16);
        //Loader images for coin animation
        for (int i = 1; i <= 17; i++){
            item[i-1] = ss.grabImage(i, 1, 16, 16);
        }
    }

}
