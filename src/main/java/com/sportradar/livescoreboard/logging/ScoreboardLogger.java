package com.sportradar.livescoreboard.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ScoreboardLogger to monitor the application activities
 * Date : 10.08.2023
 * Version : 1.0 (Initial Version)
 * @author Ujwala Vanve
 */

public class ScoreboardLogger {

    private final Logger logger;

    public ScoreboardLogger(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    public void debug(String message){
        if(this.logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    public void info(String message) {
        logger.info(message);
    }

    public void debug(String format, Object arg){
        if(this.logger.isDebugEnabled()) {
            logger.debug(format, arg);
        }
    }

    public void error(String message) {
        logger.info(message);
    }

    public void fatal(String message) {
        logger.info(message);
    }
}
