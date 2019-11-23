package Services;

public class ScoreCounter {
    private static final ScoreCounter INSTANCE = new ScoreCounter();

    private int score;

    private ScoreCounter() {
        this.score = 0;
    }

    public static ScoreCounter getInstance() {
        return INSTANCE;
    }

    public int getScore() {
        return score;
    }

    public void addScore(final int scoreAdder) {
        this.score += scoreAdder;
    }

    public void resetScore(){
        this.score = 0;
    }
}
