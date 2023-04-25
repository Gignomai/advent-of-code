import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SecondProblem {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Path.of("src/input.txt"))
                .collect(Collectors.toList());

        System.out.println(getGridFromLines(lines));
    }

    private static int getGridFromLines(List<String> lines) {
        int numberOfLines = lines.size();
        int lineLength = lines.get(0).length();
        int[][] grid = new int[numberOfLines][lineLength];

        for (int i = 0; i < numberOfLines; i++) {
            char[] chars = lines.get(i).toCharArray();
            for (int j = 0; j < lineLength; j++) {
                grid[i][j] = Integer.parseInt(String.valueOf(chars[j]));
            }
        }

        int maxScenicScore = 0;


        for (int i = 1; i < numberOfLines - 1; i++) {
            for (int j = 1; j < lineLength - 1; j++) {

                //Search North
                int northScore = 0;
                for (int row = i - 1; row >= 0; row--) {
                    northScore++;
                    if (grid[row][j] >= grid[i][j]) {
                        break;
                    }
                }

                //Search East
                int eastScore = 0;
                for (int col = j + 1; col < lineLength; col++) {
                    eastScore++;
                    if (grid[i][col] >= grid[i][j]) {
                        break;
                    }
                }

                //Search South
                int southScore = 0;
                for (int row = i + 1; row < numberOfLines; row++) {
                    southScore++;
                    if (grid[row][j] >= grid[i][j]) {
                        break;
                    }
                }

                //Search West
                int westScore = 0;
                for (int col = j - 1; col >= 0; col--) {
                    westScore++;
                    if (grid[i][col] >= grid[i][j]) {
                        break;
                    }
                }

                int auxScore = northScore * westScore * southScore * eastScore;
                if (auxScore > maxScenicScore) {
                    maxScenicScore = auxScore;
                }

            }
        }

        return maxScenicScore;
    }

}