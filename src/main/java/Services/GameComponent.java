package Services;

public abstract class GameComponent {
    protected ScoreCounter counter = new ScoreCounter();

    public GameComponent() {
    }

    public ScoreCounter getCounter() {
        return counter;
    }
}
