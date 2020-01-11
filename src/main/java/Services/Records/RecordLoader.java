package Services.Records;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

public class RecordLoader {

    private List<Record> scores;
    private RecordSorter sorter;

    public RecordLoader(final List<Record> scores, final RecordSorter sorter) {
        this.scores = scores;
        this.sorter = sorter;
    }

    public void scanScoresFromFile(File file) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(RecordManagerParameters.SEPARATOR);
                String nickname = line[0];
                int score = Integer.parseInt(line[1]);
                this.scores.add(new Record(nickname, score));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sorter.sortScores();
    }

}
