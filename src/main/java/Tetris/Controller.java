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
    @FXML
    private Button highScoresButton;
    @FXML
    private Button backButton;

    // ON BUTTON CLICK METHOD TO BE USED IN ANY CASE OF BUTTON EVENT IN OUR PROGRAM
    public void onButtonClick(ActionEvent event){
        try {
            if (event.getSource().equals(creditsButton)){
                showCredits();
            }
            if (event.getSource().equals(highScoresButton)){
                showScores();
            }
            if (event.getSource().equals(backButton)){
                showMenu();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // BELOW ARE THE METHODS RESPONSIBLE FOR SCENE CHANGING - I KNOW IT IS CODE REPETITION - TO BE FIXED LATER
    public void showCredits() throws Exception {
        Stage stage = (Stage) creditsButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Credits.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
    public void showScores() throws Exception {
        Stage stage = (Stage) highScoresButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("HighScores.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
    public void showMenu() throws Exception {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
}
