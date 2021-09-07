package ForbiddenLandsManager.View;


import ForbiddenLandsManager.Model.Injury;
import ForbiddenLandsManager.Utilities.CommandParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

public class CriticalInjuriesView extends View{

    VBox injuriesVBox = new VBox();
    ListView<Injury> injuriesListView = new ListView<>();
    Button addNewItemButton = new Button("New injury!");




    public CriticalInjuriesView(){
        injuriesListView.setCellFactory(cell -> new ListCell<Injury>(){

            // We want to create a single Tooltip that will be reused, as needed. We will simply update the text
            // for the Tooltip for each cell
            final Tooltip tooltip = new Tooltip();

            // We will override the updateItem() method of the ListCell so we can setup exactly what we want displayed
            // in each cell.  For this example, we'll just use the text of the cell, but you could also provide your
            // own layout entirely, by calling setGraphic() instead of the setText() used below.
            @Override
            protected void updateItem(Injury injury, boolean empty) {
                super.updateItem(injury, empty);

                if (injury == null || empty) {
                    // No book to display here (empty cell) so we clear the text and Tooltip
                    setText(null);
                    setTooltip(null);
                } else {
                    // A book is to be listed in this cell
                    setText(injury.injury);

                    // Let's show our Author when the user hovers the mouse cursor over this row
                    tooltip.setText(injury.effectDescription);
                    setTooltip(tooltip);
                }
            }
        });

        injuriesVBox.getChildren().addAll(injuriesListView, addNewItemButton);
        this.getChildren().add(injuriesVBox);
        injuriesListView.prefHeightProperty().bind(this.heightProperty().subtract(addNewItemButton.heightProperty()));
        injuriesListView.prefWidthProperty().bind(this.widthProperty());

        addNewItemButton.setOnAction((ev) -> {
            callCommand("addNewInjury", new CommandParameters());
        });
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {
        this.propertyBindingMap.put(injuriesListView.itemsProperty(), "injuriesListPropertyProperty");
    }
}
