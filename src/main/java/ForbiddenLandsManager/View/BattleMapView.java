package ForbiddenLandsManager.View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BattleMapView extends View{

    HBox row = new HBox();
    Label test = new Label("Test");
    Label test2 = new Label("Test2");

    public BattleMapView() {
        row.getChildren().addAll(test, test2);
        this.getChildren().add(row);
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}
