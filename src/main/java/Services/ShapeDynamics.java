package Services;

import Shapes.Shape;

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
                Thread.sleep(interval);
                gravity();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void gravity() {
        this.movingShape.fall();
    }

    public void setInterval(int level) {
        this.interval = 1000/level;
    }
}
