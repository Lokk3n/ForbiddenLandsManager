package ForbiddenLandsManager.View;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class AppearanceView extends View{

    Label faceLabel = new Label("Face:");
    Label bodyLabel = new Label("Body:");
    Label clothingLabel = new Label("Clothing:");

    TextArea faceTextArea = new TextArea();
    TextArea bodyTextArea = new TextArea();
    TextArea clothingTextArea = new TextArea();

    VBox column = new VBox();

    public AppearanceView(){
        column.getChildren().addAll(faceLabel, faceTextArea, bodyLabel, bodyTextArea, clothingLabel, clothingTextArea);
        this.getChildren().addAll(column);
        column.prefWidthProperty().bind(this.widthProperty());
        column.prefHeightProperty().bind(this.heightProperty());
        faceTextArea.setWrapText(true);
        bodyTextArea.setWrapText(true);
        clothingTextArea.setWrapText(true);
        faceTextArea.prefHeightProperty().bind(
                column.heightProperty()
                .subtract(faceLabel.heightProperty())
                .subtract(bodyLabel.heightProperty())
                .subtract(clothingLabel.heightProperty())
                .divide(3)
        );
        bodyTextArea.prefHeightProperty().bind(faceTextArea.heightProperty());
        clothingTextArea.prefHeightProperty().bind(faceTextArea.heightProperty());

        column.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, new CornerRadii(20), BorderWidths.DEFAULT)));
        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, new CornerRadii(20), BorderWidths.DEFAULT)));
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}
