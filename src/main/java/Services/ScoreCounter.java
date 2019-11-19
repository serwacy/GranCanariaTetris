package Services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class ScoreCounter {
    private int score;

    public ScoreCounter() {
        score = 0;
    }

    private void increaseScore(int level) {
        score += 10 * level;
    }

    public int getScore() {
        return score;
    }

    public static String getScores(){
        List<Score> scores = new LinkedList<>();

        File file = new File("./src/main/resources/scores.txt");
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))){
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(";");
                String nickname = line[0];
                int score = Integer.parseInt(line[1]);
                scores.add(new Score(nickname,score));
            }
        }catch (Exception e){
            System.out.println("exception in reading scores");;
        }finally {
            Collections.sort(scores, new SortScores());
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < scores.size()&&i<9; i++) {
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
            //System.out.println(result.toString());
            return result.toString();
        }
    }
    static class SortScores implements Comparator<Score> {
        @Override
        public int compare(Score o1, Score o2) {
            return -(o1.getScore()-o2.getScore());
        }
    }
}
