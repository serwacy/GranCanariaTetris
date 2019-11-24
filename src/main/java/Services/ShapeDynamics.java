package Services;

import Controlers.PlayController;
import Shapes.Shape;
import javafx.scene.canvas.Canvas;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShapeDynamics implements Runnable {

    private AtomicBoolean running;
    private Shape movingShape;
    private int interval;
    private final PlayController playController;

    public ShapeDynamics(Shape movingShape, final PlayController playController) {
        this.movingShape = movingShape;
        this.playController = playController;
        running = new AtomicBoolean(false);
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
                ScoreCounter.INSTANCE.addScore(1);
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
