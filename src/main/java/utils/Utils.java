/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Thomasz
 */
public class Utils  {
    
    public final static Logger log = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(Utils.class);
    
    public static void doOperationA()
    {
        log.info("Operation A");
    }
    
    
}
