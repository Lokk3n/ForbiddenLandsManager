package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Utilities.NavigationParameters;
import ForbiddenLandsManager.Utilities.ServiceLocator;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class MainTestView extends Pane {

    Button showFirstViewButton = new Button("First");
    Button showSecondViewButton = new Button("Second");
    Button showAttributesButton = new Button("Attributes");
    VBox column = new VBox();

    public MainTestView(){
        showFirstViewButton.setOnAction((ev)->{
            ServiceLocator.getRegionManager().requestNavigate("region1", TestView1.class, new NavigationParameters());
        });

        showSecondViewButton.setOnAction((ev)->{
            ServiceLocator.getRegionManager().requestNavigate("region1", TestView2.class, new NavigationParameters());
        });

        showAttributesButton.setOnAction((ev)->{
            ServiceLocator.getRegionManager().requestNavigate("region1", AttributesView.class, new NavigationParameters());
        });

        column.getChildren().addAll(showFirstViewButton, showSecondViewButton, showAttributesButton, new TestViewRegion());

        this.getChildren().add(column);
    }
}
