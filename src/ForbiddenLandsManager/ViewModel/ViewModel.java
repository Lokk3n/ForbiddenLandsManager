package ForbiddenLandsManager.ViewModel;

import ForbiddenLandsManager.Utilities.CommandParameters;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ViewModel {
    public void callMethod(String name, CommandParameters parameters){
        try {
            this.getClass().getMethod(name, CommandParameters.class).invoke(this, parameters);
        } catch (IllegalAccessException e) {
            System.out.println("Illegal access");
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            System.out.println("Invocation target");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("No such method");
            e.printStackTrace();
        }
    }
}
