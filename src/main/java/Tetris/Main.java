package Tetris;

import Services.MusicPlayer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.InputStream;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(this.getClass().getResource("/fonts/ARCHRISTY.ttf").toExternalForm(), 10);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Menu.fxml"));
        primaryStage.setScene(new Scene(root,550,850));
        primaryStage.setTitle("Gran Canaria Tetris - version 1.0");

        MusicPlayer.getInstance().play();

        InputStream inputStream = this.getClass().getResourceAsStream("/images/horse_icon.jpg");
        primaryStage.getIcons().add(new Image(inputStream));

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
