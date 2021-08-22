package ForbiddenLandsManager.View;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AttributesView extends View {

    //Attributes
    Label strengthLabel = new Label("Strength");
    Label agilityLabel = new Label("Agility");
    Label witsLabel = new Label("Wits");
    Label empathyLabel = new Label("Empathy");
    CheckBox[] strengthCheckboxes = new CheckBox[6];
    CheckBox[] agilityCheckboxes = new CheckBox[6];
    CheckBox[] witsCheckboxes = new CheckBox[6];
    CheckBox[] empathyCheckboxes = new CheckBox[6];
    IntegerProperty strengthValue = new SimpleIntegerProperty();
    IntegerProperty agilityValue = new SimpleIntegerProperty();
    IntegerProperty witsValue = new SimpleIntegerProperty();
    IntegerProperty empathyValue = new SimpleIntegerProperty();


    GridPane attributeGrid = new GridPane();

    public AttributesView(){
        //Attributes
        attributeGrid.add(strengthLabel, 0, 0);
        attributeGrid.add(agilityLabel, 0, 1);
        attributeGrid.add(witsLabel, 0, 2);
        attributeGrid.add(empathyLabel, 0, 3);

        for(int i = 0; i < 6; i++){
            strengthCheckboxes[i] = new CheckBox();
            agilityCheckboxes[i] = new CheckBox();
            witsCheckboxes[i] = new CheckBox();
            empathyCheckboxes[i] = new CheckBox();
        }
        attributeGrid.add(strengthCheckboxes[0], 1, 0);
        attributeGrid.add(strengthCheckboxes[1], 2, 0);
        attributeGrid.add(strengthCheckboxes[2], 3, 0);
        attributeGrid.add(strengthCheckboxes[3], 4, 0);
        attributeGrid.add(strengthCheckboxes[4], 5, 0);
        attributeGrid.add(strengthCheckboxes[5], 6, 0);

        attributeGrid.add(agilityCheckboxes[0], 1, 1);
        attributeGrid.add(agilityCheckboxes[1], 2, 1);
        attributeGrid.add(agilityCheckboxes[2], 3, 1);
        attributeGrid.add(agilityCheckboxes[3], 4, 1);
        attributeGrid.add(agilityCheckboxes[4], 5, 1);
        attributeGrid.add(agilityCheckboxes[5], 6, 1);

        attributeGrid.add(witsCheckboxes[0], 1, 2);
        attributeGrid.add(witsCheckboxes[1], 2, 2);
        attributeGrid.add(witsCheckboxes[2], 3, 2);
        attributeGrid.add(witsCheckboxes[3], 4, 2);
        attributeGrid.add(witsCheckboxes[4], 5, 2);
        attributeGrid.add(witsCheckboxes[5], 6, 2);

        attributeGrid.add(empathyCheckboxes[0], 1, 3);
        attributeGrid.add(empathyCheckboxes[1], 2, 3);
        attributeGrid.add(empathyCheckboxes[2], 3, 3);
        attributeGrid.add(empathyCheckboxes[3], 4, 3);
        attributeGrid.add(empathyCheckboxes[4], 5, 3);
        attributeGrid.add(empathyCheckboxes[5], 6, 3);

        registerCheckboxActions(strengthCheckboxes);
        registerCheckboxActions(agilityCheckboxes);
        registerCheckboxActions(witsCheckboxes);
        registerCheckboxActions(empathyCheckboxes);

        this.getChildren().add(attributeGrid);
    }

    void registerCheckboxActions(CheckBox[] checkBoxes){
        for(int i = 0; i < checkBoxes.length; i++){
            checkBoxes[i].setOnAction((ev)->{
                int index = 0;
                for(int j = 0; j < checkBoxes.length; j++){
                    if(ev.getSource().equals(checkBoxes[j])){
                        index = j;
                        break;
                    }
                }
                for(int j = 0; j < checkBoxes.length; j++){
                    checkBoxes[j].setSelected(j <= index);
                }
            });
        }
    }
}
