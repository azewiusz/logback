package handlers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;
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
import ch.qos.logback.core.Appender;

/**
 * Class description
 *
 *
 * @version 1.0, 16/05/16
 * @author Tomasz Kosiński <azewiusz@gmail.com>
 */
@ListenForLog(classes = { Utils.class })
public class GlobalListener implements org.testng.ITestListener {
    CustomAppender       appender = new CustomAppender();
    PatternLayoutEncoder ple      = new PatternLayoutEncoder();

    @Override
    public void onFinish(ITestContext itc) {
        System.out.println("onFinish");
    }

    @Override
    public void onStart(ITestContext itc) {
        System.out.println("onStart");
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
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        ple.setPattern("%date %level [%thread] %logger %msg%n");
        ple.setContext(lc);
        ple.stop();
        ple.start();

        List<Logger> log = getLogger(itr);

        appender.stop();
        appender.start();

        for (Logger l : log) {
            l.detachAppender(appender);
            l.addAppender(appender);
        }
    }

    @Override
    public void onTestSuccess(ITestResult itr) {
        writeLog(itr, "SUCCESS");
    }

    public void writeLog(ITestResult itr, String Teststatus) {
        System.out.println("onTestSuccess");
        Reporter.log("-------------------------------------------");
        Reporter.log("TEST " + Teststatus + " - LOG OUTPUT");
        Reporter.log("-------------------------------------------");

        for (ILoggingEvent e : appender.events) {
            Reporter.log(ple.getLayout().doLayout(e));
        }

        appender.clear();
        ple.stop();
    }

    public static List<Logger> getLogger(ITestResult itr) {
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
