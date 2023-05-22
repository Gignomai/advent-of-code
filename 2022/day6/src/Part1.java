import java.util.List;

public class Part1 {
    public Long processLines(List<String> lines) {
        return lines.stream()
                .mapToLong(this::searchFirstStartOfPacketMarker)
                .findFirst()
                .orElse(0L);
    }

    private long searchFirstStartOfPacketMarker(String s) {
        long position = 0L;

        for (int i = 0; i < s.length(); i++) {
            if (isMarker(s.substring(i, i + 4))) {
                position = i + 4;
                break;
            }
        }

        return position;
    }

    private boolean isMarker(String slice) {
        char[] part = slice.toCharArray();
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (part[i] == part[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(7L);
    }

}