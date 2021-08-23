package ForbiddenLandsManager.View;

import javafx.scene.layout.GridPane;

public class CharacterSheetView extends View{
    GridPane characterSheetGrid = new GridPane();
    AttributesView attributesView = new AttributesView();
    ConditionsView conditionsView = new ConditionsView();

    public CharacterSheetView() {
        characterSheetGrid.add(attributesView, 0, 1);
        characterSheetGrid.add(conditionsView, 0, 2);
        this.getChildren().add(characterSheetGrid);
    }

    @Override
    protected void postSetDataContextUpdates() {
        attributesView.setDataContext(this.dataContext);
        conditionsView.setDataContext(this.dataContext);
    }

    @Override
    protected void registerBindings() {

    }
}
