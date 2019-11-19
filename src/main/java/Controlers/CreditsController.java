package Controlers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreditsController {
    @FXML
    private Button backButton;

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
}
