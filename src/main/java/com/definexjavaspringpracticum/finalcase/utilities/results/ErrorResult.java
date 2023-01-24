package com.definexjavaspringpracticum.finalcase.utilities.results;

public class ErrorResult<T> extends Result<T> {

    public ErrorResult(T data, String message) {
        super(data, false, message);
    }

    public ErrorResult(T data) {
        super(data, false);
    }

    public ErrorResult(String message) {
        super(null, false, message);
    }

    public ErrorResult() {
        super(null, false);
    }
}
