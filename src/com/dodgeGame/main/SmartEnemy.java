package com.dodgeGame.main;

import java.awt.*;
import java.util.Map;

public class SmartEnemy extends GameObject {

    private Handler handler;

    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        //speed and movement
        //velX = 5;
        //velY = 5;

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tmpObject = handler.object.get(i);
            if (tmpObject.getId() == ID.Player){
                player = tmpObject;
            }
        }
    }

    private int calculateDistance(int distanceX, int distanceY){
        //calculate location of the player

        return (int) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

    }

    @Override
    public void tick() {
        //code that happens every game tick
        int deffX = x - player.getX() - 8;
        int deffY = y - player.getY() - 8;

        int deff = calculateDistance(deffX, deffY);

        velX = Math.round((-1.0f/deff) * deffX)  ;
        velY = Math.round((-1.0f/deff) * deffY)  ;

        //moving objects
        x += velX;
        y += velY;

        //bounce object of the wall
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        //creating objects trail (0.05f trail life - lower shorter)
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        //render enemy
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);

    }

    //return rectangle for collision detection
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
