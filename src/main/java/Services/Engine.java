package Services;

import Controlers.ControllerManager;
import Controlers.PlayController;
import Shapes.Shape;
import javafx.scene.canvas.Canvas;
import java.util.concurrent.atomic.AtomicBoolean;

public class Engine extends GameComponent implements Runnable {

    private AtomicBoolean running = new AtomicBoolean(false);
    private Shape movingShape;
    private int interval;
    private final PlayController playController;

    public Engine(Shape movingShape) {
        this.movingShape = movingShape;
        this.playController = ControllerManager.getPlayController();
    }
    public void setInterval(int level) {
        this.interval = 1000/level;
    }

    public void start(){
        Thread gameFlow = new Thread(this);
        gameFlow.start();
    }

    public void stop(){
        running.set(false);
    }

    @Override
    public void run() {
        running.set(true);
        while (running.get()) {
            try {
                refreshCanvas();
                super.getCounter().addScore(1);
                Thread.sleep(interval);
                this.movingShape.fall();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void refreshCanvas(){
        clearCanvas();
        printTetrion();
        printCurrentShape();
        printNextShape();

    }
    private void clearCanvas(){
        Canvas canvas = this.playController.getCanvasForBigPane();
        this.playController.getGraphicsContextForBigPane().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    private void printTetrion(){
        this.playController.printTetrion();
    }
    private void printCurrentShape(){ this.playController.printCurrentShape(); }
    private void printNextShape(){
        this.playController.printNextShapeOnGrid();
    }

}
