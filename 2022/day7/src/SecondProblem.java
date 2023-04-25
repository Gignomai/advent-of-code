import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SecondProblem {

    private static Node root;
    private static Node currentDir;

    public static void main(String[] args) throws IOException {
        Files.lines(Path.of("src/input.txt"))
                .forEach(SecondProblem::processCommand);
        Integer free = 70000000 - root.getSize();
        Integer needed = 30000000 - free;
        /*System.out.println(needed + ">" + root.getSize());*/
        System.out.println(needed);
        System.out.println(root);
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