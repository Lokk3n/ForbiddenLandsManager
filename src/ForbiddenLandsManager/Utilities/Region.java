package ForbiddenLandsManager.Utilities;

import ForbiddenLandsManager.View.View;
import ForbiddenLandsManager.View.ViewHandler;
import ForbiddenLandsManager.ViewModel.ViewModel;
import ForbiddenLandsManager.ViewModel.ViewModelFactory;
import javafx.scene.layout.Pane;

import java.util.LinkedList;

public class Region extends Pane {

    //private Pane pane = new Pane();
    private LinkedList<ViewModel> viewModels = new LinkedList<>();
    private ViewModel activeViewModel;
    private View activeView;

    public Region(String name, RegionManager manager){
        manager.registerRegion(name, this);
    }

    public void requestNavigate(Class view, NavigationParameters parameters){
        ViewModel viewModel = null;
        Class newViewModel = ViewHandler.getInstance().resolveViewViewModel(view);
        boolean foundContextViewModel = false;
        for(ViewModel vm : viewModels){
            if(vm.getClass() == newViewModel){
                if(vm instanceof INavigationAware){
                    if(((INavigationAware)vm).IsNavigationContext(parameters)){
                        viewModel = vm;
                        foundContextViewModel = true;
                    }
                    break;

                }
            }
        }
        if(foundContextViewModel) {
            try {
                if (activeViewModel instanceof INavigationAware)
                    ((INavigationAware) activeViewModel).OnNavigateFrom(parameters);
                activeView = (View) view.getDeclaredConstructor(null).newInstance();
                activeView.setDataContext(viewModel);
                activeViewModel = viewModel;
                ((INavigationAware) viewModel).OnNavigateTo(parameters);
                this.getChildren().clear();
                this.getChildren().add(activeView);
            } catch (Exception ex) {
                System.out.println("navigation error");
            }
        }
        else{
            try {
                if (activeViewModel instanceof INavigationAware)
                    ((INavigationAware) activeViewModel).OnNavigateFrom(parameters);
                activeView = (View) view.getDeclaredConstructor(null).newInstance();
                ViewModel newViewModelInstance = ViewModelFactory.getInstance().generateViewModel(newViewModel);
                activeView.setDataContext(newViewModelInstance);
                activeViewModel = newViewModelInstance;
                if (newViewModelInstance instanceof INavigationAware) ((INavigationAware) newViewModelInstance).OnNavigateTo(parameters);
                System.out.println(this.getChildren().size());
                this.getChildren().clear();
                System.out.println(this.getChildren().size());
                this.getChildren().add(activeView);
                System.out.println(this.getChildren().size());
            } catch (Exception ex) {
                System.out.println("navigation error");
            }
        }
    }
}
