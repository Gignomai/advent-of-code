import model.Grid;
import model.Route;

import java.util.Arrays;
import java.util.List;

public class Part2 {
    public Integer processLines(List<String> lines) {
        int loops = 0;

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.getFirst().length(); j++) {
                List<String> copy = new java.util.ArrayList<>(lines);
                char[] charArray = copy.get(i).toCharArray();
                if (charArray[j] == '.') {
                    charArray[j] = '#';

                    copy.set(i, new String(charArray));
                    Route route = new Route(new Grid(copy));

                    if (route.hasLoops()) {
                        loops++;
                    }
                }
            }
        }
        System.out.println("loops = " + loops);
        return loops;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(6);
    }
}