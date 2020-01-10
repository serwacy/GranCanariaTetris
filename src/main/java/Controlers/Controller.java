package Controlers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {
    protected void showMenu(Button button) throws IOException {
        prepareScene(button, "Menu.fxml");
    }

    protected void showGame(Button button) throws IOException {
        prepareScene(button, "Play.fxml");
    }

    protected void showCredits(Button button) throws IOException {
        prepareScene(button, "Credits.fxml");
    }

    protected void showScores(Button button) throws IOException {
        prepareScene(button, "HighScores.fxml");
    }

    protected void showScoreSaver(Button button) throws IOException {
        prepareScene(button, "SaveScore.fxml");
    }

    protected void exitProgram() {
        Platform.exit();
    }

    private void prepareScene(Button button, String resource) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(resource));
        Scene scene = new Scene(root, 550, 850);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
