package Services;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    public void play(){

        try {
            File file = new File("C:\\Users\\bartm\\OneDrive\\Documents\\IntelijiProjects\\GranCanariaTetris\\src\\main\\resources\\sounds\\korobeinikiSpanish.wav");
            Clip clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
