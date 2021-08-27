package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Model.Skill;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class WeaponsView extends View {

    VBox weaponsVBox = new VBox();
    TableView<Skill> weaponsTable = new TableView<>();
    Button addNewWeaponButton = new Button("Add new");
    Button removeWeaponButton = new Button("Remove selected");

    public WeaponsView() {
        TableColumn<Skill, String> weaponNameColumn = new TableColumn<>("Weapon name");
        TableColumn<Skill, String> gearBonusColumn = new TableColumn<>("Gear bonus");
        TableColumn<Skill, String> damageColumn = new TableColumn<>("Damage");
        TableColumn<Skill, String> featureColumn = new TableColumn<>("Features");
        TableColumn<Skill, String> commentColumn = new TableColumn<>("Comment");

        weaponNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        gearBonusColumn.setCellValueFactory(new PropertyValueFactory<>("gearBonus"));
        damageColumn.setCellValueFactory(new PropertyValueFactory<>("damage"));
        featureColumn.setCellValueFactory(new PropertyValueFactory<>("features"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        weaponsTable.getColumns().addAll(weaponNameColumn, gearBonusColumn, damageColumn, featureColumn, commentColumn);
        weaponsTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        weaponsVBox.getChildren().addAll(weaponsTable, addNewWeaponButton, removeWeaponButton);
        this.getChildren().add(weaponsVBox);
        weaponsVBox.prefHeightProperty().bind(this.heightProperty());
        weaponsVBox.prefWidthProperty().bind(this.widthProperty());
        weaponsTable.prefHeightProperty().bind(weaponsVBox.heightProperty());
        weaponsTable.prefWidthProperty().bind(weaponsVBox.widthProperty());
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {
        this.propertyBindingMap.put(weaponsTable.itemsProperty(), "weaponsListPropertyProperty");
    }
}
