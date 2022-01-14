package Werkzeug;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class Lautsprecher {
    public static void abspielen(String soundName) {
        abspielen(soundName, 0);
    }

    public static void abspielen(String soundName, int lautStärke) {
        try {
            String path = System.getProperty("user.dir") + "/assets/sounds/" + soundName + ".wav";
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new URL("file://" + path));

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(lautStärke);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
