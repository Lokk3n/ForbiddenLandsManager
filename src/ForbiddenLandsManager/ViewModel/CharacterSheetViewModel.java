package ForbiddenLandsManager.ViewModel;

import ForbiddenLandsManager.Utilities.INavigationAware;
import ForbiddenLandsManager.Utilities.NavigationParameters;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CharacterSheetViewModel extends ViewModel implements INavigationAware {

    IntegerProperty strengthValue = new SimpleIntegerProperty();
    IntegerProperty agilityValue = new SimpleIntegerProperty();
    IntegerProperty witsValue = new SimpleIntegerProperty();
    IntegerProperty empathyValue = new SimpleIntegerProperty();

    BooleanProperty sleeplessValue = new SimpleBooleanProperty();
    BooleanProperty hungryValue = new SimpleBooleanProperty();
    BooleanProperty thirstyValue = new SimpleBooleanProperty();
    BooleanProperty coldValue = new SimpleBooleanProperty();


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

    public boolean isSleeplessValue() {
        return sleeplessValue.get();
    }

    public BooleanProperty sleeplessValueProperty() {
        return sleeplessValue;
    }

    public boolean isHungryValue() {
        return hungryValue.get();
    }

    public BooleanProperty hungryValueProperty() {
        return hungryValue;
    }

    public boolean isThirstyValue() {
        return thirstyValue.get();
    }

    public BooleanProperty thirstyValueProperty() {
        return thirstyValue;
    }

    public boolean isColdValue() {
        return coldValue.get();
    }

    public BooleanProperty coldValueProperty() {
        return coldValue;
    }


    public CharacterSheetViewModel() {
        strengthValue.addListener(((observableValue, number, t1) -> {
            System.out.println("Change in strength on viewmodel detected");
        }));
        agilityValue.addListener(((observableValue, number, t1) -> {
            System.out.println("Change in agility on viewmodel detected");
        }));
        witsValue.addListener(((observableValue, number, t1) -> {
            System.out.println("Change in wits on viewmodel detected");
        }));
        empathyValue.addListener(((observableValue, number, t1) -> {
            System.out.println("Change in empathy on viewmodel detected");
        }));
    }

    @Override
    public boolean IsNavigationContext(NavigationParameters parameters) {
        return true;
    }

    @Override
    public void OnNavigateFrom(NavigationParameters parameters) {
        System.out.println("Navigated out of CharacterSheetViewModel");
    }

    @Override
    public void OnNavigateTo(NavigationParameters parameters) {
        System.out.println("Navigated to CharacterSheetViewModel");
    }
}
