package Services;

import Controlers.Graphics;
import Controlers.PlayController;
import Shapes.Shape;
import Tetris.Game;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyControls {

    public void addKeyControls() {
        final Scene scene = Graphics.INSTANCE.getPlayController().getCanvasForBigPane().getScene();
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                Game.INSTANCE.getCurrentShape().moveLeft();
                Graphics.INSTANCE.refresh();
            } else if (event.getCode() == KeyCode.RIGHT) {
                Game.INSTANCE.getCurrentShape().moveRight();
                Graphics.INSTANCE.refresh();
            } else if (event.getCode() == KeyCode.UP) {
                Game.INSTANCE.getCurrentShape().rotate();
                Graphics.INSTANCE.refresh();
            } else if (event.getCode() == KeyCode.DOWN) {
                Game.INSTANCE.getCurrentShape().fall();
                Graphics.INSTANCE.refresh();
                ScoreCounter.INSTANCE.addScore(2);
            } else {
                System.out.println(event.getCode());
            }
        });
    }
}
