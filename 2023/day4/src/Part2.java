import java.util.List;

public class Part2 {
    public Long processLines(List<String> lines) {
        return lines.stream()
                .filter(this::checkOverlappedRanges)
                .count();
    }

    private boolean checkOverlappedRanges(String s) {
        String[] parts = s.split(",");
        int elf1Ini = Integer.parseInt(parts[0].split("-")[0]);
        int elf1End = Integer.parseInt(parts[0].split("-")[1]);
        int elf2Ini = Integer.parseInt(parts[1].split("-")[0]);
        int elf2End = Integer.parseInt(parts[1].split("-")[1]);

        return ((elf1Ini >= elf2Ini && elf1Ini <= elf2End) || (elf1End >= elf2Ini && elf1End <= elf2End)) ||
                ((elf2Ini >= elf1Ini && elf2Ini <= elf1End) || (elf2End >= elf1Ini && elf2End <= elf1End));
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(4L);
    }
}