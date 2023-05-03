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


        Position start = new Position(0, 0, 'a');

        try (var lines = Files.lines(Path.of("src/input_test.txt"))) {
            List<String> mapLines = lines.toList();
            char[][] map = new char[mapLines.size()][mapLines.get(0).length()];
            for (int i = 0; i < mapLines.size(); i++) {
                char[] chars = mapLines.get(i).toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    if (chars[j] == 'S') {
                        start = new Position(j, i, 'a');
                    }
                    map[i][j] = chars[j];
                }
            }

            //List<Position> visited = new ArrayList<>();
            List<List<Position>> nodeTree = new ArrayList<>();
            nodeTree.add(List.of(start));
            System.out.println("start = " + start.x + " - " + start.y);

            boolean endFound = false;
            List<Position> nextLevel = getPossibleDestiniesFor(List.of(start), map);
            while (!endFound) {
                for (Position pos : nextLevel) {
                    if (pos.alt == 'Z') {
                        endFound = true;
                        break;
                    } else {
                        nextLevel = getPossibleDestiniesFor(nextLevel, map);
                        nodeTree.add(nextLevel);
                    }
                }
            }

            System.out.println(nodeTree.size());
        }
    }

    private static List<Position> getPossibleDestiniesFor(List<Position> positions, char[][] map) {
        List<Position> result = new ArrayList<>();

        for (Position pos : positions) {
            System.out.println("pos = " + pos.x + " - " + pos.y);
            //Go right
            if (validPosition(pos.x, pos.y + 1, pos.alt, map)) {
                result.add(new Position(pos.x, pos.y + 1, map[pos.x][pos.y + 1]));
                map[pos.x][pos.y + 1] = '0';
            }
            //Go left
            if (validPosition(pos.x, pos.y - 1, pos.alt, map)) {
                result.add(new Position(pos.x, pos.y - 1, map[pos.x][pos.y - 1]));
                map[pos.x][pos.y - 1] = '0';
            }
            //Go up
            if (validPosition(pos.x - 1, pos.y, pos.alt, map)) {
                result.add(new Position(pos.x - 1, pos.y, map[pos.x - 1][pos.y]));
                map[pos.x - 1][pos.y] = '0';
            }
            //Go down
            if (validPosition(pos.x + 1, pos.y, pos.alt, map)) {
                result.add(new Position(pos.x + 1, pos.y, map[pos.x + 1][pos.y]));
                map[pos.x + 1][pos.y] = '0';
            }
        }


        return result;
    }

    private static boolean validPosition(int x, int y, char alt, char[][] map) {
        return x >= 0 && x < map.length && y >= 0 && y < map[0].length
                && map[x][y] != '0' && (map[x][y] == alt || map[x][y] == alt + 1);
    }

}