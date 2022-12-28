package com.ping.services.onboardingservice.exceptions;

public class BlockLimitExceededException extends Exception {
    public BlockLimitExceededException(String message) {
        super(message);
    }
}
