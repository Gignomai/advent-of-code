import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SecondProblem {

    public static void main(String[] args) throws IOException {
        try (var lines = Files.lines(Path.of("src/input.txt"))) {
            System.out.println("lines = " + lines);
        }
    }

}