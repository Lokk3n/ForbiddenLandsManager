package ForbiddenLandsManager.ViewModel;

import ForbiddenLandsManager.Model.ModelFactory;
import ForbiddenLandsManager.Utilities.NavigationParameters;

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
        }
        else return null;
    }
}
