import java.util.List;

public class Part1 {
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
                if (grid[i][j] == 'X') {
                    if (checkNorth(i, j, grid)) {
                        count++;
                    }
                    if (checkSouth(i, j, grid)) {
                        count++;
                    }
                    if (checkEast(i, j, grid)) {
                        count++;
                    }
                    if (checkWest(i, j, grid)) {
                        count++;
                    }
                    if (checkNorthWest(i, j, grid)) {
                        count++;
                    }
                    if (checkSouthWest(i, j, grid)) {
                        count++;
                    }
                    if (checkSouthEast(i, j, grid)) {
                        count++;
                    }
                    if (checkNorthEast(i, j, grid)) {
                        count++;
                    }
                }
            }
        }
        System.out.println("count = " + count);
        return count;
    }

    private boolean checkNorth(int y, int x, char[][] grid) {
        StringBuilder text = new StringBuilder();
        if (y > 2) {
            for (int i = 0; i < 4; i++) {
                text.append(grid[y - i][x]);
            }
        }
        return checkText(text.toString());
    }

    private boolean checkNorthEast(int y, int x, char[][] grid) {
        StringBuilder text = new StringBuilder();
        if (y > 2 && x <= grid[0].length - 4) {
            for (int i = 0; i < 4; i++) {
                text.append(grid[y - i][x + i]);
            }
        }
        return checkText(text.toString());
    }

    private boolean checkEast(int y, int x, char[][] grid) {
        StringBuilder text = new StringBuilder();
        if (x <= grid[0].length - 4) {
            for (int i = 0; i < 4; i++) {
                text.append(grid[y][x + i]);
            }
        }
        return checkText(text.toString());
    }

    private boolean checkSouthEast(int y, int x, char[][] grid) {
        StringBuilder text = new StringBuilder();
        if (y <= grid.length - 4 && x <= grid[0].length - 4) {
            for (int i = 0; i < 4; i++) {
                text.append(grid[y + i][x + i]);
            }
        }
        return checkText(text.toString());
    }

    private boolean checkSouth(int y, int x, char[][] grid) {
        StringBuilder text = new StringBuilder();
        if (y <= grid.length - 4) {
            for (int i = 0; i < 4; i++) {
                text.append(grid[y + i][x]);
            }
        }
        return checkText(text.toString());
    }

    private boolean checkSouthWest(int y, int x, char[][] grid) {
        StringBuilder text = new StringBuilder();
        if (y <= grid.length - 4 && x > 2) {
            for (int i = 0; i < 4; i++) {
                text.append(grid[y + i][x - i]);
            }
        }
        return checkText(text.toString());
    }

    private boolean checkWest(int y, int x, char[][] grid) {
        StringBuilder text = new StringBuilder();
        if (x > 2) {
            for (int i = 0; i < 4; i++) {
                text.append(grid[y][x - i]);
            }
        }
        return checkText(text.toString());
    }

    private boolean checkNorthWest(int y, int x, char[][] grid) {
        StringBuilder text = new StringBuilder();
        if (y > 2 && x > 2) {
            for (int i = 0; i < 4; i++) {
                text.append(grid[y - i][x - i]);
            }
        }
        return checkText(text.toString());
    }

    private static boolean checkText(String text) {
        return text.equals("XMAS");
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(18);
    }
}