package com.dodgeGame.main;

import java.awt.*;
import java.util.Random;


public class Player extends GameObject {

    Handler handler;
    Random r = new Random();

    private Color color, prevColor;
    private long time = 0;


    public Player(int x, int y, ID id, Handler handler, Color color){
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.prevColor = color;

    }

    private void collision(){

        //loops through the game objects
        for(int i = 0; i < handler.object.size(); i++){

            GameObject tmpObject = handler.object.get(i);

            //if the object is enemy check for collision
            if(tmpObject.getId() == ID.BasicEnemy || tmpObject.getId() == ID.FastEnemy || tmpObject.getId() == ID.SmartEnemy){
                //calls the collision rectangle of player and checks if it intersects with enemies
                if(getBounds().intersects(tmpObject.getBounds())) {
                    //collision code

                    //if so, life goes down
                    HUD.health -= 1;
                    if (HUD.health <= 0){
                        Game.gameState = Game.STATE.End;
                    }
                    time = System.currentTimeMillis();
                    color = Color.red;
                } else {
                    if (System.currentTimeMillis() - time > 200) {
                        color = prevColor;
                    }
                }
            }
            //if the object is LifeToken check for collision
            if (tmpObject.getId() == ID.LifeToken){
                if (getBounds().intersects(tmpObject.getBounds())){
                    HUD.health += 50;
                    handler.removeObject(tmpObject);
                }
            }

            //if the object is coin check for collision
            if (tmpObject.getId() == ID.Coin){
                if (getBounds().intersects(tmpObject.getBounds())){
                    HUD.coins += 1;
                    handler.removeObject(tmpObject);
                }
            }
        }
    }

    @Override
    public void tick() {
        //code that happens every game tick

        //speed and movement of the player
        x += velX;
        y += velY;

        //doesn't allow player to get of the screen
        x = Game.clamp(x, 0, Game.WIDTH - 37);
        y = Game.clamp(y, 0, Game.HEIGHT - 59);
        //check for collision
        collision();
    }

    @Override
    public void render(Graphics g) {
        //render player
        Graphics2D g2d = (Graphics2D)g;

        g.setColor(color);
        g.fillRect(x, y, 32, 32);
    }

    //returns rectangle same size as player for collision detection
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
