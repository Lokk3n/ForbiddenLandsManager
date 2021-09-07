package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Model.Skill;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class SkillsView extends View{

    VBox skillVBox = new VBox();
    TableView<Skill> skillTable = new TableView<>();

    public SkillsView() {
        TableColumn<Skill, String> skillNameColumn = new TableColumn<>("Skill");
        TableColumn<Skill, String> attributeColumn = new TableColumn<>("Related to");
        TableColumn<Skill, String> valueColumn = new TableColumn<>("Value");

        skillNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        attributeColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        skillTable.getColumns().addAll(skillNameColumn, attributeColumn, valueColumn);
        skillTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        skillVBox.getChildren().add(skillTable);
        this.getChildren().add(skillVBox);
        skillVBox.prefHeightProperty().bind(this.heightProperty());
        skillVBox.prefWidthProperty().bind(this.widthProperty());
        skillTable.prefHeightProperty().bind(skillVBox.heightProperty());
        skillTable.prefWidthProperty().bind(skillVBox.widthProperty());
    }


    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {
        this.propertyBindingMap.put(skillTable.itemsProperty(), "skillsListPropertyProperty");
    }
}
