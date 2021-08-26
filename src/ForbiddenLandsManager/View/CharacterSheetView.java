package ForbiddenLandsManager.View;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CharacterSheetView extends View{
    GridPane characterSheetGrid = new GridPane();
    AttributesView attributesView = new AttributesView();
    ConditionsView conditionsView = new ConditionsView();
    CriticalInjuriesView criticalInjuriesView = new CriticalInjuriesView();
    SkillsView skillsView = new SkillsView();
    PrideSecretView prideSecretView = new PrideSecretView();
    ArmorView armorView = new ArmorView();


    public CharacterSheetView() {
        characterSheetGrid.add(attributesView, 0, 0);
        characterSheetGrid.add(conditionsView, 0, 1);
        characterSheetGrid.add(criticalInjuriesView, 0, 2);
        characterSheetGrid.add(skillsView, 0, 3);

        characterSheetGrid.add(prideSecretView, 1, 0, 1, 2);
        //characterSheetGrid.add(new AttributesView(), 1, 1);
        characterSheetGrid.add(armorView, 1, 2, 1, 1);
        //characterSheetGrid.add(new AttributesView(), 1, 3);

        characterSheetGrid.add(new AttributesView(), 2, 0);
        characterSheetGrid.add(new AttributesView(), 2, 1);
        //characterSheetGrid.add(new AttributesView(), 2, 2);
        //characterSheetGrid.add(new AttributesView(), 2, 3);

        ColumnConstraints col1 = new ColumnConstraints();
        ColumnConstraints col2 = new ColumnConstraints();
        ColumnConstraints col3 = new ColumnConstraints();
        col1.setPercentWidth(33);
        col1.setMinWidth(300);
        col2.setPercentWidth(33);
        col2.setMinWidth(300);
        col3.setPercentWidth(34);
        col3.setMinWidth(300);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        RowConstraints row4 = new RowConstraints();
        row1.setFillHeight(true);
        row1.setMinHeight(70);
        //row1.setMaxHeight(70);
        row2.setFillHeight(true);
        row2.setMinHeight(70);
        row2.minHeightProperty().bind(prideSecretView.heightProperty().subtract(row1.getMinHeight()));
        //row2.setMaxHeight(70);
        row3.setFillHeight(true);
        row3.setMinHeight(200);
        row3.setMaxHeight(300);
        row3.setVgrow(Priority.ALWAYS);
        row4.setFillHeight(true);
        row4.setMinHeight(200);
        row4.setVgrow(Priority.ALWAYS);

        characterSheetGrid.getColumnConstraints().addAll(col1, col2, col3);
        characterSheetGrid.getRowConstraints().addAll(row1, row2, row3, row4);
        characterSheetGrid.setGridLinesVisible(true);
        characterSheetGrid.prefWidthProperty().bind(this.widthProperty());
        characterSheetGrid.prefHeightProperty().bind(this.heightProperty());
        characterSheetGrid.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        characterSheetGrid.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(characterSheetGrid);

    }

    @Override
    protected void postSetDataContextUpdates() {
        attributesView.setDataContext(this.dataContext);
        conditionsView.setDataContext(this.dataContext);
        criticalInjuriesView.setDataContext(this.dataContext);
        skillsView.setDataContext(this.dataContext);
        prideSecretView.setDataContext(this.dataContext);
        armorView.setDataContext(this.dataContext);
    }

    @Override
    protected void registerBindings() {

    }
}
