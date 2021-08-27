package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Model.Talent;
import ForbiddenLandsManager.Utilities.CommandParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;

public class TalentsView extends View{

    VBox talentsVbox = new VBox();
    ListView<Talent> talentsListView = new ListView<>();
    Button addNewItemButton = new Button("New talent!");




    public TalentsView(){
        talentsListView.setCellFactory(cell -> new ListCell<Talent>(){

            // We want to create a single Tooltip that will be reused, as needed. We will simply update the text
            // for the Tooltip for each cell
            final Tooltip tooltip = new Tooltip();

            // We will override the updateItem() method of the ListCell so we can setup exactly what we want displayed
            // in each cell.  For this example, we'll just use the text of the cell, but you could also provide your
            // own layout entirely, by calling setGraphic() instead of the setText() used below.
            @Override
            protected void updateItem(Talent talent, boolean empty) {
                super.updateItem(talent, empty);

                if (talent == null || empty) {
                    // No book to display here (empty cell) so we clear the text and Tooltip
                    setText(null);
                    setTooltip(null);
                } else {
                    // A book is to be listed in this cell
                    setText(talent.talent);

                    // Let's show our Author when the user hovers the mouse cursor over this row
                    tooltip.setText(talent.effectDescription);
                    setTooltip(tooltip);
                }
            }
        });

        talentsVbox.getChildren().addAll(talentsListView, addNewItemButton);
        this.getChildren().add(talentsVbox);
        talentsListView.prefHeightProperty().bind(this.heightProperty().subtract(addNewItemButton.heightProperty()));
        talentsListView.prefWidthProperty().bind(this.widthProperty());

        addNewItemButton.setOnAction((ev) -> {
            callCommand("addNewTalent", new CommandParameters());
        });
    }


    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {
        this.propertyBindingMap.put(talentsListView.itemsProperty(), "talentsListPropertyProperty");
    }
}
