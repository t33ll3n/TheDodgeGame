package com.dodgeGame.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private Color color = Color.green;
    private Rectangle selectedRect = new Rectangle(290, 90, 70, 70);

    public Menu(Game game, Handler handler, HUD hud){
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void mouseClicked(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        if (game.gameState == Game.STATE.Menu){
            if (mouseOver(mx, my, 200, 90, 250, 100)){
                game.gameState = Game.STATE.Game;

                //Adds a player to the game
                handler.addObject(new Player(game.WIDTH/2 - 32, game.HEIGHT/2 - 32, ID.Player, handler, color));
                for (int i = 0; i < 2; i++) {
                    //Add to enemies for the start
                    handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 50) + 25, r.nextInt(game.HEIGHT - 50) + 25, ID.BasicEnemy, handler));
                }
                //Add first coin in the game
                handler.addObject(new Coin(r.nextInt(game.WIDTH - 50) + 25, r.nextInt(game.HEIGHT - 50) + 25, ID.Coin, handler));

            } else if(mouseOver(mx, my, 200, 210, 250, 100)){
                game.gameState = Game.STATE.Options;

            }
            else if (mouseOver(mx, my, 200, 330, 250, 100)){
                System.exit(1);
            }
        } else if (game.gameState == Game.STATE.End){
            if (mouseOver(mx, my, 225, 200, 200, 70)){
                game.gameState = Game.STATE.Game;

                hud.health = 100;
                hud.setScore(0);
                hud.setLevel(1);
                handler.addObject(new Player(game.WIDTH/2 - 32, game.HEIGHT/2 - 32, ID.Player, handler, color));
                for (int i = 0; i < 2; i++) {
                    handler.addObject(new BasicEnemy(r.nextInt(game.WIDTH - 50) + 25, r.nextInt(game.HEIGHT - 50) + 25, ID.BasicEnemy, handler));
                }

            }
            if (mouseOver(mx, my, 225, 300, 200, 70)){
                System.exit(1);
            }
        } else if (game.gameState == Game.STATE.Options){
            if (mouseOver(mx, my, 225, 300, 200, 70)){
                game.gameState = Game.STATE.Menu;
            } else if (mouseOver(mx, my, 200, 100, 50, 50)){
                color = Color.blue;
                selectedRect = new Rectangle(190, 90, 70, 70);
            } else if (mouseOver(mx, my, 300, 100, 50, 50)){
                color = Color.green;
                selectedRect = new Rectangle(290, 90, 70, 70);
            } else if (mouseOver(mx, my, 400, 100, 50, 50)){
                color = Color.white;
                selectedRect = new Rectangle(390, 90, 70, 70);
            }
        } else if (game.gameState == Game.gameState.Shop){
            //shop mouse and keyboard activities

            //mouse over bomb
            if (mouseOver(mx, my, 60, 150, 410, 40)){
                if (hud.getCoins() >= 5){
                    hud.setCoins(hud.getCoins() - 5);
                    hud.setBombs(hud.getBombs() + 1);
                }
                //System.out.println("Bombs: " + hud.getBombs());
            }
            //mouse over shield
            else if (mouseOver(mx, my, 60, 200, 465, 40)){
                if (hud.getCoins() >= 10) {
                    hud.setCoins(hud.getCoins() - 10);
                    hud.setShield(100);
                }
            }
        }
    }


    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        if (mx > x && mx < x + width){
            if (my > y && my < y + height){
                return true;
            } else return false;
        } else return false;
    }


    public void tick(){
        if (game.gameState == Game.STATE.End) {
            for (int i = 0; i < handler.object.size(); i++) {
                GameObject tmpObject = handler.object.get(i);
                handler.removeObject(tmpObject);
            }
        }
    }

    public void render(Graphics g){
        Font fnt = new Font("lilliPut Steps", 1, 60);
        Font fntsmall = new Font("lilliPut Steps", 1, 30);
        g.setFont(fntsmall);
        g.setColor(Color.white);

        if (game.gameState == Game.STATE.Menu) {
            g.setColor(Color.black);
            g.fillRect(0,0, game.getWidth(), game.getHeight());

            g.setColor(Color.white);
            g.drawString("Dodge Game", 150, 60);

            g.fillRect(200, 90, 250, 100);
            g.fillRect(200, 210, 250, 100);
            g.fillRect(200, 330, 250, 100);

            g.setColor(Color.black);
            g.drawString("Play", 260, 160);
            g.drawString("Options", 210, 280);
            g.drawString("Quit", 260, 400);

        } else if (game.gameState == Game.STATE.End){
            g.setColor(Color.black);
            g.fillRect(0,0, game.getWidth(), game.getHeight());
            g.setColor(Color.white);
            g.drawString("Game Over", 160, 90);

            Font fnt2 = new Font("lilliPut Steps", 1, 35);
            g.setFont(fnt2);

            g.drawString("Score: " + hud.getScore(), 165, 150);
            g.fillRect(185, 200, 285, 70);
            g.fillRect(225, 300, 200, 70);

            g.setColor(Color.black);
            g.drawString("Play Again", 190, 250);
            g.drawString("Quit", 270, 350);

        } else if (game.gameState == Game.STATE.Options){
            g.setColor(Color.black);
            g.fillRect(0,0, game.getWidth(), game.getHeight());
            g.setColor(Color.blue);
            g.fillRect(200, 100, 50, 50);
            g.setColor(Color.green);
            g.fillRect(300, 100, 50, 50);
            g.setColor(Color.white);
            g.fillRect(400, 100, 50, 50);

            g.setColor(Color.white);
            g.fillRect(225, 300, 200, 70);
            g.setColor(Color.black);
            g.drawString("Back", 255, 355);

            Graphics2D g2d = (Graphics2D) g;
            g.setColor(Color.white);
            g2d.draw(new Rectangle(selectedRect));

        } else if (game.gameState == Game.gameState.Shop){
            g.setFont(new Font("lilliPut Steps", 1, 35));
            g.setColor(Color.black);
            //Title
            g.fillRect(0,0, game.getWidth(), game.getHeight());
            g.setColor(Color.WHITE);
            g.drawString("Shop", 240, 60);
            g.setFont(new Font("lilliPut Steps", 1, 25));
            //Coins
            //g.setFont(fntsmall);
            g.drawString("Coins: " + HUD.coins , 75, 100);
            //Bombs
            g.setColor(Color.white);
            g.drawRect(60, 150, 410, 40);
            //g.setColor(Color.black);
            g.drawString("Bomb - Cost: 5 coins", 75, 180);
            //Shield
            g.setColor(Color.white);
            g.drawRect( 60, 200, 465, 40);
            //g.setColor(Color.black);
            g.drawString("Shield - Cost: 10 coins", 75, 230);
        }
    }

}
