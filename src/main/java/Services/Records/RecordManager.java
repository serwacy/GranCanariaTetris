package Services.Records;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public enum RecordManager {
    INSTANCE;

    private List<Record> scores = new ArrayList<>();
    private RecordSaver scoreSaver = new RecordSaver(scores);
    private RecordSorter scoreSorter = new RecordSorter(scores);
    private RecordLoader scoreLoader = new RecordLoader(scores, scoreSorter);

    RecordManager() {
        createDirectoryForSavingScores();
        initializeSaveScoresFile();
    }

    private void createDirectoryForSavingScores() {
        File directory = new File(RecordManagerParameters.SCORES_DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    private void initializeSaveScoresFile() {
        File file = new File(RecordManagerParameters.SCORES_FILE_PATH);
        try {
            if (!file.createNewFile()) {
                scoreLoader.scanScoresFromFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveScore(final String name, final int score) {
        addScore(name, score);
        scoreSaver.saveScoresToFile();
    }

    private void addScore(String name, int score) {
        name = name.replaceAll("[\\+\\.\\^:,!?\\\\/]", "");
        final Record record = new Record(name, score);
        if (this.scores.size() < RecordManagerParameters.SCORE_LIST_LENGTH) {
            this.scores.add(record);
        } else if (record.getScore() > this.scores.get(RecordManagerParameters.LAST_SCORE_ON_LIST).getScore()) {
            this.scores.remove(RecordManagerParameters.LAST_SCORE_ON_LIST);
            this.scores.add(record);
        }
        scoreSorter.sortScores();
    }

    public String prepareListNamesForLabel() {
        final AtomicInteger iterator = new AtomicInteger(0);
        return this.scores.stream()
                .map(record -> {
                    iterator.getAndIncrement();
                    return iterator + ". " + record.getNickname() + "\n";
                })
                .collect(Collectors.joining());
    }

    public String prepareListValuesForLabel() {
        return this.scores.stream()
                .map(record -> record.getScore() + "\n")
                .collect(Collectors.joining());
    }
}
