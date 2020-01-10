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
        Platform.runLater(()->MusicPlayer.INSTANCE.bindAudioButtonImage(soundButton));
    }

    @FXML
    public void onButtonClick(ActionEvent event) {
        try {
            if (event.getSource().equals(playButton)) {
                showGame(playButton);
            }
            if (event.getSource().equals(creditsButton)) {
                showCredits(creditsButton);
            }
            if (event.getSource().equals(highScoresButton)) {
                showScores(highScoresButton);
            }
            if (event.getSource().equals(exitButton)) {
                exitProgram();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
