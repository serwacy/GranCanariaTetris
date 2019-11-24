package Services;

import Controlers.PlayController;
import Tetris.Game;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyControls {
    private PlayController playController;
    private Game game;

    public KeyControls(final PlayController playController, final Game game) {
        this.playController = playController;
        this.game = game;
    }

    public void addKeyControls() {
        final Scene scene = playController.getCanvasForBigPane().getScene();
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.getCurrentShape().moveLeft();
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.getCurrentShape().moveRight();
            } else if (event.getCode() == KeyCode.UP) {
                game.getCurrentShape().rotate();
            } else {
                System.out.println(event.getCode());
            }
        });
    }
}
