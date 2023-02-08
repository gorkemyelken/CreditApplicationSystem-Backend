package com.definexjavaspringpracticum.finalcase.utilities.results;

public class DataResult<T>{
    private T data;
    private final boolean success;
    private String message;

    public DataResult(boolean success) {
        this.success = success;
    }

    public DataResult(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }

    public DataResult(T data, boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public DataResult(T data, boolean success) {
        this.success = success;
        this.data = data;
    }

    public T getData() {
        return this.data;
    }
}
