package ForbiddenLandsManager.ViewModel;

import Common.protocol.Login.LoginResponse;
import ForbiddenLandsManager.Utilities.CommandParameters;
import ForbiddenLandsManager.Utilities.Event;
import ForbiddenLandsManager.Utilities.ServerProxy;
import ForbiddenLandsManager.Utilities.ServiceLocator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginViewModel extends ViewModel{
    ServerProxy proxy = ServiceLocator.getServerProxy();
    StringProperty username = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    public Event<Object> logonEvent = new Event<>();

    public LoginViewModel() {
        setUsername("Drzazga");
        setPassword("hehehe");
    }

    public void loginCommand(CommandParameters parameters){
        System.out.println(username.get());
        System.out.println(password.get());
        proxy.requestLogin(username.get(), password.get()).thenAccept(response -> {
            System.out.println("Drugi callback " + ((LoginResponse) response).getToken());
            if(!((LoginResponse) response).getToken().isEmpty()) logonEvent.fire(new Object());
        });
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
