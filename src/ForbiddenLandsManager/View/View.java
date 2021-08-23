package ForbiddenLandsManager.View;

import ForbiddenLandsManager.ViewModel.ViewModel;
import javafx.beans.property.Property;
import javafx.scene.layout.Pane;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class View extends Pane {
    protected ViewModel dataContext;


    public void setDataContext(ViewModel vm){
        this.dataContext = vm;
    }
    public ViewModel getDataContext() { return dataContext; }

    protected void createBindings(){

    }

    protected <T1> void bindProperties(Property<T1> targetProperty, String sourcePropertyGetterName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        targetProperty.bindBidirectional((Property<T1>) this.dataContext.getClass().getMethod(sourcePropertyGetterName).invoke(this.dataContext));
    }
}
