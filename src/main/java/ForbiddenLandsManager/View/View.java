package ForbiddenLandsManager.View;

import ForbiddenLandsManager.Utilities.CommandParameters;
import ForbiddenLandsManager.ViewModel.ViewModel;
import javafx.beans.property.Property;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class View extends Pane {
    protected ViewModel dataContext;

    protected HashMap<Property<?>, String> propertyBindingMap = new HashMap<>();

    public final void setDataContext(ViewModel dataContext){
        this.dataContext = dataContext;
        createBindings();
        postSetDataContextUpdates();
    }

    public ViewModel getDataContext() { return dataContext; }

    protected abstract void postSetDataContextUpdates();

    protected abstract void registerBindings();

    protected final void createBindings() {
        try {
            unbindAllProperties();
            registerBindings();
            for(Property<?> property : propertyBindingMap.keySet()){
                bindProperty(property, propertyBindingMap.get(property));
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    private <T1> void bindProperty(Property<T1> targetProperty, String sourcePropertyGetterName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        targetProperty.bindBidirectional((Property<T1>) this.dataContext.getClass().getMethod(sourcePropertyGetterName).invoke(this.dataContext));
    }

    private <T1> void unbindProperty(Property<T1> targetProperty, String sourcePropertyGetterName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        targetProperty.unbindBidirectional((Property<T1>) this.dataContext.getClass().getMethod(sourcePropertyGetterName).invoke(this.dataContext));
    }



    public final void unbindAllProperties(){
        try {
            for (Property<?> property : propertyBindingMap.keySet()) {
                unbindProperty(property, propertyBindingMap.get(property));
            }
            propertyBindingMap = new HashMap<>();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    protected final void callCommand(String name, CommandParameters parameters){
        this.dataContext.callMethod(name, parameters);
    }
}
