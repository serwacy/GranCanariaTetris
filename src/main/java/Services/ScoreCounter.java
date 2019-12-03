package Services;

import Controlers.ControllerManager;
import javafx.application.Platform;

public class ScoreCounter {

    private int score;

    public int getScore() {
        return score;
    }

    public void addScore(final int scoreAdder) {
        this.score += scoreAdder;
        Platform.runLater(ControllerManager.getPlayController()::setScoreLabel);
    }

    public void resetScore(){
        this.score = 0;
    }
}
