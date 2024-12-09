import java.util.List;

public class Part2 {
    public Integer processLines(List<String> lines) {
        int count = 0;

        char[][] grid = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                grid[i][j] = lines.get(i).charAt(j);
            }
        }

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                if (grid[i][j] == 'A') {
                    char ne = checkNorthEast(i, j, grid);
                    char se = checkSouthEast(i, j, grid);
                    char sw = checkSouthWest(i, j, grid);
                    char nw = checkNorthWest(i, j, grid);

                    String resA = String.valueOf(ne) + 'A' + sw;
                    String resB = String.valueOf(nw) + 'A' + se;

                    if (checkText(resA) && checkText(resB)) count++;
                }
            }
        }
        System.out.println("count = " + count);
        return count;
    }

    private char checkNorthEast(int y, int x, char[][] grid) {
        if (y > 0 && x < grid[0].length - 1) {
            return grid[y - 1][x + 1];
        }
        return '0';
    }

    private char checkSouthEast(int y, int x, char[][] grid) {
        if (y < grid.length - 1 && x < grid[0].length - 1) {
            return grid[y + 1][x + 1];
        }
        return '0';
    }

    private char checkSouthWest(int y, int x, char[][] grid) {
        if (y < grid.length - 1 && x > 0) {
            return grid[y + 1][x - 1];
        }
        return '0';
    }

    private char checkNorthWest(int y, int x, char[][] grid) {
        if (y > 0 && x > 0) {
            return grid[y - 1][x - 1];
        }
        return '0';
    }

    private static boolean checkText(String text) {
        return text.equals("MAS") || text.equals("SAM");
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(9);
    }
}