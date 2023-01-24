package com.definexjavaspringpracticum.finalcase.utilities.results;

public class Result<T> {
    private final T data;
    private boolean success;
    private String message;

    public Result(T data, boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public Result(T data, boolean success) {
        this.success = success;
        this.data = data;
    }
}
