import java.util.ArrayList;
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
        List<String> elements = new ArrayList<>();
        int firstElementIndex = part.indexOf("[");
        int lastElementIndex = part.lastIndexOf("]");

        String element = part.substring(firstElementIndex + 1, lastElementIndex);

        for(char c: element.toCharArray()) {

        }

        System.out.println("element = " + element);

        return elements;
    }

    private boolean compareElements(List<String> leftElements, List<String> rightElements) {
//        for (String s : leftElements) {
//            System.out.println("s = " + s);
//        }
//        for (String s : rightElements) {
//            System.out.println("s = " + s);
//        }
        return false;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(13);
    }
}
