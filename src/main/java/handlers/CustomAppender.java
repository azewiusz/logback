
package handlers;

//~--- non-JDK imports --------------------------------------------------------

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import org.slf4j.LoggerFactory;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;

/**
 * Class description
 *
 *
 * @version        1.0, 16/05/24
 * @author         Tomasz Kosiński <azewiusz@gmail.com>    
 */
public class CustomAppender extends AppenderBase<ILoggingEvent>
  {
    public ArrayList<ILoggingEvent> events = new ArrayList<>();
    LoggerContext                   lc     = (LoggerContext) LoggerFactory.getILoggerFactory();
    private ArrayList<Logger> externalLoggers=new ArrayList<>();
    
    ;
    
    public CustomAppender() {
        super();
        this.setContext(lc);

    }

    @Override
    protected  void append(ILoggingEvent e) {

        events.add(e);
        System.out.println("Appender event " + e.getMessage() + " Thread "+Thread.currentThread().getName() + " "+this.hashCode());
        
        // Below is optional
        for(Logger l : externalLoggers)
        {
           l.callAppenders(e);
        }
        
    }

    public void clear() {
        events.clear();
    }

    /**
     * @return the externalLoggers
     */
    public ArrayList<Logger> getExternalLoggers() {
        return externalLoggers;
    }

    /**
     * @param externalLoggers the externalLoggers to set
     */
    public void setExternalLoggers(ArrayList<Logger> externalLoggers) {
        this.externalLoggers = externalLoggers;
    }
    
   
    
  }
