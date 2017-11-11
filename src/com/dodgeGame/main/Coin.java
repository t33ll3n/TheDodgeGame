package com.dodgeGame.main;

import java.awt.*;

public class Coin extends GameObject {
    Handler handler;
    Texture tex = Game.getInstance();

    public Coin(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }
    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        //g.fillRect(x, y, 16, 16);
        g.drawImage(tex.item[0], x, y, 32, 32, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 16, 16);
    }
}
