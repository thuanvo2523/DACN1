package com.example.lib.APIResponse;

import java.io.Serializable;

public class APIResponse implements Serializable {
    private boolean status;
    private String message;


    // Getter Methods

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // Setter Methods

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
