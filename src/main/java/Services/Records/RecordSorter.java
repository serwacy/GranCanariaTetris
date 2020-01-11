package Services.Records;

import java.util.Comparator;
import java.util.List;

public class RecordSorter {
    private List<Record> scores;

    public RecordSorter(final List<Record> scores) {
        this.scores = scores;
    }

    public void sortScores() {
        this.scores.sort(new ScoresComparator());
    }

    private static class ScoresComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return -(o1.getScore() - o2.getScore());
        }
    }
}
