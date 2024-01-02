import java.util.List;

public class Part2 {
    public Long processLines(List<String> lines) {
        long time = Long.parseLong(lines.get(0).replace(" ", "").split(":")[1]);
        long distance = Long.parseLong(lines.get(1).replace(" ", "").split(":")[1]);
        long count = 0;

        for (int i = 1; i < time; i++) {
            if (i * (time - i) > distance) {
                count++;
            }
        }

        return count;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(71503L);
    }
}