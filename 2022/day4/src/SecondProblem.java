import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SecondProblem {
    public static void main(String[] args) throws IOException {
        long total = Files.lines(Path.of("src/input.txt"))
                .filter(s -> SecondProblem.checkOverlapedRanges(s))
                .count();
        System.out.println("total: " + total);
    }

    private static boolean checkOverlapedRanges(String s) {
        String parts[] = s.split(",");
        int elf1Ini = Integer.parseInt(parts[0].split("-")[0]);
        int elf1End = Integer.parseInt(parts[0].split("-")[1]);
        int elf2Ini = Integer.parseInt(parts[1].split("-")[0]);
        int elf2End = Integer.parseInt(parts[1].split("-")[1]);

        return ((elf1Ini >= elf2Ini && elf1Ini <= elf2End) || (elf1End >= elf2Ini && elf1End <= elf2End)) ||
                ((elf2Ini >= elf1Ini && elf2Ini <= elf1End) || (elf2End >= elf1Ini && elf2End <= elf1End));
    }
}