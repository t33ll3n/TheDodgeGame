package com.dodgeGame.main;

import java.awt.image.BufferedImage;

public class Texture {

    SpriteSheet ss;
    SpriteSheet bombS;
    SpriteSheet explosionS;
    SpriteSheet basicEnemyS;

    //array of animation images
    public BufferedImage[] item = new BufferedImage[17];
    public BufferedImage[] bomb = new BufferedImage[4];
    public BufferedImage[] explosion = new BufferedImage[4];
    public BufferedImage[][] basicEnemy = new BufferedImage[4][2];

    //sprite sheets
    private BufferedImage item_sheet;
    private BufferedImage bomb_sheet;
    private BufferedImage explosion_sheet;
    private BufferedImage basic_enemy;


    public Texture(){

        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            item_sheet = loader.loadImage("\\sprites\\item_sheet.png");
            bomb_sheet = loader.loadImage("\\sprites\\bomba1.png");
            explosion_sheet = loader.loadImage("\\sprites\\explosion1.png");
            basic_enemy = loader.loadImage("\\sprites\\basic_monster.png");
        } catch (Exception e){
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }

        ss = new SpriteSheet(item_sheet);
        bombS = new SpriteSheet(bomb_sheet);
        explosionS = new SpriteSheet(explosion_sheet);
        basicEnemyS = new SpriteSheet(basic_enemy);

        getTexture();
    }

    private void getTexture(){
        //item[0] = ss.grabImage(1, 1, 16, 16);
        //Loads images for coin animation
        for (int i = 1; i <= 17; i++){
            item[i-1] = ss.grabImage(i, 1, 16, 16);
        }
        //System.out.println(bombS);
        //Loads images for bomb
        for (int i = 1; i <= 4; i++){
            bomb[i-1] = bombS.grabImage(i, 1, 30, 30);
        }

        //Loads images for explosion
        for (int i = 1; i <= 4; i++){
            explosion[i-1] = explosionS.grabImage(i, 1, 30, 30);
        }

        for (int i = 1; i <= 4; i++){
            for (int j = 1; j <= 2; j++)
            basicEnemy[i-1][j-1] = basicEnemyS.grabImage(j, i, 32, 32);
        }

    }

}
