package Services;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Engine implements Runnable {

    private AtomicBoolean running = new AtomicBoolean(false);
    private int interval;

    private void setInterval(int level) {
        this.interval = 1000/level;
    }

    public void start(){
        Thread gameFlow = new Thread(this);
        gameFlow.start();
    }

    public void stop(){
        running.set(false);
    }

    private List<Runnable> onTick = new LinkedList<>();

    public void onTick(Runnable r){
        onTick.add(r);
    }

    @Override
    public void run() {
        running.set(true);
        setInterval(1);
        while (running.get()) {
            try {
                onTick.forEach(Runnable::run);
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
