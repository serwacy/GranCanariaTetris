package Services;

import Controlers.ControllerManager;
import javafx.application.Platform;

public class ScoreCounter {

    private int score;

    private Runnable onNewScore;
    public void OnNewScore(Runnable r){
        if(r!=null)
            r.run();
    }

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
