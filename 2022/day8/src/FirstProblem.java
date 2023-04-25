import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FirstProblem {

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

        int visibleTrees = (lineLength * 2) + ((numberOfLines - 2) * 2);
        for (int i = 1; i < numberOfLines - 1; i++) {
            for (int j = 1; j < lineLength - 1; j++) {

                boolean isVisible = false;
                //Search North
                boolean visibleTemp = true;
                for (int row = i - 1; row >= 0; row--) {
                    if (grid[row][j] >= grid[i][j]) {
                        visibleTemp = false;
                        break;
                    }
                }
                isVisible = visibleTemp;
                //Search East
                visibleTemp = true;
                if (!isVisible) {
                    for (int col = j + 1; col < lineLength; col++) {
                        if (grid[i][col] >= grid[i][j]) {
                            visibleTemp = false;
                            break;
                        }
                    }
                    isVisible = visibleTemp;
                }
                //Search South
                visibleTemp = true;
                if (!isVisible) {
                    for (int row = i + 1; row < numberOfLines; row++) {
                        if (grid[row][j] >= grid[i][j]) {
                            visibleTemp = false;
                            break;
                        }
                    }
                    isVisible = visibleTemp;
                }
                //Search West
                visibleTemp = true;
                if (!isVisible) {
                    for (int col = j - 1; col >= 0; col--) {
                        if (grid[i][col] >= grid[i][j]) {
                            visibleTemp = false;
                            break;
                        }
                    }
                    isVisible = visibleTemp;
                }

                if (isVisible) {
                    visibleTrees++;
                }
            }
        }

        return visibleTrees;
    }

}