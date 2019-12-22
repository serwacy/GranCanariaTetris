package Controlers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class SaveScoreController extends Controller implements Initializable {
    @FXML
    private Label finalScoreLabel;


    public void setScoreLabel(int score) {
        finalScoreLabel.setText(String.format("%04d", score));
    }


    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        PlayController playController = ControllerManager.getPlayController();
        int scoreValue = playController.getScoreValue();
        setScoreLabel(scoreValue);
    }
}
