package com.dodgeGame.main;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoadResources {

    public LoadResources(){
        LoadFonts();
    }

    public void LoadFonts(){
        try {
            // register font to graphics environment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //System.out.println( "fonts " + this.getClass().getResource("\\fonts\\lilliput_steps.ttf"));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File((".\\resources\\com\\dodgeGame\\main\\fonts\\lilliput_steps.ttf"))));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}
