/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Thomasz
 */
public class Utils {
    
    
    public final static Logger LOG = LoggerFactory.getLogger(Utils.class);
    
    public static String superMethod(String a, String b)
    {
        return a+b;
    }
    
    public static void doOperationA()
    {
        LOG.info("Operation A");
    }
    
    
}
