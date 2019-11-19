package Controlers;

import Services.HighestRecordsManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HighScoresController implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private Label highScores;

    @FXML
    public void onButtonClick(ActionEvent event){
        try {
            if (event.getSource().equals(backButton)){
                showMenu();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void showMenu() throws Exception {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        highScores.setText(HighestRecordsManager.getScores());
    }
}
