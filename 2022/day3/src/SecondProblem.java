import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SecondProblem {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws IOException {
        List<String> rucksacks = Files.lines(Path.of("src/input.txt")).collect(Collectors.toList());

        Long total = getGroups(rucksacks).stream()
                .mapToLong(SecondProblem::getPriority)
                .sum();

        System.out.println("total: \n" + total);
    }

    private static List<List<String>> getGroups(List<String> rucksacks) {
        List<List<String>> groups = new ArrayList<>();
        List<String> group = new ArrayList<>();
        for (int i = 0; i < rucksacks.size(); i++) {
            group.add(rucksacks.get(i));
            if ((i + 1) % 3 == 0) {
                groups.add(group);
                group = new ArrayList<>();
            }
        }

        return groups;
    }

    private static Integer getPriority(List<String> group) {

        char finding = ' ';

        String r1 = group.get(0);
        String r2 = group.get(1);
        String r3 = group.get(2);

        for (int i = 0; i < r1.length(); i++) {
            if (r2.indexOf(r1.charAt(i)) >= 0 && r3.indexOf(r1.charAt(i)) >= 0) {
                finding = r1.charAt(i);
                break;
            }
        }

        return LETTERS.indexOf(finding) + 1;
    }
}