package com.dodgeGame.main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Sound {

    private Mixer mixer;
    private static Clip clip;

    public Sound() {
        
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException lue) {
            lue.printStackTrace();
        }

        try {
            URL soundURL = this.getClass().getResource("/sounds/Voice_Over_Under.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip.open(audioStream);
        } catch (LineUnavailableException lue) {
            System.out.println("LineUnavailableException");
            lue.printStackTrace();
        } catch (UnsupportedAudioFileException uafe) {
            System.out.println("UnsupportedAudioFileException");
            uafe.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("IOException");
            ioe.printStackTrace();
        }
    }

    public void start(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}




