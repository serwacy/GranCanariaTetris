package Services;

import Controlers.Graphics;
import javafx.application.Platform;

public enum ScoreCounter {
    INSTANCE;

    private int score;

    public int getScore() {
        return score;
    }

    public void addScore(final int scoreAdder) {
        this.score += scoreAdder;
        Platform.runLater(Graphics.INSTANCE.getPlayController()::setScoreLabel);
    }

    public void resetScore(){
        this.score = 0;
    }
}
