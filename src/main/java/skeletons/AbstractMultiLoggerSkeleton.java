/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package skeletons;

import com.handler.LoggerAppenderPair;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;

/**
 *
 * @author Tomasz Kosiński <azewiusz@gmail.com>
 */
public abstract class AbstractMultiLoggerSkeleton {
    
    public static final ConcurrentHashMap<Thread, LoggerAppenderPair> LOG_BUFFER = new ConcurrentHashMap<>();
   
    /**
     * @return the LOG
     */
    public static Logger getLOG() {
        LoggerAppenderPair current = LOG_BUFFER.get(Thread.currentThread());
        return current.getLogger();
    }
    
    
}
