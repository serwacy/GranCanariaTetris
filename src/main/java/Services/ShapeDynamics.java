package Services;

import Controlers.ControllerManager;
import Controlers.PlayController;
import Shapes.Shape;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;

import java.util.concurrent.atomic.AtomicBoolean;

public class ShapeDynamics implements Runnable {

    private AtomicBoolean running = new AtomicBoolean(false);
    private Shape movingShape;
    private int interval;

    public ShapeDynamics(Shape movingShape) {
        this.movingShape = movingShape;
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
                printing();
                Thread.sleep(interval);
                this.movingShape.fall();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printing(){
        Canvas canvas = ControllerManager.getPlayController().getCanvas();
        ControllerManager.getPlayController().getGc().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        ControllerManager.getPlayController().printCurrentShapeOnGrid();
    }

    public void setInterval(int level) {
        this.interval = 1000/level;
    }
}
