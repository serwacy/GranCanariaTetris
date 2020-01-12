package Controlers;

import Services.Music.MusicManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends Controller implements Initializable {
    @FXML
    private Button playButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Button highScoresButton;
    @FXML
    private Button soundButton;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        Platform.runLater(()-> MusicManager.INSTANCE.bindAudioButtonImage(soundButton));
    }

    @FXML
    public void onButtonClick(ActionEvent event) {
        try {
            if (event.getSource().equals(playButton)) {
                prepareScene(playButton, "Play.fxml");
            }
            if (event.getSource().equals(creditsButton)) {
                prepareScene(creditsButton, "Credits.fxml");
            }
            if (event.getSource().equals(highScoresButton)) {
                prepareScene(highScoresButton, "HighScores.fxml");
            }
            if (event.getSource().equals(exitButton)) {
                Platform.exit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
