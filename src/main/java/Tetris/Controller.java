package Tetris;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



public class Controller {

    @FXML
    private Button creditsButton;

    public void onButtonClick(ActionEvent event){
        try {
            if (event.getSource().equals(creditsButton)){
                showCredits();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showCredits() throws Exception {
        Stage stage = (Stage) creditsButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Credits.fxml"));
        stage.setScene(new Scene(root,600,800));
        stage.show();
    }
}
