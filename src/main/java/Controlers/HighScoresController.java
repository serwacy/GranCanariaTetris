package Controlers;

import Services.Records.RecordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HighScoresController extends Controller implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Label highScoresNames;
    @FXML
    private Label highScoresValues;
    private RecordManager recordsManager = RecordManager.INSTANCE;

    @FXML
    public void onButtonClick(ActionEvent event) {
        try {
            if (event.getSource().equals(backButton)) {
                prepareScene(backButton, "Menu.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        highScoresNames.setText(recordsManager.prepareListNamesForLabel());
        highScoresValues.setText(recordsManager.prepareListValuesForLabel());
    }
}
