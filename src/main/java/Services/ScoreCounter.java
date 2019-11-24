package Services;

public enum ScoreCounter {
    INSTANCE;

    private int score;

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
