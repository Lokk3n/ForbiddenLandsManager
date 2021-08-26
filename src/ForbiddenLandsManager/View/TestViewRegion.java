package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Utilities.Region;
import ForbiddenLandsManager.Utilities.RegionManager;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestViewRegion extends View{
    Region region1 = new Region("region1", RegionManager.getInstance());


    public TestViewRegion(){
        this.getChildren().add(region1);
        region1.prefWidthProperty().bind(this.widthProperty());
        region1.prefHeightProperty().bind(this.heightProperty());
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}
