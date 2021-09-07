package ForbiddenLandsManager.Model;

import ForbiddenLandsManager.Enums.AttributeType;

public class Attribute {
    String name;
    int value;
    AttributeType type;

    public Attribute(String name, int value, AttributeType type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }
}
