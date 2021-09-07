package ForbiddenLandsManager.View;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ArmorView extends View{
    Label helmetNameLabel = new Label("Helmet name: ");
    Label shieldNameLabel = new Label("Shield name: ");
    Label armorNameLabel = new Label("Armor name: ");

    TextArea helmetNameText = new TextArea();
    TextArea shieldNameText = new TextArea();
    TextArea armorNameText = new TextArea();

    Label helmetRatingLabel = new Label("Helmet rating: ");
    Label shieldRatingLabel = new Label("Shield rating: ");
    Label armorRatingLabel = new Label("Armor rating: ");

    TextField helmetRatingText = new TextField();
    TextField shieldRatingText = new TextField();
    TextField armorRatingText = new TextField();

    GridPane armorGridPane = new GridPane();

    @Override
    protected void postSetDataContextUpdates() {
        armorGridPane.addRow(0, helmetNameLabel, helmetRatingLabel);
        armorGridPane.addRow(1, helmetNameText, helmetRatingText);
        armorGridPane.addRow(2, shieldNameLabel, shieldRatingLabel);
        armorGridPane.addRow(3, shieldNameText, shieldRatingText);
        armorGridPane.addRow(4, armorNameLabel, armorRatingLabel);
        armorGridPane.addRow(5, armorNameText, armorRatingText);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(65);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(35);

        armorGridPane.getColumnConstraints().addAll(col1, col2);

        this.getChildren().add(armorGridPane);
        armorGridPane.prefWidthProperty().bind(this.widthProperty());
        armorGridPane.prefHeightProperty().bind(this.heightProperty());


        armorGridPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }

    @Override
    protected void registerBindings() {

    }
}
