import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand implements Comparable<Hand> {
    private final static String CARD_STRENGTH = "AKQJT98765432";

    private final String cards;
    private final int bid;
    private final HandStrength handStrength;

    public Hand(String cards, String bid) {
        this.cards = cards;
        this.bid = Integer.parseInt(bid);
        this.handStrength = calculateHandType(cards);
    }

    private HandStrength calculateHandType(String cards) {
        List<Integer> counts = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < 5; i++) {
            int val = cards.length() - cards.replace(String.valueOf(cards.charAt(i)), "").length();
            counts.add(val);
            if (val > max) {
                max = val;
            }
        }

        if (max == 5) {
            return HandStrength.FIVE;
        } else if (max == 4) {
            return HandStrength.FOUR;
        } else if (max == 3) {
            if (counts.contains(2)) {
                return HandStrength.FULL_HOUSE;
            } else {
                return HandStrength.THREE;
            }
        } else if (max == 2) {
            int occurrences = Collections.frequency(counts, 2);
            if (occurrences == 4) {
                return HandStrength.TWO_PAIRS;
            } else {
                return HandStrength.ONE_PAIR;
            }
        }
        return HandStrength.HIGH_CARD;
    }

    public int getBid() {
        return bid;
    }

    public HandStrength getHandStrength() {
        return handStrength;
    }

    public String getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return cards + " " + handStrength + " " + bid;
    }

    @Override
    public boolean equals(Object other) {
        if (!other.getClass().equals(Hand.class)) {
            return false;
        }

        return this.cards.equals(((Hand) other).getCards())
                && this.handStrength.equals(((Hand) other).getHandStrength())
                && this.bid == ((Hand) other).getBid();
    }

    @Override
    public int compareTo(Hand other) {
        int result = 0;

        if (this.handStrength.strength > other.handStrength.strength) {
            result = 1;
        } else if (this.handStrength.strength < other.handStrength.strength) {
            result = -1;
        } else {
            for (int i = 0; i < this.cards.length(); i++) {
                if (this.cards.charAt(i) != other.cards.charAt(i)) {
                    result = CARD_STRENGTH.indexOf(this.cards.charAt(i)) < CARD_STRENGTH.indexOf(other.cards.charAt(i))
                            ? 1 : -1;
                    break;
                }
            }
        }

        return result;
    }

}
