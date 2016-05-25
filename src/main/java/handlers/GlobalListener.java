package handlers;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;

import org.slf4j.LoggerFactory;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.handler.Utils;

import annotations.ListenForLog;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Class description
 *
 *
 * @version 1.0, 16/05/16
 * @author Tomasz Kosiński <azewiusz@gmail.com>
 */
@ListenForLog(classes = { Utils.class })
public class GlobalListener implements org.testng.ITestListener {

    // Define a hash map as each distinc thread will have distinct appender
    final static ConcurrentHashMap<String, CustomAppender> appender = new ConcurrentHashMap<>();
    PatternLayoutEncoder                                   ple      = new PatternLayoutEncoder();

    
    
    
    @Override
    public void onFinish(ITestContext itc) {
        System.out.println("onFinish "+getHash(this));
    }

    /**
     * Here multiple logging queues should be started to separate 
     * logging streams
     * @param itc 
     */
    @Override
    public void onStart(ITestContext itc) {
        System.out.println("onStart "+getHash(this));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult itr) {
        writeLog(itr, "FAILED");
    }

    @Override
    public void onTestFailure(ITestResult itr) {
        writeLog(itr, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult itr) {
        writeLog(itr, "SKIPPED");
    }

    @Override
    public void onTestStart(ITestResult itr) {
        System.out.println("Test start : " + getHash(this));

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        ple.setPattern("%date %level [%thread] %logger %msg%n");
        ple.setContext(lc);
        ple.stop();
        ple.start();

        List<Logger>   log     = getLogger(itr);
        CustomAppender current = null;

        current = appender.get(getHash(this));

        if (current == null) {
            current = new CustomAppender();
           
        }

        current.stop();
        current.start();

        for (Logger l : log) {
            l.detachAppender(current);
            l.addAppender(current);
        }
         appender.put(getHash(this), current);
    }

    @Override
    public void onTestSuccess(ITestResult itr) {
        writeLog(itr, "SUCCESS");
    }

    public static void print(String result) {
        Reporter.log(result);
    }

    public void writeLog(ITestResult itr, String Teststatus) {
        System.out.println("onTestSuccess");
        print("-------------------------------------------");
        print("TEST " + Teststatus + " - LOG OUTPUT");
        print("-------------------------------------------");

        CustomAppender get = appender.get(getHash(this));

        for (ILoggingEvent e : get.events) {
            print(ple.getLayout().doLayout(e));
        }

        appender.get(getHash(this)).clear();
    }

    public static String getHash(Object th) {
        return Thread.currentThread().getName() + th.hashCode();
    }

    public  static List<Logger> getLogger(ITestResult itr) {
        try {
            ArrayList<Logger> loggers = new ArrayList<>();

            // Class             zeta       = Class.forName(GlobalListener.class.getCanonicalName());
            ListenForLog annotation = (ListenForLog) GlobalListener.class.getAnnotation(ListenForLog.class);

            // zeta.getClass().
            // System.out.println("Class : " + zeta.getCanonicalName());
            // System.out.println("Annotation : " + zeta.getAnnotations().length);
            if (annotation != null) {

                // Add all classes listed in annotation to be intercepted logs for
                for (Class c : annotation.classes()) {
                    Field log = null;

                    try {
                        log = c.getDeclaredField("LOG");
                    } catch (NoSuchFieldException e) {}

                    if (log == null) {

                        // Skip this class as it is not valid class for appending logger
                        continue;
                    }

                    log.setAccessible(true);

                    Logger l = (Logger) log.get(null);

                    loggers.add(l);
                }
            }

            // Add test instance class logger to appender
            Class c   = Class.forName(itr.getInstanceName());
            Field log = c.getDeclaredField("LOG");

            log.setAccessible(true);

            Logger l = (Logger) log.get(null);

            loggers.add(l);

            return loggers;
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException
                 | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlobalListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
