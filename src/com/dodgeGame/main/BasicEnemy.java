package com.dodgeGame.main;

import java.awt.*;

public class BasicEnemy extends GameObject {

    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        //speed and movement
        velX = 5;
        velY = 5;
    }

    @Override
    public void tick() {
        //code that happens every game tick

        //moving objects
        x += velX;
        y += velY;

        //bounce object of the wall
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        //creating objects trail (0.05f trail life - lower shorter)
        handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 16, 16, 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        //render enemy
        g.setColor(Color.blue);
        g.fillRect(x, y, 16, 16);

    }

    //return rectangle for collision detection
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
