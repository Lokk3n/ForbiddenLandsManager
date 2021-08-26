package ForbiddenLandsManager.ViewModel;

import ForbiddenLandsManager.Enums.AttributeType;
import ForbiddenLandsManager.Model.Injury;
import ForbiddenLandsManager.Model.Skill;
import ForbiddenLandsManager.Utilities.CommandParameters;
import ForbiddenLandsManager.Utilities.INavigationAware;
import ForbiddenLandsManager.Utilities.NavigationParameters;
import javafx.beans.property.*;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.LinkedList;
import java.util.List;

public class CharacterSheetViewModel extends ViewModel implements INavigationAware {

    IntegerProperty strengthValue = new SimpleIntegerProperty();
    IntegerProperty agilityValue = new SimpleIntegerProperty();
    IntegerProperty witsValue = new SimpleIntegerProperty();
    IntegerProperty empathyValue = new SimpleIntegerProperty();

    IntegerProperty strengthMaxValue = new SimpleIntegerProperty();
    IntegerProperty agilityMaxValue = new SimpleIntegerProperty();
    IntegerProperty witsMaxValue = new SimpleIntegerProperty();
    IntegerProperty empathyMaxValue = new SimpleIntegerProperty();

    BooleanProperty sleeplessValue = new SimpleBooleanProperty();
    BooleanProperty hungryValue = new SimpleBooleanProperty();
    BooleanProperty thirstyValue = new SimpleBooleanProperty();
    BooleanProperty coldValue = new SimpleBooleanProperty();

    ObservableList<Injury> injuriesObservableList = FXCollections.observableList(new LinkedList<>());
    ListProperty<Injury> injuriesListProperty = new SimpleListProperty<>(injuriesObservableList);

    ObservableList<Skill> skillsObservableList = FXCollections.observableList(new LinkedList<>());
    ListProperty<Skill> skillsListProperty = new SimpleListProperty<>(skillsObservableList);


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

    public int getStrengthMaxValue() {
        return strengthMaxValue.get();
    }

    public IntegerProperty strengthMaxValueProperty() {
        return strengthMaxValue;
    }

    public int getAgilityMaxValue() {
        return agilityMaxValue.get();
    }

    public IntegerProperty agilityMaxValueProperty() {
        return agilityMaxValue;
    }

    public int getWitsMaxValue() {
        return witsMaxValue.get();
    }

    public IntegerProperty witsMaxValueProperty() {
        return witsMaxValue;
    }

    public int getEmpathyMaxValue() {
        return empathyMaxValue.get();
    }

    public IntegerProperty empathyMaxValueProperty() {
        return empathyMaxValue;
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

    public ObservableList<Injury> getInjuriesListProperty() {
        return injuriesListProperty.get();
    }

    public ListProperty<Injury> injuriesListPropertyProperty() {
        return injuriesListProperty;
    }

    public ObservableList<Skill> getSkillsListProperty() {
        return skillsListProperty.get();
    }

    public ListProperty<Skill> skillsListPropertyProperty() {
        return skillsListProperty;
    }



    public void addNewInjury(CommandParameters parameters){
        //injuriesList.add("DD");
        injuriesObservableList.add(new Injury("Fractured bone", "-2 to strength"));
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

        strengthMaxValue.set(5);
        agilityMaxValue.set(4);
        witsMaxValue.set(4);
        empathyMaxValue.set(2);

        loadSkills();
    }

    private void loadSkills(){
        skillsObservableList.add(new Skill("Might", 4, AttributeType.STRENGTH));
        skillsObservableList.add(new Skill("Endurance", 4, AttributeType.STRENGTH));
        skillsObservableList.add(new Skill("Melee", 4, AttributeType.STRENGTH));
        skillsObservableList.add(new Skill("Crafting", 4, AttributeType.STRENGTH));
        skillsObservableList.add(new Skill("Stealth", 4, AttributeType.AGILITY));
        skillsObservableList.add(new Skill("Sleight of Hand", 4, AttributeType.AGILITY));
        skillsObservableList.add(new Skill("Move", 4, AttributeType.AGILITY));
        skillsObservableList.add(new Skill("Marksmanship", 4, AttributeType.AGILITY));
        skillsObservableList.add(new Skill("Scouting", 4, AttributeType.WITS));
        skillsObservableList.add(new Skill("Lore", 4, AttributeType.WITS));
        skillsObservableList.add(new Skill("Survival", 4, AttributeType.WITS));
        skillsObservableList.add(new Skill("Insight", 4, AttributeType.WITS));
        skillsObservableList.add(new Skill("Manipulation", 4, AttributeType.EMPATHY));
        skillsObservableList.add(new Skill("Performance", 4, AttributeType.EMPATHY));
        skillsObservableList.add(new Skill("Healing", 4, AttributeType.EMPATHY));
        skillsObservableList.add(new Skill("Animal Handling", 4, AttributeType.EMPATHY));
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
