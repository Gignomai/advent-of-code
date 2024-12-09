import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Part2 {
    public Integer processLines(List<String> lines) {
        int total = 0;
        List<String> rules = new ArrayList<>();
        ArrayList<ArrayList<String>> updates = new ArrayList<>();

        for (String line : lines) {
            if (line.contains("|")) {
                rules.add(line);
            }

            if (line.contains(",")) {
                updates.add(new ArrayList<>(List.of(line.split(","))));
            }
        }
        HashSet<String> hashRules = new HashSet<>(rules);

        System.out.println("updates = " + updates);
        for (var update : updates) {
            if (!hashRules.containsAll(getWantedRules(update))) {
                System.out.println("PRE " + update);

                while (!hashRules.containsAll(getWantedRules(update))) {
                    for (int i = 0; i < update.size() - 1; i++) {
                        if (!hashRules.contains(update.get(i) + "|" + update.get(i + 1))) {
                            String aux1 = update.get(i);
                            String aux2 = update.get(i + 1);
                            update.set(i, aux2);
                            update.set(i + 1, aux1);
                        }
                    }
                }

                System.out.println("POST " + update);
                int val = Integer.parseInt(update.get((update.size() / 2)));
                System.out.println("val = " + val);
                total += val;
            }
        }


        return total;
    }

    private static List<String> getWantedRules(ArrayList<String> update) {
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
        return wantedRules;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(123);
    }
}