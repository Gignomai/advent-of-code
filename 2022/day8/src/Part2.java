import java.util.List;

public class Part2 {
    public Integer processLines(List<String> lines) {
        HeightMap map = new HeightMap(lines);
        return getMaxScenicScore(map.grid);
    }

    private Integer getMaxScenicScore(int[][] grid) {
        int numberOfLines = grid.length;
        int lineLength = grid[0].length;
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

    public boolean test(List<String> lines) {
        return processLines(lines).equals(8);
    }
}