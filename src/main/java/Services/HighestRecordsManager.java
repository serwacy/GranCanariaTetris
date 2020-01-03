package Services;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Slf4j
public enum HighestRecordsManager {
    INSTANCE;

    private static final int LAST_SCORE_ON_LIST = 9;
    private static final int SCORE_LIST_LENGTH = 10;
    private static final String SEPARATOR = ";";
//    private static final String FILEPATH = "%appdata%/scores.txt";

    private List<Record> scores = new ArrayList<>();
//    private File file = new File(FILEPATH);

    HighestRecordsManager() {
        File file = new File(System.getenv("APPDATA") + "/scores.txt");
        try {
            if(!file.createNewFile()) {
                scanScoresFromFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void scanScoresFromFile(File file) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(SEPARATOR);
                String nickname = line[0];
                int score = Integer.parseInt(line[1]);
                this.scores.add(new Record(nickname, score));
            }
        } catch (Exception e) {
            log.warn("cannot read from file " + file.getAbsolutePath());
        }
        sortScores();
    }

    private void sortScores() {
        this.scores.sort(new ScoresComparator());
    }

    static class ScoresComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return -(o1.getScore() - o2.getScore());
        }
    }

    public void addScore(String name, int score) {
        final Record record = new Record(name, score);
        if (this.scores.size() < SCORE_LIST_LENGTH) {
            this.scores.add(record);
        } else if (record.getScore() > this.scores.get(LAST_SCORE_ON_LIST).getScore()) {
            this.scores.remove(LAST_SCORE_ON_LIST);
            this.scores.add(record);
        }
        sortScores();
    }

    public void saveScoresToFile() {
        final String collect = getScoreListAsString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(System.getenv("APPDATA") + "/scores.txt"))) {
            writer.write(collect);
        } catch (IOException e) {
            log.warn("cannot write in file ");
        }
    }

    private String getScoreListAsString(){
        return this.scores.stream()
                .map(record -> record.getNickname() + SEPARATOR + record.getScore() + "\n")
                .collect(Collectors.joining());
    }

    public String prepareScoreListForLabel() {
        return getScoreListAsString()
                .replace(SEPARATOR, "\t");
    }
}
