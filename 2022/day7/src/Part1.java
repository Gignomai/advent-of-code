import java.util.List;

public class Part1 {

    public static final int DIR_MIN_SIZE = 100000;

    public Integer processLines(List<String> lines){
        return calculateTotal(Node.getNodeTreeFromCommandList(lines));
    }

    private int calculateTotal(Node node) {
        if (node.getChildren().isEmpty()) {
            if (node.getSize() <= DIR_MIN_SIZE) {
                return node.getSize();
            } else {
                return 0;
            }
        }

        Integer result = 0;
        if (node.getSize() <= DIR_MIN_SIZE) {
            result = node.getSize();
        }

        return result + node.getChildren().stream()
                .mapToInt(this::calculateTotal)
                .sum();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(95437);
    }
}