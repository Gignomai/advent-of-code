import java.util.List;

enum Directions {
    UP,
    RIGHT,
    DOWN,
    LEFT
}

record Position(int x, int y) {
}

public class Part1 {
    public Integer processLines(List<String> lines) {
        int count = 0;

        char[][] grid = new char[lines.size()][lines.get(0).length()];
        Position position = new Position(0, 0);

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                grid[i][j] = lines.get(i).charAt(j);
                if (lines.get(i).charAt(j) == '^') {
                    position = new Position(j, i);
                }
            }
        }

        Directions current = Directions.UP;
        while (insideGrid(position, grid)) {
            position = move(position, current, grid);
        }

        return count;
    }

    private Position move(Position position, Directions current, char[][] grid) {

        return new Position(0,0);
    }

    private static boolean insideGrid(Position position, char[][] grid) {
        return position.x() > 0 || position.x() < grid.length
                || position.y() > 0 || position.y() < grid[0].length;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(41);
    }

}