package com.example.part2.model;

import org.testng.annotations.Test;

public class ErrorMessage {
    private String error;

    public ErrorMessage() {
    }

    @Test
    public String getError() {
        return error;
    }

    @Test
    public void setError(String error) {
        System.out.println(error);
        this.error = error;
    }
}
