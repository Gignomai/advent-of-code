import java.util.List;

public class Part1 {

    public Integer processLines(List<String> lines) {
        HeightMap map = new HeightMap(lines);

        return getVisibleTrees(map.grid);
    }

    private Integer getVisibleTrees(int[][] grid) {
        int cols = grid.length;
        int rows = grid[0].length;
        int visibleTrees = (rows * 2) + ((cols - 2) * 2);

        for (int i = 1; i < cols - 1; i++) {
            for (int j = 1; j < rows - 1; j++) {

                boolean isVisible;
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
                    for (int col = j + 1; col < rows; col++) {
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
                    for (int row = i + 1; row < cols; row++) {
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



    public boolean test(List<String> lines) {
        return processLines(lines).equals(21);
    }
}