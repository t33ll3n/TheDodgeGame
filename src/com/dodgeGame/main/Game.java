package com.dodgeGame.main;


import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.nio.Buffer;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 640, HEIGHT = WIDTH  /12 * 9;

    private Thread thread;
    private boolean running = false;

    private Random r = new Random();
    private Handler handler;
    private HUD hud;
    private Spawner spawner;
    private Menu menu;
    private Sound sound;
    private static Texture tex;
    private static LoadResources resources;

    public enum STATE {
        Menu,
        Options,
        Game,
        Pause,
        Shop,
        End
    }

    public static STATE gameState = STATE.Menu;

    public Game(){
        resources = new LoadResources();
        resources.LoadFonts();
        sound = new Sound();
        handler = new Handler();
        hud = new HUD();
        tex = new Texture();
        this.addKeyListener(new KeyInput(handler, this, hud));
        menu = new Menu(this, handler, hud);
        this.addMouseListener(menu);
        spawner = new Spawner(handler, hud);
        new Window(WIDTH, HEIGHT, "Dodge Game", this);
    }

    public synchronized void start(){
        thread = new Thread(this); //start now thread
        thread.start();
        sound.start();
        running = true;
    }

    public synchronized void stop(){
        try {
            thread.join(); //kill the thread
            running = false;
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){ // game loop
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                updates++;
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private void tick(){ //Game ticks
        //what happened every tick in the game
//        gameState = STATE.Shop;

        if (gameState == STATE.Game) {
            handler.tick();
            hud.tick();
            spawner.tick();
            sound.start();
        } else if (gameState == STATE.Menu || gameState == STATE.End){
            menu.tick();
        } else if (gameState == STATE.Pause){
            //Do nothing. Dont change the gameobjects
            sound.stop();
        }
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy(); //creating game buffers
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();


        g.setColor(Color.black); //window background color
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g); //Game object rendering

        if (gameState == STATE.Game) {
            hud.render(g);
        } else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Options || gameState == STATE.Shop){
            menu.render(g);
        } else if (gameState == STATE.Pause) {
            //Do nothing
        }

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max){
        //don't allow value go over max or min value
        if (var >= max){
            return max;
        } else if (var <= min){
            return min;
        } else {
            return var;
        }
    }

    public static Texture getInstance(){
        return tex;
    }

    public static void main(String[] args){
        new Game(); // starts new game
    }

}
