package com.dodgeGame.main;

import java.awt.*;


public class HUD {

    public static int health = 100;
    public static int score = 0;
    public static int level = 1;
    public static int coins = 0;
    private static int bombs = 0;

    public void tick(){
        //stop adding score if health is 0
        if (health > 0)
        score++;

        //doesn't allow health to goes over 100 or below 0
        health = Game.clamp(health, 0, 100);
    }

    public void render(Graphics g){
        //render health bar
        Font fnt = new Font("lilliPut Steps", 1, 12);
        g.setFont(fnt);
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200 , 32);
        g.setColor(Color.red);
        g.fillRect(15, 15, health * 2, 32);
        g.setColor(Color.green);
        g.drawRect(15, 15, 200, 32);
        //renders score in level status
        g.setColor(Color.white);
        g.drawString("Score: " + score, 15, 60);
        g.drawString("Level: " + level, 15, 75);
        g.drawString("Coins: " + coins, 15, 90);
    }

    public int getHealth() { return this.health; }

    public void setHealth(int health) { this.health = health; }

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

    public int getCoins(){ return this.coins; }

    public void setCoins(int coins) {this.coins = coins;}

    public int getBombs(){ return this.bombs; }

    public void setBombs(int bombs){ this.bombs = bombs; }
}
