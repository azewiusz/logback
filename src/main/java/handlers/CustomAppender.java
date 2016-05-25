
package handlers;

//~--- non-JDK imports --------------------------------------------------------

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

    public CustomAppender() {
        super();
        this.setContext(lc);

    }

    @Override
    protected synchronized void append(ILoggingEvent e) {

        events.add(e);
        System.out.println("Appender event " + e.getMessage() + " Thread "+Thread.currentThread().getName() + " "+this.hashCode());

    }

    public void clear() {
        events.clear();
    }
  }
