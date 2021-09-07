package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Utilities.NavigationParameters;
import ForbiddenLandsManager.Utilities.ServiceLocator;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;



public class MainTestView extends View {

    Button showFirstViewButton = new Button("First");
    Button showSecondViewButton = new Button("Second");
    Button showAttributesButton = new Button("Attributes");
    VBox column = new VBox();

    TestViewRegion viewWithRegion = new TestViewRegion();

    public MainTestView(){
        showFirstViewButton.setOnAction((ev)->{
            ServiceLocator.getRegionManager().requestNavigate("region1", TestView1.class, new NavigationParameters());
        });

        showSecondViewButton.setOnAction((ev)->{
            ServiceLocator.getRegionManager().requestNavigate("region1", TestView2.class, new NavigationParameters());
        });

        showAttributesButton.setOnAction((ev)->{
            ServiceLocator.getRegionManager().requestNavigate("region1", CharacterSheetView.class, new NavigationParameters());
        });

        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        column.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        column.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        viewWithRegion.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        viewWithRegion.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        column.getChildren().addAll(showFirstViewButton, showSecondViewButton, showAttributesButton, viewWithRegion);
        column.prefWidthProperty().bind(this.widthProperty());
        column.prefHeightProperty().bind(this.heightProperty());;
        viewWithRegion.prefWidthProperty().bind(column.widthProperty());
        viewWithRegion.prefHeightProperty().bind(column.heightProperty().subtract(showFirstViewButton.heightProperty()).subtract(showSecondViewButton.heightProperty()).subtract(showAttributesButton.heightProperty()));
        //column.setFillWidth(true);

        this.getChildren().add(column);
        //this.setCenter(column);
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}
