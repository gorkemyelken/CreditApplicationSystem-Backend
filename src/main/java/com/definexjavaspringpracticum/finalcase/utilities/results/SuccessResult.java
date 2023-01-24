package com.definexjavaspringpracticum.finalcase.utilities.results;

public class SuccessResult<T> extends Result<T> {

    public SuccessResult(T data, String message) {
        super(data, true, message);
    }

    public SuccessResult(T data) {
        super(data, true);
    }

    public SuccessResult(String message) {
        super(null, true, message);
    }

    public SuccessResult() {
        super(null, true);
    }
}
