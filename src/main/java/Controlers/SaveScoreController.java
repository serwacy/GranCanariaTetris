package Controlers;

import Services.HighestRecordsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controlers.ControllerManager.*;

public class SaveScoreController extends Controller implements Initializable {
    @FXML
    private Label finalScoreLabel;

    @FXML
    private TextField nicknameField;

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

    public String getNickname(){
        return nicknameField.getText();
    }

    public int getScore(){
        return Integer.parseInt(finalScoreLabel.getText());
    }


    @FXML
    public void onButtonClick(ActionEvent event) {
        try {
            if (event.getSource().equals(saveButton)) {
                saveNewScore();
                showMenu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNewScore(){
        HighestRecordsManager recordsManager = HighestRecordsManager.INSTANCE;
        final String nickname = getNickname();
        final int score = getScore();
        recordsManager.addScore(nickname, score);
        recordsManager.saveScoresToFile();
    }

    private void showMenu() throws IOException {
        prepareScene(saveButton, "Menu.fxml");
    }
}