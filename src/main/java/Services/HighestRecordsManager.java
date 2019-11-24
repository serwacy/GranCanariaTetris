package Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public enum  HighestRecordsManager {
    INSTANCE;

    List<Record> scores = new LinkedList<>();
    File file = new File("./src/main/resources/scores.txt");

    private void scanScoresFromFile(File file) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                String nickname = line[0];
                int score = Integer.parseInt(line[1]);
                this.scores.add(new Record(nickname, score));
            }
        } catch (Exception e) {
            System.out.println("exception in reading scores");
        }
        this.scores.sort(new ScoresComparator());
    }
    public String prepareContentForLabel(){
        scanScoresFromFile(this.file);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.scores.size() && i < 10; i++) {
            result.append(i+1);
            result.append(". ");
            result.append(this.scores.get(i).getNickname());
            int dots = 20- this.scores.get(i).getNickname().length()-(Integer.toString(this.scores.get(i).getScore())).length();
            for (int j = 0; j < dots; j++) {
                result.append(" ");
            }
            result.append(this.scores.get(i).getScore());
            result.append("\n");
        }
        System.out.println(result);
        return result.toString();
    }

    static class ScoresComparator implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return -(o1.getScore()-o2.getScore());
        }
    }
}
