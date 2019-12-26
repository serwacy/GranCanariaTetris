package Services;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Engine implements Runnable {

    private AtomicBoolean running = new AtomicBoolean(false);
    private int interval;
    private int level;

    private void setInterval(int level) {
        this.interval = 1000/level;
    }

    public void start(){
        Thread gameFlow = new Thread(this);
        this.level = 1;
        gameFlow.start();
    }

    public void stop(){
        running.set(false);
    }

    private List<Runnable> onTick = new LinkedList<>();

    public void addToOnTick(Runnable r){
        onTick.add(r);
    }

    @Override
    public void run() {
        running.set(true);
//        setInterval(level);
        while (running.get()) {
            try {
                setInterval(level);
                onTick.forEach(Runnable::run);
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(final int level) {
        this.level = level;
    }
}
