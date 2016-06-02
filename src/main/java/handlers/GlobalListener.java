package handlers;

import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.slf4j.LoggerFactory;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.handler.LoggerAppenderPair;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.handler.LoggerFactory2;
import com.handler.XPathService;

/**
 * Class description
 *
 *
 * @version 1.0, 16/05/16
 * @author Tomasz Kosiński <azewiusz@gmail.com>
 */
//@ListenForLog(classes = { Utils.class })
public class GlobalListener implements org.testng.ITestListener {

    // Define a hash map as each distinc thread will have distinct appender
    PatternLayoutEncoder ple = new PatternLayoutEncoder();

    @Override
    public void onFinish(ITestContext itc) {
       // System.out.println("onFinish " + getHash(this));
    }

    /**
     * Here multiple logging queues should be started to separate
     * logging streams
     * @param itc
     */
    @Override
    public void onStart(ITestContext itc) {
       // System.out.println("onStart " + getHash(this));
        
        
        String htmlReportPattern = XPathService.extractXPathValue("(//Pattern[ancestor::appender[@name='JUNITREPORT']])[1]");
        
        if (htmlReportPattern==null)
            htmlReportPattern = "%date %level [%thread] %logger %msg%n";
        
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

        ple.setPattern(htmlReportPattern);
        ple.setContext(lc);
        ple.stop();
        ple.start();
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
      
        CustomAppender                                current = null;
        // Get logger for current test class
        ConcurrentHashMap<Thread, LoggerAppenderPair> lap     = getLoggerAppenderPairs(itr);
      
        LoggerAppenderPair lapr=null;
        lapr = lap.get(Thread.currentThread());
        
        // No logger for current thread, create one
        if (lapr == null)
        {
            lapr = LoggerFactory2.configureLogger();
            lap.put(Thread.currentThread(), lapr);            
        }
            
        current = lap.get(Thread.currentThread()).getAppender();       
      
        current.stop();
        current.start();
    }

    @Override
    public void onTestSuccess(ITestResult itr) {
        writeLog(itr, "SUCCESS");
    }

    public static void print(String result) {
        Reporter.log(result);
    }

    public void writeLog(ITestResult itr, String Teststatus) {
  
        print("-------------------------------------------");
        print("TEST " + Teststatus + " - LOG OUTPUT");
        print("-------------------------------------------");

        CustomAppender                                current = null;
        ConcurrentHashMap<Thread, LoggerAppenderPair> lap     = getLoggerAppenderPairs(itr);

        current = lap.get(Thread.currentThread()).getAppender();

        for (ILoggingEvent e : current.events) {
            print(ple.getLayout().doLayout(e));
        }
               
        current.clear();
        current.getExternalLoggers().clear();
    }

    public static String getHash(Object th) {
        return Thread.currentThread().getName() + th.hashCode();
    }

    
    public static ConcurrentHashMap<Thread, LoggerAppenderPair> getLoggerAppenderPairs(ITestResult itr) {
        try {
            Class c   = Class.forName(itr.getInstanceName());
            
            Field log = c.getSuperclass().getDeclaredField("LOG_BUFFER");

            log.setAccessible(true);

            ConcurrentHashMap<Thread, LoggerAppenderPair> lap = (ConcurrentHashMap<Thread,
                                                                                   LoggerAppenderPair>) log.get(null);

            return lap;
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlobalListener.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
  
}


//~ Formatted by Jindent --- http://www.jindent.com
