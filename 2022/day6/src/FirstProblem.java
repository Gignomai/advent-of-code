import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FirstProblem {
    public static void main(String[] args) throws IOException {
        Files.lines(Path.of("src/input.txt"))
                .map(s -> FirstProblem.searchFirstStartOfPacketMarker(s))
                .forEach(System.out::println);
    }

    private static long searchFirstStartOfPacketMarker(String s) {
        long position = 0L;

        for (int i = 0; i < s.length(); i++) {
            if (hasDuplicatedLetters(s.substring(i, i + 4))) {
                position = i + 4;
                break;
            }
        }

        return position;
    }

    private static boolean hasDuplicatedLetters(String letters) {
        char[] part = letters.toCharArray();
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (part[i] == part[j]) {
                    return false;
                }
            }
        }
        return true;
    }

}