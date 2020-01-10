package Services.Music;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MusicPlayer {

    private Clip clip;

    public void stop() {
        MusicManager.INSTANCE.setPlaying(false);
        clip.stop();
    }

    public void play() {
        MusicManager.INSTANCE.setPlaying(true);
        try (InputStream inputStream = getClass().getResourceAsStream("/sounds/korobeinikiSpanish.wav")) {
            InputStream bufferedInputStream = new BufferedInputStream(inputStream);
            try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream)) {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
