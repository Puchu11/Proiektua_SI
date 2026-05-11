package Eredua;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.io.File;


public class AudioKudeatzailea {
    private static AudioKudeatzailea nireAudioKudeatzailea;
    private Clip fondoMusika;
    private boolean Muted=false;
    private AudioKudeatzailea() {} 

    public static AudioKudeatzailea getAudioKudeatzailea() {
        if (nireAudioKudeatzailea == null) {
            nireAudioKudeatzailea = new AudioKudeatzailea();
        }
        return nireAudioKudeatzailea;
    }

    public void musikaErreproduzitu(String bidea) {
        try {
        	musikaGelditu();
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
        if (fondoMusika != null) {
        	if (fondoMusika.isRunning()) {
        		fondoMusika.stop();
        	}
        	fondoMusika.close();
        	fondoMusika = null; 
        }
    }
    public void bolumenaJaitsi(float dB) {
        try {
            if (fondoMusika != null) {
                FloatControl gainControl = (FloatControl) fondoMusika.getControl(FloatControl.Type.MASTER_GAIN);
                float newValue = gainControl.getValue() - dB;
                
                float min = gainControl.getMinimum();
                float max = gainControl.getMaximum();
                newValue = Math.max(min, Math.min(max, newValue));
                
                gainControl.setValue(newValue);
            }
        } catch (Exception e) {
            System.out.println("Errorea bolumena aldatzean: " + e.getMessage());
        }
    }
    
    public void musikaMuteatu(boolean muteatu) {
    	this.Muted=!this.Muted;
    	try {
    		if(fondoMusika != null) {
    			BooleanControl muteControl= (BooleanControl) fondoMusika.getControl(BooleanControl.Type.MUTE);
    			muteControl.setValue(muteatu);
    		}
    	}catch (Exception e) {
    		System.out.println("Errorea muteatzen: " + e.getMessage());
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