package Controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controlers.ControllerManager.*;

public class SaveScoreController extends Controller implements Initializable {
    @FXML
    private Label finalScoreLabel;

    @FXML
    private Button saveButton;


    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        PlayController playController = getPlayController();
        int scoreValue = playController.getScoreValue();
        setScoreLabel(scoreValue);
    }

    public void setScoreLabel(int score) {
        finalScoreLabel.setText(String.format("%04d", score));
    }

    @FXML
    public void onButtonClick(ActionEvent event) {
        try {
            if (event.getSource().equals(saveButton)) {
                showMenu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMenu() throws IOException {
        prepareScene(saveButton, "Menu.fxml");
    }
}
