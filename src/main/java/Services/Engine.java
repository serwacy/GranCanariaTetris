package Services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Engine implements Runnable {
    private final static int MAX_LEVEL = 20;
    private final static List<Integer> LEVEL_THRESHOLD =
            Arrays.asList(2000, 6000, 12000, 20000, 30000, 42000, 56000, 72000, 90000, 110000,
                    132000, 156000, 182000, 210000, 240000, 272000, 306000, 342000, 380000, 420000);

    private AtomicBoolean running = new AtomicBoolean(false);
    private int interval;
    private int level;
    private List<Runnable> onTick = new LinkedList<>();

    public void start(){
        Thread gameFlow = new Thread(this);
        this.level = 1;
        gameFlow.start();
    }

    public void stop(){
        running.set(false);
    }

    public void addToOnTick(Runnable r){
        onTick.add(r);
    }

    @Override
    public void run() {
        running.set(true);
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

    private void setInterval(int level) {
        this.interval = 1000/level;
    }
    public int getLevel() {
        return level;
    }
    private void setLevel(final int level) {
        this.level = level;
    }
    public void raiseGameLevel (final Integer score){
        if(getLevel() < MAX_LEVEL) {
            if (score > LEVEL_THRESHOLD.get(getLevel() - 1)) {
                setLevel(getLevel() + 1);
            }
        }
    }
}
