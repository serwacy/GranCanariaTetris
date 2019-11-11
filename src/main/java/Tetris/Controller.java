package Tetris;

import Services.Score;
import Services.ScoreCounter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Controller{

    @FXML
    private Button playButton;
    @FXML
    private Button creditsButton;
    @FXML
    private Button highScoresButton;
    @FXML
    private Button backButton;
    @FXML
    private Button stopButton;
    @FXML
    private Label highScores = new Label();


    // ON BUTTON CLICK METHOD TO BE USED IN ANY CASE OF BUTTON EVENT IN OUR PROGRAM
    public void onButtonClick(ActionEvent event){
        try {
            if (event.getSource().equals(playButton)){
                showGame();
            }
            if (event.getSource().equals(creditsButton)){
                showCredits();
            }
            if (event.getSource().equals(highScoresButton)){
                showScores();
            }
            if (event.getSource().equals(backButton)){
                showMenu();
            }
            if (event.getSource().equals(stopButton)){
                fishGame();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // BELOW ARE THE METHODS RESPONSIBLE FOR SCENE CHANGING - I KNOW IT IS CODE REPETITION - TO BE FIXED LATER
    public void showGame() throws Exception {
        Stage stage = (Stage) creditsButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Play.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
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
    public void fishGame() throws Exception {
        Stage stage = (Stage) stopButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        stage.setScene(new Scene(root,550,850));
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    private void initialize(){
        highScores.setText(ScoreCounter.getScores());
    }



}
