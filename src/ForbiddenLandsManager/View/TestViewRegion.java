package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Utilities.Region;
import ForbiddenLandsManager.Utilities.RegionManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestViewRegion extends View{
    Label regionLabel = new Label("region view");
    Region region1 = new Region("region1", RegionManager.getInstance());
    VBox vbox = new VBox();


    public TestViewRegion(){
        this.getChildren().add(vbox);
        vbox.getChildren().addAll(regionLabel, region1);
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}
