package Common.protocol.Login;

import Common.protocol.Response;

public class LoginResponse extends Response {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
