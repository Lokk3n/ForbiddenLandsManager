package ForbiddenLandsManager.View;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PrideSecretView extends View {

    Label prideLabel = new Label("Pride:");
    Label darkSecretLabel = new Label("Dark Secret: ");
    Label ageLabel = new Label("Age:");
    Label reputationLabel = new Label(("Reputation:"));

    TextArea prideText = new TextArea();
    TextArea darkSecretText = new TextArea();
    TextField ageText = new TextField();
    TextField reputationText = new TextField();

    VBox column = new VBox();

    public PrideSecretView(){
        column.getChildren().addAll(prideLabel, prideText, darkSecretLabel, darkSecretText, ageLabel, ageText, reputationLabel, reputationText);
        prideText.setMinHeight(30);
        prideText.setMaxHeight(100);
        darkSecretText.setMinHeight(30);
        darkSecretText.setMaxHeight(100);


        prideText.setText("My code always compiles");
        darkSecretText.setText("Brak pomysłu");

        this.getChildren().addAll(column);
        column.prefHeightProperty().bind(this.heightProperty());
        column.prefWidthProperty().bind(this.widthProperty());
        this.setMinHeight(350);
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}
