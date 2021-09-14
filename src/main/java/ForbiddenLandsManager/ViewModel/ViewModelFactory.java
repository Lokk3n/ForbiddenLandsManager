package ForbiddenLandsManager.ViewModel;

import ForbiddenLandsManager.Model.ModelFactory;

public class ViewModelFactory {
    ModelFactory mf;
    static ViewModelFactory instance;


    public ViewModelFactory(ModelFactory mf){
        this.mf = mf;
        instance = this;
    }

    public static ViewModelFactory getInstance(){
        return instance;
    }

    public <T extends ViewModel> T generateViewModel(Class<T> viewModelClass){
        if (TestViewModel1.class.equals(viewModelClass)) {
            return (T) new TestViewModel1();
        } else if (TestViewModel2.class.equals(viewModelClass)) {
            return (T) new TestViewModel2();
        } else if (AttributesViewModel.class.equals(viewModelClass)) {
            return (T) new AttributesViewModel();
        } else if (CharacterSheetViewModel.class.equals(viewModelClass)) {
            return (T) new CharacterSheetViewModel();
        } else if (BattleMapViewModel.class.equals(viewModelClass)) {
            return (T) new BattleMapViewModel();
        }
            else return null;
        }
}
