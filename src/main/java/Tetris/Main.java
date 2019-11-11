package Tetris;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        primaryStage.setScene(new Scene(root,550,850));
        primaryStage.setTitle("Gran Canaria Tetris - version 1.0");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        launch(args);
    }
}
