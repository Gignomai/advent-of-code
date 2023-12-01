import java.util.List;

public class Part2 {
    public Long processLines(List<String> lines) {
        return lines.stream()
                .map(this::searchFirstStartOfPacketMarker)
                .findFirst()
                .orElse(0L);
    }

    private long searchFirstStartOfPacketMarker(String s) {
        long position = 0L;

        for (int i = 0; i < s.length(); i++) {
            if (hasDuplicatedLetters(s.substring(i, i + 14))) {
                position = i + 14;
                break;
            }
        }

        return position;
    }

    private boolean hasDuplicatedLetters(String letters) {
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

    public boolean test(List<String> lines) {
        return processLines(lines).equals(19L);
    }
}