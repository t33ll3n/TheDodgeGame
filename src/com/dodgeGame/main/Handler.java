package com.dodgeGame.main;

import java.awt.*;
import java.util.LinkedList;


public class Handler {

    //list of all the objects in the game
    LinkedList<GameObject> object = new LinkedList<>();


    public void tick(){

        //every game tick loops through all the objects
        for(int i = 0; i < object.size(); i++){
            GameObject tmpObject = object.get(i);

            //and calls there tick method e.g. to move them
            tmpObject.tick();
        }
    }

    public void render(Graphics g){

        //render all the objects in the game
        for(int i = 0; i < object.size(); i++){
            GameObject tmpObject = object.get(i);

            //calls there render method
            tmpObject.render(g);

        }
    }

    //method to add objects in the game
    public void addObject(GameObject object){
        this.object.add(object);
    }

    //method to remove objects from the game
    public void removeObject(GameObject object){
        this.object.remove(object);
    }

}
