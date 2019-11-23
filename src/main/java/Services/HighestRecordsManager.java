package Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class HighestRecordsManager {
    public static String getScores(){
        List<Record> scores = new LinkedList<>();

        File file = new File("./src/main/resources/scores.txt");
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))){
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                String nickname = line[0];
                int score = Integer.parseInt(line[1]);
                scores.add(new Record(nickname,score));
            }
        }
        catch (Exception e) {
            System.out.println("exception in reading scores");
        }
        scores.sort(new SortScores());
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < scores.size() && i < 10; i++) {
            result.append(i+1);
            result.append(". ");
            result.append(scores.get(i).getNickname());
            int dots = 20-scores.get(i).getNickname().length()-(Integer.toString(scores.get(i).getScore())).length();
            for (int j = 0; j < dots; j++) {
                result.append(" ");
            }
            result.append(scores.get(i).getScore());
            result.append("\n");
        }
        return result.toString();
    }
    static class SortScores implements Comparator<Record> {
        @Override
        public int compare(Record o1, Record o2) {
            return -(o1.getScore()-o2.getScore());
        }
    }
}
