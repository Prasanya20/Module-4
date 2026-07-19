package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AppenderLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message - should appear in console and app.log");
        logger.info("Info message - should appear in console and app.log");
        logger.warn("Warning message - should appear in console and app.log");
        logger.error("Error message - should appear in console and app.log");
    }
}
