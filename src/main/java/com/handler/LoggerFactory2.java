package com.handler;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import handlers.CustomAppender;

public class LoggerFactory2 {
    public static LoggerAppenderPair configureLogger(Class logclass, Logger append) {
        
        
        
        
        Logger         rootLogger = (Logger) LoggerFactory.getLogger(Thread.currentThread().getName());
        CustomAppender appender   = new CustomAppender();

       // if (append !=null)
       // appender.getExternalLoggers().add(append);
        rootLogger.addAppender(appender);
             
        
        LoggerAppenderPair lap = new LoggerAppenderPair();
        lap.setAppender(appender);
        lap.setLogger(rootLogger);
        
        return lap;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
