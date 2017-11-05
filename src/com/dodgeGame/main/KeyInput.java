package com.dodgeGame.main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Tilen on 24.4.2016.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Game game;
    private boolean[] keyDown = new boolean[4]; //boolean array prevents sticky keys

    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
        for (int i = 0; i < keyDown.length; i++){
            keyDown[i] = false;
        }
    }


    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tmpObject = handler.object.get(i);

            if (tmpObject.getId() == ID.Player){
                //key event for player 1
                if(key == KeyEvent.VK_W) { tmpObject.setVelY(-5); keyDown[0] = true; }
                if(key == KeyEvent.VK_S) { tmpObject.setVelY(5); keyDown[1] = true; }
                if(key == KeyEvent.VK_A) { tmpObject.setVelX(-5); keyDown[2] = true; }
                if(key == KeyEvent.VK_D) { tmpObject.setVelX(5); keyDown[3] = true; }

                else if (key == KeyEvent.VK_E){
                    handler.addObject(new Bomb(tmpObject.getX() + 8, tmpObject.getY() + 8, ID.Bomb, handler));
                }
            }

        }
        if (key == KeyEvent.VK_P) {
            if (game.gameState == Game.STATE.Game){
                game.gameState = Game.STATE.Pause;
            } else if (game.gameState == Game.STATE.Pause) {
                game.gameState = Game.STATE.Game;
            }
        }
        else if (key == KeyEvent.VK_B){
            if (game.gameState == Game.gameState.Game){
                game.gameState = Game.gameState.Shop;
            } else if (game.gameState == Game.gameState.Shop){
                game.gameState = Game.gameState.Game;
            }
        }


        else if (key == KeyEvent.VK_ESCAPE) System.exit(0);

    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tmpObject = handler.object.get(i);

            if (tmpObject.getId() == ID.Player){
                //key event for player 1
                if(key == KeyEvent.VK_W) keyDown[0] = false; //tmpObject.setVelY(0);
                if(key == KeyEvent.VK_S) keyDown[1] = false; //tmpObject.setVelY(0);
                if(key == KeyEvent.VK_A) keyDown[2] = false; //tmpObject.setVelX(0);
                if(key == KeyEvent.VK_D) keyDown[3] = false; //tmpObject.setVelX(0);

                //vertical movement
                if (!keyDown[0] && !keyDown[1]) tmpObject.setVelY(0);
                //horizontal movement
                if (!keyDown[2] && !keyDown[3]) tmpObject.setVelX(0);
            }
        }


    }


}
