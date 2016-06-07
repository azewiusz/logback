package com.testngapppender;

//~--- non-JDK imports --------------------------------------------------------
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.LoggerFactory;

//~--- JDK imports ------------------------------------------------------------
import org.testng.Reporter;

/**
 * Class description
 *
 *
 * @version 1.0, 16/05/24
 * @author Tomasz Kosiński <azewiusz@gmail.com>
 */
public class TestNGAppender extends AppenderBase<ILoggingEvent> {
   
    PatternLayoutEncoder ple = null;

    public TestNGAppender() {
        super();
    }

    @Override
    protected void append(ILoggingEvent e) {

        if (ple == null) {
            ple = new PatternLayoutEncoder();
            String htmlReportPattern = XPathService.extractXPathValue("(//Pattern[ancestor::appender[@name='" + name + "']])[1]");

            if (htmlReportPattern == null) // If somehow we are missing JUNITREPORT appender pattern in logback.xml please apply some default pattern
            {
                htmlReportPattern = "%date %level [%thread] %logger %msg%n";
            }

            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();

            ple.setPattern(htmlReportPattern);
            ple.setContext(lc);
            ple.stop();
            ple.start();
        }

        Reporter.log(ple.getLayout().doLayout(e));

    }

    @Override
    public synchronized void doAppend(ILoggingEvent eventObject) {
        super.doAppend(eventObject); 
    }
}
