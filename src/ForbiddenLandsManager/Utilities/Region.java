package ForbiddenLandsManager.Utilities;

import ForbiddenLandsManager.View.View;
import ForbiddenLandsManager.View.ViewHandler;
import ForbiddenLandsManager.ViewModel.ViewModel;
import javafx.scene.layout.Pane;

import java.util.LinkedList;

public class Region {

    private Pane pane = new Pane();
    private LinkedList<ViewModel> viewModels = new LinkedList<>();
    private ViewModel activeViewModel;

    public void requestNavigate(Class<? extends View> view, NavigationParameters parameters){
        ViewModel viewModel;
        if(activeViewModel.getClass().equals(ViewHandler.getInstance().resolveViewViewModel(view))){
            if(activeViewModel instanceof INavigationAware){
                if(((INavigationAware) activeViewModel).IsNavigationContext(parameters)){
                    viewModel = activeViewModel;
                }
                else {
                    ((INavigationAware) activeViewModel).OnNavigateFrom(parameters);
                    activeViewModel = ViewHandler.getInstance().resolveViewViewModel(view);
                }
            }
        }





    }
}
