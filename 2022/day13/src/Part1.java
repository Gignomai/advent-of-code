import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class Part1 {
    public Integer processLines(List<String> lines) {
        List<Packet> packets = getPacketsFromLines(lines);
        int total = 0;
        int index = 1;
        for (Packet packet : packets) {
            if (isInRightOrder(packet)) {
                total += index;
            }
            index++;
        }
        System.out.println("total = " + total);
        return total;
    }

    private List<Packet> getPacketsFromLines(List<String> lines) {
        List<Packet> packets = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).isEmpty()) {
                packets.add(new Packet(lines.get(i - 2), lines.get(i - 1)));
            }
        }
        return packets;
    }

    private boolean isInRightOrder(Packet packet) {
        List<String> leftElements = getElementsOfList(packet.left());
        List<String> rightElements = getElementsOfList(packet.right());

        return compareElements(leftElements, rightElements);
    }

    private List<String> getElementsOfList(String part) {
        String listContent = part.substring(1, part.length() - 1);
        if (listContent.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(listContent.split(",(?![^\\[]*\\])")).toList();
    }

    private boolean compareElements(List<String> leftElements, List<String> rightElements) {
        if (leftElements.isEmpty() && !rightElements.isEmpty()) {
            return true;
        }
        if (rightElements.isEmpty() && !leftElements.isEmpty()) {
            return false;
        }

        String leftElement = leftElements.get(0);
        String rightElement = rightElements.get(0);
        System.out.println("rightElement = " + rightElement);
        System.out.println("leftElement = " + leftElement);

        try {
            int left = Integer.parseInt(leftElement);
            int right = Integer.parseInt(rightElement);

            if (left < right) {
                return true;
            }
            if (left > right) {
                return false;
            }
        } catch (NumberFormatException e) {
            if (leftElement.startsWith("[") && !rightElement.startsWith("[")) {
                rightElement = "[" + rightElement + "]";
            }
            if (rightElement.startsWith("[") && !leftElement.startsWith("[")) {
                leftElement = "[" + leftElement + "]";
            }

            if (leftElement.startsWith("[") && rightElement.startsWith("[")) {
                return compareElements(getElementsOfList(leftElement), getElementsOfList(rightElement));
            }
        }

        return compareElements(leftElements.subList(1, leftElements.size() - 1), rightElements.subList(1, rightElements.size() - 1));
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(13);
    }
}
