package com.dodgeGame.main;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Sound {

    private Mixer mixer;
    private static Clip clip;

    public Sound() {
        Mixer.Info[] mixInfo = AudioSystem.getMixerInfo();

        mixer = AudioSystem.getMixer(mixInfo[0]);

        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        try {
            clip = (Clip) mixer.getLine(dataInfo);
        } catch (LineUnavailableException lue) {
            lue.printStackTrace();
        }

        try {
            //URL soundURL = ".\\resources\\com\\dodgeGame\\main\\sound\\Voice_Over_Under.wav";
            //URL soundStream = this.getClass().getResource("\\sounds\\Voice_Over_Under.wav");
            URL soundStream = new URL(".\\resources\\sounds\\Voice_Over_Under.wav");
            System.out.println("Sound path " + soundStream);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundStream);
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




