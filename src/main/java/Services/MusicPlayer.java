package Services;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public enum  MusicPlayer {
    INSTANCE;

    private final Image mute = new Image(getClass().getResourceAsStream("/images/mute.png"));
    private final Image play = new Image(getClass().getResourceAsStream("/images/audio.png"));

    private Clip clip;
    private boolean isPlaying = true;

    public void bindAudioButtonImage(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (isPlaying()) {
                    stop();
                    button.setGraphic(new ImageView(mute));
                    if (button.getText().isEmpty()){return;}
                    button.setText(" Music off");
                }else {
                    play();
                    button.setGraphic(new ImageView(play));
                    if (button.getText().isEmpty()){return;}
                    button.setText(" Music  on");
                }
            }
        });
    }

    public void play(){
        isPlaying = true;
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
    private void stop(){
        isPlaying = false;
        clip.stop();
    }

    private boolean isPlaying() {
        return isPlaying;
    }
}
