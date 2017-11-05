package com.dodgeGame.main;

import java.util.Random;

/**
 * Created by Tilen on 25.4.2016.
 */
public class Spawner {

    private Handler handler;
    private HUD hud;
    private Random r = new Random();

    private int scorePlus;

    public Spawner(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
    }

    public void tick(){
        scorePlus = hud.getScore();

        //every x points level increase for 1
        if (scorePlus % 800 == 0){
            hud.setLevel(hud.getLevel() + 1);
            //spawn now enemies
            SpawnEnemys(hud.getLevel());
            scorePlus = 0;
        }
        if (scorePlus % 500 == 0){
            handler.addObject(new Coin(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.Coin, handler));
        }
    }

    public void render(){

    }

    public void SpawnEnemys(int level){ // adds now enemies to the game, depends of the level
        if (level == 2){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.BasicEnemy, handler));
        } else if (level == 3){
            handler.addObject(new LifeToken(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.LifeToken, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.BasicEnemy, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.BasicEnemy, handler));
        } else if (level == 4){
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.FastEnemy, handler));
        } else if (level == 5){
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.BasicEnemy, handler));
            handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 50 ) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.FastEnemy, handler));
            handler.addObject(new LifeToken(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50) + 50, ID.LifeToken, handler));
        } else if (level == 6){
            handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50) + 50, r.nextInt(Game.HEIGHT - 50 ) + 50, ID.SmartEnemy, handler));

        }
    }
}
