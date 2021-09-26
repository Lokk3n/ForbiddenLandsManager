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

    public <T extends ViewModel> T generateViewModel(Class<T> viewModelClass) {
        T vm;
        if (TestViewModel1.class.equals(viewModelClass)) {
            vm = (T) new TestViewModel1();
        } else if (TestViewModel2.class.equals(viewModelClass)) {
            vm = (T) new TestViewModel2();
        } else if (AttributesViewModel.class.equals(viewModelClass)) {
            vm = (T) new AttributesViewModel();
        } else if (CharacterSheetViewModel.class.equals(viewModelClass)) {
            vm = (T) new CharacterSheetViewModel();
        } else if (MainViewModel.class.equals(viewModelClass)) {
            vm = (T) new MainViewModel();
        } else if (LoginViewModel.class.equals(viewModelClass)) {
            vm = (T) new LoginViewModel();
        } else {
            vm = null;
        }
        return vm;
    }
}
