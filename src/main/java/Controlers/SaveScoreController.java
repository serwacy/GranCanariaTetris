package Controlers;

import Services.Records.RecordManager;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SaveScoreController extends Controller implements Initializable {
    @FXML
    private Label finalScoreLabel;

    @FXML
    private TextField nicknameField;

    @FXML
    private Button saveButton;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        PlayController playController = ControllerManager.getPlayController();
        int scoreValue = playController.getScoreValue();
        setScoreLabel(scoreValue);
        validateNicknameLength();
    }

    private void setScoreLabel(int score) {
        finalScoreLabel.setText(String.format("%04d", score));
    }

    private void validateNicknameLength() {
        saveButton.disableProperty().bind(Bindings.createBooleanBinding(() ->
                nicknameField.getText().trim().length() < 3 ||
                        nicknameField.getText().length() > 15, nicknameField.textProperty()));
    }

    @FXML
    public void onButtonClick(ActionEvent event) {
        try {
            if (event.getSource().equals(saveButton)) {
                saveNewScore();
                prepareScene(saveButton, "Menu.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNewScore() {
        final String nickname = nicknameField.getText();
        final int score = Integer.parseInt(finalScoreLabel.getText());
        saveNewScore(nickname, score);
    }

    private void saveNewScore(final String nickname, final int score) {
        RecordManager recordsManager = RecordManager.INSTANCE;
        recordsManager.saveScore(nickname, score);
    }
}
