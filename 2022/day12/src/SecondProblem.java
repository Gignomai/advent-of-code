import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SecondProblem {

    public static void main(String[] args) throws IOException {
        try (var lines = Files.lines(Path.of("src/input.txt"))) {
            List<String> mapLines = lines.collect(Collectors.toList());
            String[][] map = new String[mapLines.size()][mapLines.get(0).length()];
            for (int i = 0; i < mapLines.size(); i++) {
                char[] chars = mapLines.get(i).toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    map[i][j] = String.valueOf(chars[j]);
                }
            }
        }
    }

}