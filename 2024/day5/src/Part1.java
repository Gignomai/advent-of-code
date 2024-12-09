import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Part1 {
    public Integer processLines(List<String> lines) {
        int total = 0;
        List<String> rules = new ArrayList<>();
        List<List<String>> updates = new ArrayList<>();

        for (String line : lines) {
            if (line.contains("|")) {
                rules.add(line);
            }

            if (line.contains(",")) {
                updates.add(List.of(line.split(",")));
            }
        }

        for (var update : updates) {
            List<String> wantedRules = new ArrayList<>();
            for (int i = 0; i < update.size(); i++) {
                for (int j = 0; j < update.size(); j++) {
                    if (j > i) {
                        wantedRules.add(update.get(i) + "|" + update.get(j));
                    } else if (j < i) {
                        wantedRules.add(update.get(j) + "|" + update.get(i));
                    }
                }
            }

            if (new HashSet<>(rules).containsAll(wantedRules)) {
                int val = Integer.parseInt(update.get((update.size() / 2)));
                total += val;
            }
        }


        return total;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(143);
    }
}