package com.example.webapp.data.model.retrofit;

public class Model_login {

    private String message;

    public Model_login(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
