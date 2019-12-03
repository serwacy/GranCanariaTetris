package Services;

import Controlers.PlayController;
import Tetris.Game;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class KeyControls extends GameComponent{
    private PlayController playController;

    public KeyControls(final PlayController playController) {
        this.playController = playController;
    }

    public void addKeyControls(Game game) {
        final Scene scene = playController.getCanvasForBigPane().getScene();
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                game.getCurrentShape().moveLeft();
                game.getEngine().refreshCanvas();
            } else if (event.getCode() == KeyCode.RIGHT) {
                game.getCurrentShape().moveRight();
                game.getEngine().refreshCanvas();
            } else if (event.getCode() == KeyCode.UP) {
                game.getCurrentShape().rotate();
                game.getEngine().refreshCanvas();
            } else if(event.getCode() == KeyCode.DOWN){
                game.getCurrentShape().fall();
                game.getEngine().refreshCanvas();
                super.counter.addScore(1);
            } else {
                System.out.println(event.getCode());
            }
        });
    }
}
