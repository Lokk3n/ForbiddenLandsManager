package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Model.Character;
import ForbiddenLandsManager.Model.CircleCharacter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

public class BattleMapView extends View{

    LinkedList<CircleCharacter> listOfCircles = new LinkedList<>();
    Group root = new Group();
    Point startPos = new Point(0,0);
    Boolean circleIsPressed = false;

    private TableView<Character> table = new TableView<>();
    private final ObservableList<Character> data =
            FXCollections.observableArrayList(
                    new Character("Wilczomlecz", startPos),
                    new Character("Vuko", new Point(100,200)),
                    new Character("Pinku", new Point(150, 250)),
                    new Character("S", startPos),
                    new Character("Michael", startPos));
    final HBox hb = new HBox();

    TableColumn firstNameCol = new TableColumn("First Name");
    TableColumn distanceCol = new TableColumn("Distance between characters");

    public BattleMapView() {

        Pane pane = new Pane();
        Pane boxForCharacters = new Pane();
        Pane boxForTable = new Pane();
        boxForTable.setPrefSize(450, 550);
        boxForCharacters.setPrefSize(400, 400);
        boxForTable.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        boxForCharacters.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        boxForCharacters.getChildren().add(root);
        boxForCharacters.relocate(500,0);
        pane.getChildren().addAll(boxForTable, boxForCharacters);


        // circles

        for (Character character : data){
            CircleCharacter newChar = createCircle(character.getPosition().getX(), character.getPosition().getY(), 30, Color.RED, character);
            newChar.toFront();
            listOfCircles.add(newChar);
        }

        // add the circles
        root.getChildren().addAll(listOfCircles);


        Double d1 =  0.00;
        for( int j = 0; j < listOfCircles.size(); j++){
            for( int i = 0; i < listOfCircles.size(); i++) {
                if (listOfCircles.get(i).isPressed()) {
                    CircleCharacter pressed = listOfCircles.get(i);
                    d1 = calculateDistanceBetweenPoints(pressed, listOfCircles.get(j));
                }
            }
        }
        System.out.println(d1);

        // bring the circles to the front of the lines

        table.setEditable(true);

        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory( new PropertyValueFactory<Character, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Character, String>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Character, String> t) {
                            ((Character) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setFirstName(t.getNewValue());
                        }
                    });


        distanceCol.setMinWidth(300);
        



        distanceCol.setCellFactory(TextFieldTableCell.forTableColumn());


        table.setItems(data);
        table.getColumns().addAll(firstNameCol, distanceCol);

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(distanceCol.getPrefWidth());
        addEmail.setPromptText("Distance");

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new Character(addFirstName.getText(), startPos));
                addFirstName.clear();
                addEmail.clear();
            }
        });

        hb.getChildren().addAll(addFirstName, addEmail, addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, hb);

        boxForTable.getChildren().add(vbox);

        this.getChildren().add(pane);

    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }

    double orgSceneX, orgSceneY;

    private EventHandler<MouseEvent> mousePressedEventHandler = (t) ->
    {
        System.out.println("pressed");
        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();

        // bring the clicked circle to the front

        CircleCharacter c = (CircleCharacter) (t.getSource());

        List<Line> listOfLines = new LinkedList<>();

        for(Object obj : root.getChildren()){
            if(obj instanceof Line){
                listOfLines.add((Line) obj);
                disconnect((Line) obj);
            }
        }

        root.getChildren().removeAll(listOfLines);

        for (int i = 0; i < getListOfCircles().size(); i++) {
            Line line = connect(c, getListOfCircles().get(i));
            root.getChildren().add(line);
            line.toBack();

            }

        c.toFront();
        c.requestFocus();

        DecimalFormat currency = new DecimalFormat("0.00");

        getDistanceCol().setCellValueFactory(cellData -> {
            String formattedCost = " ";

            for (CircleCharacter listOfCircle : listOfCircles) {
                formattedCost = currency.format(calculateDistanceBetweenPoints(c, listOfCircle));

            }
            return new SimpleStringProperty(formattedCost);
        });
    };

    private EventHandler<MouseEvent> mouseDraggedEventHandler = (t) ->
    {
        double offsetX = t.getSceneX() - orgSceneX;
        double offsetY = t.getSceneY() - orgSceneY;

        Circle c = (Circle) (t.getSource());

        c.setCenterX(c.getCenterX() + offsetX);
        c.setCenterY(c.getCenterY() + offsetY);

        orgSceneX = t.getSceneX();
        orgSceneY = t.getSceneY();

    };

    private CircleCharacter createCircle(double x, double y, double r, Color color, Character character)
    {
        CircleCharacter circle = new CircleCharacter(x, y, r, color, character);

        circle.setCursor(Cursor.CROSSHAIR);

//        circle.setOnMouseClicked(mouseClickedEventHandler);
        circle.setOnMousePressed(mousePressedEventHandler);
        circle.setOnMouseDragged(mouseDraggedEventHandler);

        return circle;
    }

    private Line connect(Circle c1, Circle c2)
    {
        Line line = new Line();

        line.startXProperty().bind(c1.centerXProperty());
        line.startYProperty().bind(c1.centerYProperty());

        line.endXProperty().bind(c2.centerXProperty());
        line.endYProperty().bind(c2.centerYProperty());

        line.setStrokeWidth(1);
        line.setStrokeLineCap(StrokeLineCap.BUTT);
        line.getStrokeDashArray().setAll(1.0, 4.0);

        return line;
    }

    private void disconnect (Line l1){
        l1.startXProperty().unbind();
        l1.startYProperty().unbind();

        l1.endXProperty().unbind();
        l1.endYProperty().unbind();
    }

    public double calculateDistanceBetweenPoints(CircleCharacter mainChar, CircleCharacter otherChar) {
        double x1 = mainChar.getCenterX();
        double y1 = mainChar.getCenterY();
        double x2 = otherChar.getCenterX();
        double y2 = otherChar.getCenterY();
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }


    public double getOrgSceneX() {
        return orgSceneX;
    }

    public void setOrgSceneX(double orgSceneX) {
        this.orgSceneX = orgSceneX;
    }

    public double getOrgSceneY() {
        return orgSceneY;
    }

    public void setOrgSceneY(double orgSceneY) {
        this.orgSceneY = orgSceneY;
    }

    public EventHandler<MouseEvent> getMousePressedEventHandler() {
        return mousePressedEventHandler;
    }

    public void setMousePressedEventHandler(EventHandler<MouseEvent> mousePressedEventHandler) {
        this.mousePressedEventHandler = mousePressedEventHandler;
    }

    public EventHandler<MouseEvent> getMouseDraggedEventHandler() {
        return mouseDraggedEventHandler;
    }

    public void setMouseDraggedEventHandler(EventHandler<MouseEvent> mouseDraggedEventHandler) {
        this.mouseDraggedEventHandler = mouseDraggedEventHandler;
    }

    public Group getRoot() {
        return root;
    }

    public void setRoot(Group root) {
        this.root = root;
    }

    public LinkedList<CircleCharacter> getListOfCircles() {
        return listOfCircles;
    }

    public void setListOfCircles(LinkedList<CircleCharacter> listOfCircles) {
        this.listOfCircles = listOfCircles;
    }

    public TableView<Character> getTable() {
        return table;
    }

    public void setTable(TableView<Character> table) {
        this.table = table;
    }

    public ObservableList<Character> getData() {
        return data;
    }

    public TableColumn getFirstNameCol() {
        return firstNameCol;
    }

    public void setFirstNameCol(TableColumn firstNameCol) {
        this.firstNameCol = firstNameCol;
    }

    public TableColumn getDistanceCol() {
        return distanceCol;
    }

    public void setDistanceCol(TableColumn distanceCol) {
        this.distanceCol = distanceCol;
    }
}
