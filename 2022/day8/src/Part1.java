import java.util.List;

public class Part1 {

    public Integer processLines(List<String> lines) {
        HeightMap map = new HeightMap(lines);

        return getVisibleTrees(map.grid);
    }

    private Integer getVisibleTrees(int[][] grid) {
        int numberOfLines = grid.length;
        int lineLength = grid[0].length;
        int visibleTrees = (lineLength * 2) + ((numberOfLines - 2) * 2);

        for (int i = 1; i < numberOfLines - 1; i++) {
            for (int j = 1; j < lineLength - 1; j++) {

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



    public boolean test(List<String> lines) {
        return processLines(lines).equals(21);
    }
}