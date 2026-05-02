package Eredua;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class AudioKudeatzailea {
    private static AudioKudeatzailea nireAudioKudeatzailea;
    private Clip fondoMusika;

    private AudioKudeatzailea() {} 

    public static AudioKudeatzailea getAudioKudeatzailea() {
        if (nireAudioKudeatzailea == null) {
            nireAudioKudeatzailea = new AudioKudeatzailea();
        }
        return nireAudioKudeatzailea;
    }

    public void musikaErreproduzitu(String bidea) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(bidea));
            fondoMusika = AudioSystem.getClip();
            fondoMusika.open(audioStream);
            fondoMusika.loop(Clip.LOOP_CONTINUOUSLY);
            fondoMusika.start();
        } catch (Exception e) {
            System.out.println("Errorea musika kargatzean: " + e.getMessage());
        }
    }

    public void musikaGelditu() {
        if (fondoMusika != null && fondoMusika.isRunning()) {
            fondoMusika.stop();
        }
    }

    public void soinuaErreproduzitu(String bidea) {
        new Thread(() -> {
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(bidea));
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
            } catch (Exception e) {
                System.out.println("Errorea soinua kargatzean: " + e.getMessage());
            }
        }).start();
    }
}