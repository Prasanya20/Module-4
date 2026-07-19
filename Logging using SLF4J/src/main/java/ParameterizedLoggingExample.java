package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "alice";
        int loginAttempts = 3;

        
        logger.info("User '{}' logged in", username);

       
        logger.warn("User '{}' failed to log in {} times", username, loginAttempts);

        
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("Failed to process division for user '{}'", username, e);
        }
    }
}
