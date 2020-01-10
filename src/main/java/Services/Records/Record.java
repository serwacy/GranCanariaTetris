package Services.Records;

public class Record {
    private String nickname;
    private int score;

    public Record(final String nickname, final int score) {
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
