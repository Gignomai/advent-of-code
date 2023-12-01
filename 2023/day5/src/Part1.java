import java.util.List;

public class Part1 {
    public String processLines(List<String> lines) {
        return new SupplyCargo(lines).crateMover9000Operate();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals("CMZ");
    }
}