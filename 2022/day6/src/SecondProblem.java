import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SecondProblem {
    public static void main(String[] args) throws IOException {
        Files.lines(Path.of("src/input.txt"))
                .map(s -> SecondProblem.searchFirstStartOfPacketMarker(s))
                .forEach(System.out::println);
    }

    private static long searchFirstStartOfPacketMarker(String s) {
        long position = 0L;

        for (int i = 0; i < s.length(); i++) {
            if (hasDuplicatedLetters(s.substring(i, i + 14))) {
                position = i + 14;
                break;
            }
        }

        return position;
    }

    private static boolean hasDuplicatedLetters(String letters) {
        char[] part = letters.toCharArray();
        for (int i = 0; i < 14; i++) {
            for (int j = i + 1; j < 14; j++) {
                if (part[i] == part[j]) {
                    return false;
                }
            }
        }
        return true;
    }
}