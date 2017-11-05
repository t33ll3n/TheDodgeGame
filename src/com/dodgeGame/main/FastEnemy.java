package com.dodgeGame.main;

import java.awt.*;

public class FastEnemy extends GameObject {

    private Handler handler;

    public FastEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        //speed and direction of movement
        velX = 2;
        velY = 8;
    }

    @Override
    public void tick() {
        //code that happens every game tick

        //moving object
        x += velX;
        y += velY;

        //bounce objects of the walls
        if (y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16) velX *= -1;


        //create objects trail (0.05f trail life - lower shorter)
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.05f, handler));
    }

    @Override
    public void render(Graphics g) {
        //render enemy
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);

    }

    //return rectangle same size as object for collision detection
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
