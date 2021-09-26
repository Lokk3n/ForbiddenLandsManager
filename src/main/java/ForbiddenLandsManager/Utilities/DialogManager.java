package ForbiddenLandsManager.Utilities;

import ForbiddenLandsManager.View.LoginView;
import ForbiddenLandsManager.ViewModel.LoginViewModel;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogManager {
    Stage parentStage;
    public DialogManager(Stage parentStage){
        this.parentStage = parentStage;
    }
    public void showLoginDialog(){
        Stage loginStage = new Stage();
        LoginView loginView = new LoginView();
        loginView.setDataContext(new LoginViewModel());
        loginStage.setScene(new Scene(loginView));
        loginStage.initOwner(parentStage);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.resizableProperty().set(false);
        loginStage.showAndWait();
    }
}
