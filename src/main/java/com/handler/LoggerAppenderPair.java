/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import ch.qos.logback.classic.Logger;
import handlers.CustomAppender;

/**
 *
 * @author tomasz.kosinski
 */
public class LoggerAppenderPair {
    
    private Logger logger;
    private CustomAppender appender;

    /**
     * @return the appender
     */
    public CustomAppender getAppender() {
        return appender;
    }

    /**
     * @param appender the appender to set
     */
    public void setAppender(CustomAppender appender) {
        this.appender = appender;
    }

    /**
     * @return the logger
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * @param logger the logger to set
     */
    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
