package com.dodgeGame.main;

import java.awt.*;

public class Explosion extends GameObject {

    private float alpha = 1;
    private  float life = 0.05f;
    private Handler handler;
    Texture tex = Game.getInstance();

    Animation animation;
    private int endAnimation = 0;

    public Explosion(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        this.animation = new Animation(5, tex.explosion);
    }

    @Override
    public void tick() {

        endAnimation = animation.runAnimationOnce();

        if (endAnimation == 1){
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        /*
        Graphics2D g2d = (Graphics2D)g;
        g2d.setComposite(makeTransperent(alpha));
        g.setColor(Color.orange);
        g.fillRect(x, y, 64, 64);
        g2d.setComposite(makeTransperent(1));
        */
        animation.drawAnimation(g, x, y, 64, 64);
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
