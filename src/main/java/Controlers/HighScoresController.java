package Controlers;

import Services.HighestRecordsManager;
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
    private HighestRecordsManager recordsManager = HighestRecordsManager.INSTANCE;

    @FXML
    public void onButtonClick(ActionEvent event){
        try {
            if (event.getSource().equals(backButton)){
                showMenu();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void showMenu() throws IOException {
        prepareScene(backButton, "Menu.fxml");
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        highScoresNames.setText(recordsManager.prepareListNamesForLabel());
        highScoresValues.setText(recordsManager.prepareListValuesForLabel());
    }
}
