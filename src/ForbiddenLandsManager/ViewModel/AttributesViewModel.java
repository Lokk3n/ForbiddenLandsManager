package ForbiddenLandsManager.ViewModel;

import ForbiddenLandsManager.Utilities.INavigationAware;
import ForbiddenLandsManager.Utilities.NavigationParameters;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AttributesViewModel extends ViewModel implements INavigationAware {
    IntegerProperty strengthValue = new SimpleIntegerProperty();
    IntegerProperty agilityValue = new SimpleIntegerProperty();
    IntegerProperty witsValue = new SimpleIntegerProperty();
    IntegerProperty empathyValue = new SimpleIntegerProperty();


    public int getStrengthValue() {
        return strengthValue.get();
    }

    public IntegerProperty strengthValueProperty() {
        return strengthValue;
    }

    public int getAgilityValue() {
        return agilityValue.get();
    }

    public IntegerProperty agilityValueProperty() {
        return agilityValue;
    }

    public int getWitsValue() {
        return witsValue.get();
    }

    public IntegerProperty witsValueProperty() {
        return witsValue;
    }

    public int getEmpathyValue() {
        return empathyValue.get();
    }

    public IntegerProperty empathyValueProperty() {
        return empathyValue;
    }

    @Override
    public boolean IsNavigationContext(NavigationParameters parameters) {
        return true;
    }

    @Override
    public void OnNavigateFrom(NavigationParameters parameters) {

    }

    @Override
    public void OnNavigateTo(NavigationParameters parameters) {

    }
}
