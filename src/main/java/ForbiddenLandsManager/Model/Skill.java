package ForbiddenLandsManager.Model;

import ForbiddenLandsManager.Enums.AttributeType;

public class Skill {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public AttributeType getCategory() {
        return category;
    }

    public void setCategory(AttributeType category) {
        this.category = category;
    }

    public String name;
    public int value;
    AttributeType category;

    public Skill(String name, int value, AttributeType category) {
        this.name = name;
        this.value = value;
        this.category = category;
    }
}
