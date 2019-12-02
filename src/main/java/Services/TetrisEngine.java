package Services;

import Controlers.Graphics;
import Controlers.PlayController;
import Tetris.Game;

import java.util.concurrent.atomic.AtomicBoolean;

public enum TetrisEngine implements Runnable {
    INSTANCE;

    private AtomicBoolean running = new AtomicBoolean(false);
    private int interval;

    //    public TetrisEngine(Shape movingShape, final PlayController playController) {
//        this.movingShape = movingShape;
//        this.playController = playController;
//        running = new AtomicBoolean(false);
//    }
    public void setInterval(int level) {
        this.interval = 500/level;
    }

    public void start(){
        KeyControls keyControls = new KeyControls();
        keyControls.addKeyControls();
        Thread gameFlow = new Thread(this);
        gameFlow.start();
    }

    public void stop(){
        running.set(false);
    }

    @Override
    public void run() {
        running.set(true);
        Game.INSTANCE.nextShapeToCurrentShapeAndInitNext();
        while (running.get()) {
            try {
                Graphics.INSTANCE.refresh();
                ScoreCounter.INSTANCE.addScore(1);
                Thread.sleep(interval);
                Game.INSTANCE.getCurrentShape().fall();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
