package ForbiddenLandsManager.Model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class CircleCharacter extends Circle {
    private final Character chosenChar;

    public CircleCharacter(double v, double v1, double v2, Paint paint, Character chosenChar) {
        super(v, v1, v2, paint);

        this.chosenChar = chosenChar;
    }

    public Character getChosenChar() {
        return chosenChar;
    }




}
