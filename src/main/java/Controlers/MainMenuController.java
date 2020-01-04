package Controlers;

import Services.MusicPlayer;
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
        MusicPlayer.getInstance().bindAudioButtonImage(soundButton);
    }

    @FXML
    public void onButtonClick(ActionEvent event) {
        try {
            if (event.getSource().equals(playButton)) {
                showGame();
            }
            if (event.getSource().equals(creditsButton)) {
                showCredits();
            }
            if (event.getSource().equals(highScoresButton)) {
                showScores();
            }
            if (event.getSource().equals(exitButton)) {
                exitProgram();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // BELOW ARE THE METHODS RESPONSIBLE FOR SCENE CHANGING - I KNOW IT IS CODE REPETITION - TO BE FIXED LATER
    private void showGame() throws IOException {
        prepareScene(playButton, "Play.fxml");
    }

    private void showCredits() throws IOException {
        prepareScene(creditsButton, "Credits.fxml");
    }

    private void showScores() throws IOException {
        prepareScene(highScoresButton, "HighScores.fxml");
    }
    private void exitProgram() {
        Platform.exit();
    }
}
