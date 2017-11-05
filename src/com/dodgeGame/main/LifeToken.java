package com.dodgeGame.main;

import java.awt.*;


public class LifeToken extends GameObject {

    Handler handler;

    public LifeToken(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        Font fnt1 = new Font("arial", 1, 55);
        g.setFont(fnt1);
        g.setColor(Color.white);
        g.fillRect(x, y, 25, 25);
        g.setColor(Color.red);
        g.drawString("+", this.x - 3, this.y + 32);
    }

    @Override
    public Rectangle getBounds() { return new Rectangle(x, y, 20, 20); }
}
