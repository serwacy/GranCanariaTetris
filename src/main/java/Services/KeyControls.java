package Services;

import Controlers.PlayController;
import Tetris.Game;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class KeyControls extends GameComponent{
    private PlayController playController;

    Map<KeyCode, Runnable> actions = new HashMap<>();
    public void AddAction(KeyCode k, Runnable r)
    {
        actions.put(k,r);
    }

    public KeyControls(final PlayController playController) {
        this.playController = playController;
    }

    public void addKeyControls(Game game) {
        final Scene scene = playController.getCanvasForBigPane().getScene();
        scene.setOnKeyPressed(event -> {

            if(actions.containsKey(event.getCode()))
            {
                actions.get(event.getCode()).run();
            }

            else if(event.getCode() == KeyCode.DOWN){
                game.getCurrentShape().fall();
                game.getEngine().refreshCanvas();

                super.counter.addScore(1);
            } else {
                System.out.println(event.getCode());
            }
        });
    }
}
