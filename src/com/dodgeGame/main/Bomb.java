package com.dodgeGame.main;

import javafx.scene.shape.Circle;

import java.awt.*;

public class Bomb extends GameObject{

    Handler handler;
    boolean explosion = false;
    public Bomb(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        collision();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(x, y, 16, 16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, 16, 16);
    }

    public Rectangle explosion(){
        return new Rectangle(this.x - 24, this.y - 24, 64, 64);
    }

    private void collision(){

        for (int i = 0; i < handler.object.size(); i++){

            GameObject tmpObject = handler.object.get(i);

            if (tmpObject.getId() == ID.BasicEnemy || tmpObject.getId() == ID.FastEnemy || tmpObject.getId() == ID.SmartEnemy) {
                if (getBounds().intersects(tmpObject.getBounds())){
                    //collision code

                    handler.addObject(new Explosion(this.x - 24, this.y -24, ID.Explosion, this.handler));

                    for (int j = 0; j < handler.object.size(); j++){
                        //checks if enemies are in range
                        GameObject tmpObject2 = handler.object.get(j);

                        if (tmpObject2.getId() == ID.BasicEnemy || tmpObject2.getId() == ID.FastEnemy || tmpObject2.getId() == ID.SmartEnemy){
                            //if they are, we destroy them
                            if (explosion().intersects(tmpObject2.getBounds())){
                                handler.removeObject(tmpObject2);
                            }
                        }

                    }

                    handler.removeObject(this);
                }
            }
        }
    }
}
