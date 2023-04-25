import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FirstProblem {

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws IOException {
        long total = Files.lines(Path.of("src/input.txt"))
                .mapToLong(FirstProblem::getPriority)
                .sum();
        System.out.println("total: " + total);
    }

    private static Integer getPriority(String s) {
        String part1 = s.substring(0, s.length()/2);
        String part2 = s.substring(s.length()/2);

        char finding = ' ';
        boolean found = false;
        for (int i = 0; i < part1.length(); i++) {
            for (int j = 0; j < part2.length(); j++) {
                if(part1.charAt(i) == part2.charAt(j)) {
                    found = true;
                    finding = part1.charAt(i);
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        return LETTERS.indexOf(finding) + 1;
    }
}