package ForbiddenLandsManager.Model;

import javafx.beans.property.SimpleStringProperty;

import java.awt.*;

public class Character {

    private final SimpleStringProperty firstName;
    private final Point position;

    public Character(String fName, Point position) {
        this.firstName = new SimpleStringProperty(fName);
        this.position = position;

    }

    public Point getPosition() {
        return position;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String fName) {
        firstName.set(fName);
    }
}

