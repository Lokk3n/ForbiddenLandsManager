package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Utilities.CommandParameters;
import ForbiddenLandsManager.Utilities.Event;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.lang.reflect.Field;

public class LoginView extends View{
    Label loginLabel = new Label("Username:");
    Label passwordLabel = new Label("Password:");
    TextField loginField = new TextField();
    TextField passwordField = new TextField();
    Button loginButton = new Button("Login");
    Button cancelButton = new Button("Cancel");
    Event<Object> event;

    public LoginView(){
        GridPane grid = new GridPane();
        grid.add(loginLabel, 0, 0);
        grid.add(loginField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);

        HBox hBox = new HBox();
        Region r1 = new Region();
        Region r2 = new Region();
        hBox.getChildren().addAll(r1, loginButton, cancelButton, r2);
        grid.add(hBox, 0, 2, 2, 1);

        HBox.setHgrow(r1, Priority.ALWAYS);
        HBox.setHgrow(r2, Priority.ALWAYS);
        GridPane.setHalignment(loginLabel, HPos.RIGHT);
        GridPane.setHalignment(passwordLabel, HPos.RIGHT);
        GridPane.setHalignment(hBox, HPos.CENTER);


        this.getChildren().add(grid);

        loginButton.setOnAction(ev -> {
            this.callCommand("loginCommand", null);
        });



    }

    @Override
    protected void postSetDataContextUpdates() {
        try {
            event = (Event<Object>) dataContext.getClass().getField("logonEvent").get(dataContext);
            event.subscribe(ev -> {
                ((Stage)this.getScene().getWindow()).close();
            });
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void registerBindings() {
        this.propertyBindingMap.put(loginField.textProperty(), "usernameProperty");
        this.propertyBindingMap.put(passwordField.textProperty(), "passwordProperty");
    }
}
