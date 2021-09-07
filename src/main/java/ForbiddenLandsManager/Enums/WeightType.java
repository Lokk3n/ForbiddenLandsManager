package ForbiddenLandsManager.Enums;

public enum WeightType {
    PETTY(1),
    SMALL(2),
    COMMON(3),
    LARGE(4),
    GREAT(5);

    public int getWeight() {
        return weight;
    }

    private final int weight;

    WeightType(int i) {
        this.weight = i;
    }
}
