package Services;

public class ScoreCounter {
    private int score;

    public ScoreCounter() {
        score = 0;
    }

    private void increaseScore(int level) {
        score += 10 * level;
    }

    public int getScore() {
        return score;
    }
}
