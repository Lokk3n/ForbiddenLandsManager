package ForbiddenLandsManager.View;

import ForbiddenLandsManager.ViewModel.ViewModel;
import javafx.scene.layout.Pane;

public class View<T extends ViewModel> extends Pane {
    protected T dataContext;

    public void setDataContext(T vm){
        this.dataContext = vm;
    }
    public ViewModel getDataContext() { return dataContext; }
}
