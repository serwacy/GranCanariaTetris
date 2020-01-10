package Services.Music;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public enum MusicManager {
    INSTANCE;

    private final Image mute = new Image(getClass().getResourceAsStream("/images/mute.png"));
    private final Image play = new Image(getClass().getResourceAsStream("/images/audio.png"));

    private MusicPlayer musicPlayer = new MusicPlayer();

    private Boolean isPlaying = true;

    public void initMusic(){
        musicPlayer.play();
    }

    public void bindAudioButtonImage(final Button button) {
        updateMusicButton(button);
        button.setOnAction(event -> {
            switchMusicStatus(button);
        });
    }

    private void updateMusicButton(Button button) {
        if (isPlaying) {
            turnMusicButtonOn(button);
        } else {
            turnMusicButtonOff(button);
        }
    }

    private void switchMusicStatus(final Button button) {
        if (isPlaying) {
            musicPlayer.stop();
            turnMusicButtonOff(button);
        } else {
            musicPlayer.play();
            turnMusicButtonOn(button);
        }
    }

    private void turnMusicButtonOff(Button button) {
        button.setGraphic(new ImageView(mute));
        if (button.getText().isEmpty()) {
            return;
        }
        button.setText(" Music off");
    }

    private void turnMusicButtonOn(Button button) {
        button.setGraphic(new ImageView(play));
        if (button.getText().isEmpty()) {
            return;
        }
        button.setText(" Music  on");
    }

    public void setPlaying(final Boolean playing) {
        isPlaying = playing;
    }
}
