package model;

import java.util.Arrays;
import java.util.List;

public class Grid {
    private final char[][] map;
    private Position start;

    public Grid(List<String> lines) {
        start = new Position(0, 0);
        map = new char[lines.size()][lines.getFirst().length()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                map[i][j] = lines.get(i).charAt(j);
                if (lines.get(i).charAt(j) == '^') {
                    start = new Position(j, i);
                }
            }
        }
    }

    public Position getStart() {
        return start;
    }

    public boolean isInsideTheGrid(Position position) {
        return position.x() >= 0 && position.x() < map[0].length && position.y() >= 0 && position.y() < map.length;
    }

    public boolean checkIfPositionIsBlocked(Position position) {
        return map[position.y()][position.x()] == '#';
    }

    public boolean isVisited(int x, int y) {
        return map[y][x] == 'X';
    }

    public void markAsVisited(int x, int y) {
        map[y][x] = 'X';
    }

    @Override
    public String toString() {
        return Arrays.deepToString(map) + " " + start.toString();
    }
}
