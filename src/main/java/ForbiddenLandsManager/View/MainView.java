package ForbiddenLandsManager.View;
import ForbiddenLandsManager.Utilities.ServiceLocator;
import javafx.geometry.Insets;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class MainView extends View{
    MenuBar menuBar = new MenuBar();
    Menu connectionMenu = new Menu("Connection");
    MenuItem loginAction = new MenuItem("Log in");
    MenuItem logoutAction = new MenuItem("Log out");
    MenuItem registerAction = new MenuItem("Register");

    public MainView(){
        connectionMenu.getItems().addAll(loginAction, logoutAction, registerAction);
        menuBar.getMenus().addAll(connectionMenu);
        VBox vbox = new VBox();
        vbox.getChildren().add(menuBar);
        this.getChildren().addAll(vbox);


        this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        menuBar.prefWidthProperty().bind(this.widthProperty());

        loginAction.setOnAction(e -> {
            ServiceLocator.getDialogManager().showLoginDialog();
        });
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}
