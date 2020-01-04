package Services;

import javafx.application.Platform;
import java.util.Observable;

public class ScoreCounter extends Observable {
    private int score;

    public int getScore() {
        return score;
    }
    public void addScore(final int scoreAdder) {
        this.score += scoreAdder;
        Platform.runLater(()->{
            setChanged();
            notifyObservers(score);
        });
    }
    public void resetScore(){
        this.score = 0;
    }

}
