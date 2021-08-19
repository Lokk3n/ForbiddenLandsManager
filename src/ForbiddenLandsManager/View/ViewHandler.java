package ForbiddenLandsManager.View;

import ForbiddenLandsManager.ViewModel.ViewModel;
import ForbiddenLandsManager.ViewModel.ViewModelFactory;
import javafx.stage.Stage;

import java.util.HashMap;

public class ViewHandler {
    ViewModelFactory vmf;
    Stage stg;

    static ViewHandler instance;

    //private static HashMap<String, Class<? extends View>> viewLocator = new HashMap<>();
    private static HashMap<Class<? extends View>, Class<? extends ViewModel>> viewModelLocator = new HashMap<>();


    public ViewHandler(Stage stage, ViewModelFactory vmf){
        this.vmf = vmf;
        this.stg = stage;
        instance = this;
    }

    public static ViewHandler getInstance(){
        return instance;
    }

    //public void registerView(String )
    public void registerViewViewModel(Class<? extends View> view, Class<? extends ViewModel> viewModel){
        viewModelLocator.put(view, viewModel);
    }

    public Class<? extends ViewModel> resolveViewViewModel(Class<? extends View> view){
        return viewModelLocator.get(view);
    }


}
