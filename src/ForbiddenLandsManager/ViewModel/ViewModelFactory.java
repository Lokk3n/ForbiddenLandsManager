package ForbiddenLandsManager.ViewModel;

import ForbiddenLandsManager.Model.ModelFactory;
import ForbiddenLandsManager.Utilities.NavigationParameters;

public class ViewModelFactory {
    ModelFactory mf;

    public ViewModelFactory(ModelFactory mf){
        this.mf = mf;
    }

    public <T extends ViewModel> T generateViewModel(Class<T> viewModelClass, NavigationParameters parameters){
        if (TestViewModel1.class.equals(viewModelClass)) {
            return (T) new TestViewModel1();
        } else if (TestViewModel2.class.equals(viewModelClass)) {
            return (T) new TestViewModel2();
        }
    }
}
