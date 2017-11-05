package com.dodgeGame.main;

import java.awt.*;


public class HUD {

    public static int HEALTH = 100;
    public int score = 0;
    public int level = 1;
    public static int coins = 0;

    public void tick(){
        //stop adding score if health is 0
        if (HEALTH > 0)
        score++;

        //doesn't allow health to goes over 100 or below 0
        HEALTH = Game.clamp(HEALTH, 0, 100);
    }

    public void render(Graphics g){
        //render health bar
        Font fnt = new Font("arial", 1, 12);
        g.setFont(fnt);
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200 , 32);
        g.setColor(Color.red);
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.green);
        g.drawRect(15, 15, 200, 32);
        //renders score in level status
        g.setColor(Color.white);
        g.drawString("Score: " + score, 15, 60);
        g.drawString("Level: " + level, 15, 75);
        g.drawString("Coins: " + coins, 15, 90);
    }

    public int getScore(){
        return score;
    }

    public int getLevel(){
        return level;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getCoins(){return this.coins;}

    public void setCoins(int coins) {this.coins = coins;}

}
