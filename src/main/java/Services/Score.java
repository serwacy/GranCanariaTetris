package Services;

public class Score {
    String nickname;
    int score;

    public Score(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }
}
