package ForbiddenLandsManager.View;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;



public class ConditionsView extends View{
    GridPane conditionsGrid = new GridPane();

    Label sleeplessLabel = new Label("Sleepless");
    CheckBox sleeplessCheckbox = new CheckBox();
    Label thirstyLabel = new Label("Thirsty");
    CheckBox thirstyCheckbox = new CheckBox();
    Label hungryLabel = new Label("Hungry");
    CheckBox hungryCheckbox = new CheckBox();
    Label coldLabel = new Label("Cold");
    CheckBox coldCheckbox = new CheckBox();

    public ConditionsView() {
        conditionsGrid.add(sleeplessLabel, 0, 0);
        conditionsGrid.add(thirstyLabel, 0, 1);
        conditionsGrid.add(hungryLabel, 0, 2);
        conditionsGrid.add(coldLabel, 0, 3);

        conditionsGrid.add(sleeplessCheckbox, 1, 0);
        conditionsGrid.add(thirstyCheckbox, 1, 1);
        conditionsGrid.add(hungryCheckbox, 1, 2);
        conditionsGrid.add(coldCheckbox, 1, 3);

        this.getChildren().add(conditionsGrid);
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}
