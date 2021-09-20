package ForbiddenLandsManager.View;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

import java.util.LinkedList;
import java.util.List;

public class BattleMapView extends View{

    HBox row = new HBox();
    Label test = new Label("Test");
    Label test2 = new Label("Test2");
    LinkedList<Circle> listOfCircles = new LinkedList<>();
    Group root = new Group();

    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("Wilczomlecz" ),
                    new Person("Vuko"),
                    new Person("Pinku"),
                    new Person("S"),
                    new Person("Michael"));
    final HBox hb = new HBox();


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
        Circle redCircle = createCircle(100, 50, 30, Color.RED);
        Circle yellowCircle = createCircle(200, 50, 30, Color.YELLOW);
        Circle blueCircle = createCircle(200, 150, 20, Color.BLUE);
        Circle greenCircle = createCircle(400, 100, 40, Color.GREEN);

        listOfCircles.add(redCircle);
        listOfCircles.add(yellowCircle);
        listOfCircles.add(blueCircle);
        listOfCircles.add(greenCircle);

        // add the circles
        root.getChildren().addAll(listOfCircles);

        //nie działa

        /*
        for(int j = 0; j < getListOfCircles().size(); j++ ) {
            if (getListOfCircles().get(j).isPressed()) {
                System.out.println("pressed");
                for (int i = 0; i < getListOfCircles().size(); i++) {
                    Line line = connect(getListOfCircles().get(j), getListOfCircles().get(i));
                    getRoot().getChildren().add(line);
                }
            }
        }

         */

        // add the lines
        /*
        root.getChildren().add(line1);
        root.getChildren().add(line2);
        root.getChildren().add(line3);

         */

        // bring the circles to the front of the lines
        redCircle.toFront();
        blueCircle.toFront();
        greenCircle.toFront();



        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory( new PropertyValueFactory<Person, String>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<Person, String> t) {
                            ((Person) t.getTableView().getItems().get(
                                    t.getTablePosition().getRow())
                            ).setFirstName(t.getNewValue());
                        }
                    });
/*
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Person, String> t) {
                        ((Person) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());
                    }
                }
        );

 */

        TableColumn distanceCol = new TableColumn("Distance between characters");
        distanceCol.setMinWidth(300);
        distanceCol.setCellValueFactory(new PropertyValueFactory<Person, String>("distance"));
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
                data.add(new Person(addFirstName.getText()));
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

        /*
        for (int i = 0; i < getListOfCircles().size(); i++) {
            Line line = connect(redCircle, getListOfCircles().get(i));
            root.getChildren().add(line);

        }

         */

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

        Circle c = (Circle) (t.getSource());

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

            }

        c.toFront();



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

    private Circle createCircle(double x, double y, double r, Color color)
    {
        Circle circle = new Circle(x, y, r, color);

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

    private void init (){

        //
        Group root = new Group();
        Scene scene = new Scene(root, 500, 260);

        // circles
        Circle redCircle = createCircle(100, 50, 30, Color.RED);
        Circle blueCircle = createCircle(200, 150, 20, Color.BLUE);
        Circle greenCircle = createCircle(400, 100, 40, Color.GREEN);

        Line line1 = connect(redCircle, blueCircle);
        Line line2 = connect(redCircle, greenCircle);
        Line line3 = connect(greenCircle, blueCircle);

        // add the circles
        root.getChildren().add(redCircle);
        root.getChildren().add(blueCircle);
        root.getChildren().add(greenCircle);

        // add the lines
        root.getChildren().add(line1);
        root.getChildren().add(line2);
        root.getChildren().add(line3);

        // bring the circles to the front of the lines
        redCircle.toFront();
        blueCircle.toFront();
        greenCircle.toFront();

        // set the scene

    }

    public double calculateDistanceBetweenPoints(Circle mainChar, Circle otherChar) {
        double x1 = mainChar.getCenterX();
        double y1 = mainChar.getCenterY();
        double x2 = otherChar.getCenterX();
        double y2 = otherChar.getCenterY();
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public void setListOfCircles(LinkedList<Circle> listOfCircles) {
        this.listOfCircles = listOfCircles;
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

    public LinkedList<Circle> getListOfCircles() {
        return listOfCircles;
    }

    public class Person {

        private final SimpleStringProperty firstName;
//        private final double distanceBetweenChar;

        public Person(String fName) {
            this.firstName = new SimpleStringProperty(fName);

        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

    }
}
