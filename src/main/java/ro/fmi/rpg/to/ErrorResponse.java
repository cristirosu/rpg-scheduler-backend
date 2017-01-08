package ro.fmi.rpg.to;

import java.io.Serializable;

/**
 * Created by User on 11/13/2016.
 */
public class ErrorResponse implements Serializable{

    private String error;

    public ErrorResponse() {
    }

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error='" + error + '\'' +
                '}';
    }
}
