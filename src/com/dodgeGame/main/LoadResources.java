package com.dodgeGame.main;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;



public class LoadResources {

    public LoadResources(){
        LoadFonts();
    }

    public void LoadFonts(){
        // register font to graphics environment
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		System.out.println( "fonts " + getClass().getResource("/fonts/lilliput_steps.ttf"));
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/fonts/lilliput_steps.ttf")));
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
