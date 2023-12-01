import java.util.List;

public class Part2 {

    public static final int TOTAL_DEVICE_DISK_SPACE = 70000000;
    public static final int SYSTEM_UPDATE_DISK_USAGE = 30000000;

    public Integer processLines(List<String> lines) {
        Node root = Node.getNodeTreeFromCommandList(lines);
        int free = TOTAL_DEVICE_DISK_SPACE - root.getSize();
        Integer needed = SYSTEM_UPDATE_DISK_USAGE - free;

        return getMinSizeViableDir(root, needed);
    }

    private Integer getMinSizeViableDir(Node node, Integer needed) {
        if (node.getChildren().isEmpty()) {
            return node.getSize();
        }

        Integer result = node.getSize();
        for (Node n: node.getChildren()) {
            Integer childSize = getMinSizeViableDir(n, needed);
            if (childSize > needed && childSize < result) {
                result = childSize;
            }
        }

        return result;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(24933642);
    }
}