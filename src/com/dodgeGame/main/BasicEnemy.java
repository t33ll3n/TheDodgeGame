package com.dodgeGame.main;

import java.awt.*;

public class BasicEnemy extends GameObject {

    private Handler handler;
    Texture tex = Game.getInstance();

    Animation leftDown, rightUp, leftUp, rightDown;

    public BasicEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        this.leftDown = new Animation(15, tex.basicEnemy[0]);
        this.rightUp = new Animation(15, tex.basicEnemy[1]);
        this.leftUp = new Animation(15, tex.basicEnemy[2]);
        this.rightDown = new Animation(15, tex.basicEnemy[3]);

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
        if (y <= 0 || y >= Game.HEIGHT - 60) velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velX *= -1;

        //creating objects trail (0.05f trail life - lower shorter)
        //handler.addObject(new Trail(x, y, ID.Trail, Color.blue, 16, 16, 0.05f, handler));


        leftDown.runAnimation();
        leftUp.runAnimation();
        rightUp.runAnimation();
        rightDown.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        //render enemy
        //g.setColor(Color.blue);
        //g.fillRect(x, y, 16, 16);



        if (velX < 0 && velY > 0){
            leftDown.drawAnimation(g, x, y, 32, 32);
        }
        else if (velX < 0 && velY < 0){
            leftUp.drawAnimation(g, x, y, 32, 32);
        }
        else if (velX > 0 && velY < 0){
            rightUp.drawAnimation(g, x, y, 32, 32);
        }
        else if (velX > 0 && velY > 0){
            rightDown.drawAnimation(g, x, y, 32, 32);
        }
    }

    //return rectangle for collision detection
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
