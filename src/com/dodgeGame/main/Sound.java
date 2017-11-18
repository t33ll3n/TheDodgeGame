package com.dodgeGame.main;

import javax.print.DocFlavor;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    private Mixer mixer;
    private static Clip clip;

    public Sound() {
        Mixer.Info[] mixInfos = AudioSystem.getMixerInfo();

        mixer = AudioSystem.getMixer(mixInfos[0]);

        DataLine.Info dataInfo = new DataLine.Info(Clip.class, null);
        try {
            clip = (Clip) mixer.getLine(dataInfo);
        } catch (LineUnavailableException lue) {
            lue.printStackTrace();
        }

        try {
            //URL soundURL = Sound.class.getResource("Voice_Over_Under.wav");
            URL soundURL = this.getClass().getResource("Voice_Over_Under.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip.open(audioStream);
        } catch (LineUnavailableException lue) {
            lue.printStackTrace();
        } catch (UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        } catch (IOException ioe) {
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




