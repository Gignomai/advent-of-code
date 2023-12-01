import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


class Position {
    int x;
    int y;
    char alt;

    public Position(int x, int y, char alt) {
        this.x = x;
        this.y = y;
        this.alt = alt;
    }

}

public class FirstProblem {

    public static void main(String[] args) throws IOException {
        try (var lines = Files.lines(Path.of("src/input.txt"))) {
            Position start = new Position(0, 0, 'a');
            List<String> mapLines = lines.toList();
            char[][] map = new char[mapLines.size()][mapLines.get(0).length()];
            boolean[][] visited = new boolean[mapLines.size()][mapLines.get(0).length()];
            for (int i = 0; i < mapLines.size(); i++) {
                char[] chars = mapLines.get(i).toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    visited[i][j] = false;
                    map[i][j] = chars[j];
                    if (chars[j] == 'S') {
                        start = new Position(i, j, 'a');
                        visited[i][j] = true;
                    }
                }
            }

            List<List<Position>> nodeTree = new ArrayList<>(List.of(List.of(start)));
            nodeTree.add(getPossibleDestiniesFor(List.of(start), map, visited));

            System.out.println("Cell number: " + (map.length * map[0].length));
            System.out.println("Start: " + start.x + " - " + start.y);

            boolean endFound = false;
            for (int i = 1; i < nodeTree.size(); i++) {
                List<Position> altLevel = nodeTree.get(i);

                for (Position pos : altLevel) {
                    System.out.println("pos = " + pos.alt);
                    if (pos.alt == 'E') {
                        endFound = true;
                        break;
                    }
                }

                if (!endFound && !altLevel.isEmpty()) {
                    nodeTree.add(getPossibleDestiniesFor(altLevel, map, visited));
                }

                System.out.println(altLevel.size());
            }

            System.out.println(nodeTree.size() - 1);
        }
    }

    private static List<Position> getPossibleDestiniesFor(List<Position> positions, char[][] map, boolean[][] visited) {
        List<Position> result = new ArrayList<>();

        for (Position pos : positions) {
            //Go right
            if (validPosition(pos.x, pos.y + 1, pos.alt, map, visited)) {
                result.add(new Position(pos.x, pos.y + 1, map[pos.x][pos.y + 1]));
                visited[pos.x][pos.y + 1] = true;
            }
            //Go left
            if (validPosition(pos.x, pos.y - 1, pos.alt, map, visited)) {
                result.add(new Position(pos.x, pos.y - 1, map[pos.x][pos.y - 1]));
                visited[pos.x][pos.y - 1] = true;
            }
            //Go up
            if (validPosition(pos.x - 1, pos.y, pos.alt, map, visited)) {
                result.add(new Position(pos.x - 1, pos.y, map[pos.x - 1][pos.y]));
                visited[pos.x - 1][pos.y] = true;
            }
            //Go down
            if (validPosition(pos.x + 1, pos.y, pos.alt, map, visited)) {
                result.add(new Position(pos.x + 1, pos.y, map[pos.x + 1][pos.y]));
                visited[pos.x + 1][pos.y] = true;
            }
        }

        return result;
    }

    private static boolean validPosition(int x, int y, char alt, char[][] map, boolean[][] visited) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length && !visited[x][y]
                && ((map[x][y] == 'E' && (alt == 'y' || alt == 'z'))
                || (map[x][y] != 'E' && (map[x][y] <= alt || map[x][y] == alt + 1)));
    }

}