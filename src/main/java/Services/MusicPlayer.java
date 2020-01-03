package Services;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {

    public void play(){

        try {
            InputStream inputStream = this.getClass().getResourceAsStream("/sounds/korobeinikiSpanish.wav");
//            File file = new File(this.getClass().getResource("/sounds/korobeinikiSpanish.wav").getFile());
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
