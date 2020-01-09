package Services;

import Controlers.ControllerManager;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import java.util.HashMap;
import java.util.Map;

public class KeyControls {
    private Map<KeyCode, Runnable> actions = new HashMap<>();

    public void addAction(KeyCode k, Runnable r) {
        actions.put(k, r);
    }

    public void addKeyControls() {
        final Scene scene = ControllerManager.getPlayController().getCanvasForBigPane().getScene();
        scene.setOnKeyPressed(event -> {
            if (actions.containsKey(event.getCode())) {
                actions.get(event.getCode()).run();
            }
        });
    }

    public void removeKeyControls(){
        actions.clear();
    }
}
