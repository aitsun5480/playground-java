package Werkzeug;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Lautsprecher {
    public static void abspielen(String soundName) {
        try {
            String path = System.getProperty("user.dir") + "/assets/sounds/" + soundName + ".wav";
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new URL("file://" + path));

            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
