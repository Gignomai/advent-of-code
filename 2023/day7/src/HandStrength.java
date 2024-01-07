public enum HandStrength {
    HIGH_CARD(1),
    ONE_PAIR(2),
    TWO_PAIRS(3),
    THREE(4),
    FULL_HOUSE(5),
    FOUR(6),
    FIVE(7);

    public final int strength;

    HandStrength(int strength) {
        this.strength = strength;
    }
}
