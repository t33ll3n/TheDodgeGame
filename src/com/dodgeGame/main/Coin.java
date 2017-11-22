package com.dodgeGame.main;

import java.awt.*;

public class Coin extends GameObject {
    Handler handler;
    Texture tex = Game.getInstance();
    private Animation coinRotation;

    public Coin(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        coinRotation = new Animation(5, tex.item);
    }
    @Override
    public void tick() {
        coinRotation.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        //g.fillRect(x, y, 16, 16);
        //g.drawImage(tex.item[0], x, y, null);
        coinRotation.drawAnimation(g, x, y, 16, 16);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 16, 16);
    }
}
