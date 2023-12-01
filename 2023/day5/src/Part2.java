import java.util.List;

public class Part2 {
    public String processLines(List<String> lines) {
        return new SupplyCargo(lines).crateMover9001Operate();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals("MCD");
    }
}