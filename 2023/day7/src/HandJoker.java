import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandJoker implements Comparable<HandJoker> {
    private final static String CARD_STRENGTH = "AKQT98765432J";

    private final String cards;
    private final int bid;
    private final HandStrength handStrength;

    public HandJoker(String cards, String bid) {
        this.cards = cards;
        this.bid = Integer.parseInt(bid);
        this.handStrength = calculateHandType(cards);
    }

    private HandStrength calculateHandType(String cards) {
        List<Integer> counts = new ArrayList<>();
        int max = 0;
        int jays = 0;
        for (int i = 0; i < 5; i++) {
            String card = String.valueOf(cards.charAt(i));
            if (card.equals("J")) {
                jays++;
            } else {
                int val = cards.length() - cards.replace(card, "").length();
                counts.add(val);
                if (val > max) {
                    max = val;
                }
            }
        }

        HandStrength handStrength = HandStrength.HIGH_CARD;
        if (max == 5) {
            handStrength =  HandStrength.FIVE;
        } else if (max == 4) {
            handStrength =  HandStrength.FOUR;
        } else if (max == 3) {
            if (counts.contains(2)) {
                handStrength =  HandStrength.FULL_HOUSE;
            } else {
                handStrength =  HandStrength.THREE;
            }
        } else if (max == 2) {
            int occurrences = Collections.frequency(counts, 2);
            if (occurrences == 4) {
                handStrength =  HandStrength.TWO_PAIRS;
            } else {
                handStrength =  HandStrength.ONE_PAIR;
            }
        }

        if (jays == 5) {
          handStrength = HandStrength.FIVE;
        } else if (jays > 0) {
            if (handStrength.equals(HandStrength.HIGH_CARD)) {
                handStrength = switch (jays) {
                    case 1 -> HandStrength.ONE_PAIR;
                    case 2 -> HandStrength.THREE;
                    case 3 -> HandStrength.FOUR;
                    case 4 -> HandStrength.FIVE;
                    default -> HandStrength.HIGH_CARD;
                };
            } else if (handStrength.equals(HandStrength.ONE_PAIR)) {
                handStrength = switch (jays) {
                    case 1 -> HandStrength.THREE;
                    case 2 -> HandStrength.FOUR;
                    case 3 -> HandStrength.FIVE;
                    default -> HandStrength.ONE_PAIR;
                };
            } else if (handStrength.equals(HandStrength.TWO_PAIRS)) {
                handStrength = switch (jays) {
                    case 1 -> HandStrength.FULL_HOUSE;
                    default -> HandStrength.TWO_PAIRS;
                };
            } else if (handStrength.equals(HandStrength.THREE)) {
                handStrength = switch (jays) {
                    case 1 -> HandStrength.FOUR;
                    case 2 -> HandStrength.FIVE;
                    default -> HandStrength.THREE;
                };
            } else if (handStrength.equals(HandStrength.FOUR)) {
                handStrength = switch (jays) {
                    case 1 -> HandStrength.FIVE;
                    default -> HandStrength.FOUR;
                };
            }
        }
        return handStrength;
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
        if (!other.getClass().equals(HandJoker.class)) {
            return false;
        }

        return this.cards.equals(((HandJoker) other).getCards())
                && this.handStrength.equals(((HandJoker) other).getHandStrength())
                && this.bid == ((HandJoker) other).getBid();
    }

    @Override
    public int compareTo(HandJoker other) {
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
