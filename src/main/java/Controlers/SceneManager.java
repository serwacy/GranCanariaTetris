package Controlers;

import javafx.scene.Scene;

public class SceneManager {
    static Scene scene;

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(final Scene scene) {
        SceneManager.scene = scene;
    }
}
