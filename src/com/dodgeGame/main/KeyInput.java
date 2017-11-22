package com.dodgeGame.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Tilen on 24.4.2016.
 */
public class KeyInput extends KeyAdapter {

    private Handler handler;
    private Game game;
    private HUD hud;
    private boolean[] keyDown = new boolean[4]; //boolean array prevents sticky keys

    public KeyInput(Handler handler, Game game, HUD hud){
        this.handler = handler;
        this.game = game;
        this.hud = hud;

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
                    if (hud.getBombs() >= 1) {
                        hud.setBombs(hud.getBombs() - 1);
                        handler.addObject(new Bomb(tmpObject.getX() + 8, tmpObject.getY() + 8, ID.Bomb, handler));
                    }
                    //System.out.println(hud.getBombs());
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


        else if (key == KeyEvent.VK_ESCAPE){
            if (game.gameState == Game.STATE.Game) {
                game.gameState = Game.STATE.Pause;
            }
            int exitDialog = JOptionPane.YES_NO_OPTION;
            int exitDialogResult = JOptionPane.showConfirmDialog(null, "Would you really like to exit the game?\n The progress will be lost!", "Warning", exitDialog);
            if (exitDialogResult == JOptionPane.YES_OPTION) {
                System.exit(0);
            } else {
                if (game.gameState == Game.STATE.Pause)
                game.gameState = Game.STATE.Game;
            }
        }

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
