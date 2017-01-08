package ro.fmi.rpg.to.login;

import java.io.Serializable;

/**
 * Created by User on 11/1/2016.
 */
public class LoginResponse implements Serializable {

    private String token;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
