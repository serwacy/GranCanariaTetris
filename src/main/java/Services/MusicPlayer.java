package Services;

import javax.sound.sampled.*;
import java.io.*;

public class MusicPlayer {

    public void play(){
        try (InputStream inputStream = getClass().getResourceAsStream("/sounds/korobeinikiSpanish.wav")) {
            InputStream bufferedInputStream = new BufferedInputStream(inputStream);
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream)) {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

//        try {
//            InputStream inputStream = this.getClass().getResourceAsStream("/sounds/korobeinikiSpanish.wav");
////            File file = new File(this.getClass().getResource("/sounds/korobeinikiSpanish.wav").getFile());
//            Clip clip = AudioSystem.getClip();
//            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
//            clip.open(audioInputStream);
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
//            e.printStackTrace();
//        }
    }
}
