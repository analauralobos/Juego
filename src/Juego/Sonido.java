package Juego;

import java.net.URL;
import javax.sound.sampled.*;

public class Sonido {
    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl fc;
    int volumeScale = 2;
    float volume;

    public Sonido() {
        soundURL[0] = getClass().getResource("sonidos/original.wav");
        soundURL[1] = getClass().getResource("sonidos/explosion.wav");
        soundURL[2] = getClass().getResource("sonidos/gameover.wav");
        soundURL[3] = getClass().getResource("sonidos/disparo.wav");
        soundURL[4] = getClass().getResource("sonidos/powerup.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void checkVolume() {
        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        fc.setValue(volume);
    }}


/*
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public enum Sonido {

    MUSICA(BatallaMidway1943.juego.MUSICAFONDO),
    BONUS("powerup.WAV"),
    GAME_OVER("gameover.wav"),
    WINGAME("wingame.wav"),
    DISPARO("disparo.wav"),
    EXPLOSION("explosion.wav");
    
    private String soundFile;
    private Clip clip;
    public static Volume volume;
    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    Sonido(String wav) {
        this.soundFile = wav;
        try {
            URL url = this.getClass().getClassLoader().getResource("sonidos/" + wav);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(float numero) {
        FloatControl f = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        f.setValue(numero);
        if (volume != Volume.MUTE) {
            if (!clip.isRunning()) {
                clip.setFramePosition(0);
                clip.start();
            } else {
                clip.setFramePosition(0);
                clip.start();
            }

        }
    }

    public void play2(float numero) {
        FloatControl f = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        f.setValue(numero);
        if (volume != Volume.MUTE) {
            if (!clip.isRunning()) {
                clip.setFramePosition(0);
                clip.start();
            }
        }
    }

    public void stop() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning()) {
                clip.setFramePosition(0);
                clip.stop();
            }

        }
    }

    public void loop(float numero) {
        FloatControl f = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        f.setValue(numero);
        if (volume != Volume.MUTE) {
            if (!clip.isRunning()) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

        }
    }

    static void init() {
       for (Sonido sonido : values()) {
            sonido.initSound();
        }
    }
    
     private void initSound() {
        try {
            URL url = this.getClass().getClassLoader().getResource("sonidos/" + soundFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
*/