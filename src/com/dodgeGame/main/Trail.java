package com.dodgeGame.main;

import java.awt.*;


public class Trail extends GameObject {

    private float alpha = 1;
    private  float life;

    private Handler handler;
    private Color color;

    private int width, height;

    public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler){
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }


    @Override
    public void tick() {
        //code that happens every game tick

        //lowers trail alpha till it is 100% transparent, then removes it
        if (alpha >= life){
            alpha -= life - 0.001f;
        } else handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) { // render trails
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(makeTransperent(alpha));
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g2d.setComposite(makeTransperent(1));

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    private AlphaComposite makeTransperent(float alpha){ // have no idea!
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }
}
