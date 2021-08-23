package ForbiddenLandsManager.Utilities;

import ForbiddenLandsManager.Utilities.Events.NavigationEvent;
import ForbiddenLandsManager.View.View;
import ForbiddenLandsManager.View.ViewHandler;
import ForbiddenLandsManager.ViewModel.ViewModel;
import ForbiddenLandsManager.ViewModel.ViewModelFactory;
import javafx.scene.layout.Pane;

import java.util.LinkedList;

public class Region extends Pane {

    private Event<String> navigationEvent = ServiceLocator.getEventAggregator().getEvent(NavigationEvent.class);
    //private Pane pane = new Pane();
    private LinkedList<ViewModel> viewModels = new LinkedList<>();
    private ViewModel activeViewModel;
    private View activeView;
    private String name;

    public Region(String name, RegionManager manager){
        manager.registerRegion(name, this);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void requestNavigate(Class view, NavigationParameters parameters){
        ViewModel viewModel = null;
        Class newViewModel = ViewHandler.getInstance().resolveViewViewModel(view);
        boolean foundContextViewModel = false;
        for(ViewModel vm : viewModels){
            System.out.println("Checking view model...");
            if(vm.getClass() == newViewModel){
                System.out.println("Found view model of same class as requested");
                if(vm instanceof INavigationAware){
                    System.out.println("View model is an instance of INavigationAware");
                    if(((INavigationAware)vm).IsNavigationContext(parameters)){
                        System.out.println("View model is navigation context");
                        viewModel = vm;
                        foundContextViewModel = true;
                    }
                    break;

                }
            }
        }
        if(foundContextViewModel) {
            System.out.println("Found context view model");
            try {
                if (activeViewModel instanceof INavigationAware) {
                    ((INavigationAware) activeViewModel).OnNavigateFrom(parameters);
                }
                if(activeView != null) activeView.unbindAllProperties();
                activeView = (View) view.getDeclaredConstructor(null).newInstance();
                activeView.setDataContext(viewModel);
                activeViewModel = viewModel;
                ((INavigationAware) viewModel).OnNavigateTo(parameters);
                this.getChildren().clear();
                this.getChildren().add(activeView);
            } catch (Exception ex) {
                System.out.println("navigation error");
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Cause: " + ex.getCause());
            }
        }
        else{
            System.out.println("Generating new view model");
            try {
                if (activeViewModel instanceof INavigationAware)
                    ((INavigationAware) activeViewModel).OnNavigateFrom(parameters);
                if(activeView != null) activeView.unbindAllProperties();
                activeView = (View) view.getDeclaredConstructor(null).newInstance();
                ViewModel newViewModelInstance = ViewModelFactory.getInstance().generateViewModel(newViewModel);
                activeView.setDataContext(newViewModelInstance);
                activeViewModel = newViewModelInstance;
                if (newViewModelInstance instanceof INavigationAware) ((INavigationAware) newViewModelInstance).OnNavigateTo(parameters);
                this.getChildren().clear();
                this.getChildren().add(activeView);
                viewModels.add(activeViewModel);
            } catch (Exception ex) {
                System.out.println("navigation error");
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Cause: " + ex.getCause());
            }
        }
        System.out.println("Fire navigation event");
        navigationEvent.fire(this.name);
    }
}
