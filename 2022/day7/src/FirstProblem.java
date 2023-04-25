import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class Node {
    private Directory directory;
    private final List<Node> children = new ArrayList<>();
    private final Node parent;

    public Node(Node parent) {
        this.parent = parent;
    }

    public Directory getDirectory() {
        return directory;
    }

    public void setDirectory(Directory directory) {
        this.directory = directory;
    }

    public List<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    @Override
    public String toString() {
        if (this.getSize() > 4376732) {
            return this.getDirectory().name() + " - " + this.getSize() + " \n\t" + this.getChildren().toString();
        } else return "";
    }

    public Integer getSize() {
        return this.getDirectory().getSize() + this.getChildren().stream()
                .map(Node::getSize)
                .mapToInt(Integer::intValue)
                .sum();
    }

}

record Directory(String name, List<File> files) {
    public Integer getSize() {
        return files.stream()
                .mapToInt(File::size)
                .sum();
    }
}

record File(String name, Integer size) {
}

public class FirstProblem {

    private static Node root;
    private static Node currentDir;

    public static void main(String[] args) throws IOException {
        Files.lines(Path.of("src/input.txt"))
                .forEach(FirstProblem::processCommand);

        System.out.println(calculateTotal(root));
    }

    private static int calculateTotal(Node node) {
        if (node.getChildren().isEmpty()) {
            if (node.getSize() <= 100000) {
                return node.getSize();
            } else {
                return 0;
            }
        }
        Integer result = 0;

        if (node.getSize() <= 100000) {
            result = node.getSize();
        }
        return result + node.getChildren().stream()
                .mapToInt(FirstProblem::calculateTotal)
                .sum();
    }

    private static void processCommand(String command) {
        if (command.startsWith("$")) {
            if (command.startsWith("$ cd /")) {
                Directory directory = new Directory("/", new ArrayList<>());
                root = new Node(null);
                root.setDirectory(directory);
                currentDir = root;
            } else if (command.startsWith("$ cd ..")) {
                if (currentDir.getParent() != null) {
                    currentDir = currentDir.getParent();
                }
            } else if (command.startsWith("$ cd ")) {
                String name = command.replace("$ cd ", "");
                currentDir = currentDir.getChildren().stream()
                        .filter(node -> node.getDirectory().name().equals(name))
                        .toList().get(0);
            }
        } else {
            if (command.startsWith("dir")) {
                String name = command.replace("dir ", "");
                Directory directory = new Directory(name, new ArrayList<>());
                Node node = new Node(currentDir);
                node.setDirectory(directory);
                currentDir.getChildren().add(node);
            } else {
                String[] parts = command.split(" ");
                File file = new File(parts[1], Integer.parseInt(parts[0]));
                currentDir.getDirectory().files().add(file);
            }
        }
    }

}