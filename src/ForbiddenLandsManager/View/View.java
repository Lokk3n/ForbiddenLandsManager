package ForbiddenLandsManager.View;

import ForbiddenLandsManager.ViewModel.ViewModel;
import javafx.beans.property.Property;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class View extends Pane {
    protected ViewModel dataContext;

    protected HashMap<Property<?>, String> bindingsMap = new HashMap<>();

    public final void setDataContext(ViewModel dataContext){
        this.dataContext = dataContext;
        createBindings();
        postSetDataContextUpdates();
    }

    public ViewModel getDataContext() { return dataContext; }

    protected abstract void postSetDataContextUpdates();

    protected abstract void registerBindings();

    protected void createBindings() {
        try {
            unbindAllProperties();
            registerBindings();
            for(Property<?> property : bindingsMap.keySet()){
                bindProperty(property, bindingsMap.get(property));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    protected <T1> void bindProperty(Property<T1> targetProperty, String sourcePropertyGetterName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        targetProperty.bindBidirectional((Property<T1>) this.dataContext.getClass().getMethod(sourcePropertyGetterName).invoke(this.dataContext));
    }

    protected <T1> void unbindProperty(Property<T1> targetProperty, String sourcePropertyGetterName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        targetProperty.unbindBidirectional((Property<T1>) this.dataContext.getClass().getMethod(sourcePropertyGetterName).invoke(this.dataContext));
    }

    public void unbindAllProperties(){
        try {
            for (Property<?> property : bindingsMap.keySet()) {
                unbindProperty(property, bindingsMap.get(property));
            }
            bindingsMap = new HashMap<>();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
