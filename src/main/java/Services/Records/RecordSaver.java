package Services.Records;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class RecordSaver {

    private List<Record> scores;

    public RecordSaver(final List<Record> scores) {
        this.scores = scores;
    }

    public void saveScoresToFile() {
        final String collect = getScoreListAsString();
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(RecordManagerParameters.SCORES_FILE_PATH))) {
            writer.write(collect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getScoreListAsString() {
        return this.scores.stream()
                .map(record -> record.getNickname() + RecordManagerParameters.SEPARATOR + record.getScore() + "\n")
                .collect(Collectors.joining());
    }
}
