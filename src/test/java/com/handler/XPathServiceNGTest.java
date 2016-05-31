/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import handlers.GlobalListener;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 * @author tomasz.kosinski
 */
@Listeners(GlobalListener.class)
public class XPathServiceNGTest {
    
    private static final ConcurrentHashMap<Thread, LoggerAppenderPair> LOG_BUFFER = new ConcurrentHashMap<>();

       /**
     * @return the LOG
     */
    public Logger getLOG() {
        LoggerAppenderPair current = LOG_BUFFER.get(Thread.currentThread());
        return current.getLogger();
    }
    
    public XPathServiceNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of extractXPathValue method, of class XPathService.
     */
    @Test
    public void testExtractXPathValue() {
        getLOG().info("extractXPathValue");
        fail("Test Failed");
    }
    
}
