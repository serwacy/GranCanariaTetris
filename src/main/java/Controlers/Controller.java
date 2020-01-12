package Controlers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class Controller {
    protected void prepareScene(Button button, String resource) throws IOException {
        Stage stage = (Stage) button.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(resource));
        Scene scene = new Scene(root, 550, 850);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
