package ForbiddenLandsManager.View;

import ForbiddenLandsManager.ViewModel.ViewModel;
import javafx.scene.layout.Pane;

public class View extends Pane {
    private ViewModel dataContext;

    public void setDataContext(ViewModel vm){
        this.dataContext = vm;
    }
    public ViewModel getDataContext() { return dataContext; }
}
