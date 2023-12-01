import java.util.ArrayList;
import java.util.List;


public class Part2 {

    public Long processLines(List<String> lines) {
        return getGroups(lines).stream()
                .mapToLong(this::getPriority)
                .sum();

    }

    private List<List<String>> getGroups(List<String> rucksacks) {
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

    private Integer getPriority(List<String> group) {

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

        return Day3.LETTERS.indexOf(finding) + 1;
    }

    public boolean test(List<String> fileName) {
        return processLines(fileName).equals(70L);
    }
}