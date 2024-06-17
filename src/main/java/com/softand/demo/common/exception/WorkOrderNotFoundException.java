package com.softand.demo.common.exception;

public class WorkOrderNotFoundException extends RuntimeException {
    public WorkOrderNotFoundException(String message) {
        super(message);
    }
}

