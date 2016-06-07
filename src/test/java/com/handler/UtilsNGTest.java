package com.handler;

//~--- non-JDK imports --------------------------------------------------------

import utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.Utils2;

/**
 * Class description
 *
 *
 * @version        1.0, 16/05/23
 * @author         Tomasz Kosiński <azewiusz@gmail.com>
 */

public class UtilsNGTest  {
    
    public final static Logger log = (ch.qos.logback.classic.Logger)LoggerFactory.getLogger(UtilsNGTest.class);
    
    
    public UtilsNGTest() {}

    public Logger getLOG()
    {
        return log;
    }
    
    
    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {}

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {}

    /**
     * Test of superMethod method, of class Utils.
     */
    @org.testng.annotations.Test(testName = "MethodA")
    public void testMethodA() {
        getLOG().info("MethodA");
        Utils.doOperationA();
        Utils2.doOperationB();
        getLOG().info("STEP 1. - MA - Open browser window");
        getLOG().info("STEP 2. - MA - Close browser window");
    }

    @org.testng.annotations.Test(testName = "MethodB")
    public void testMethodB() {
        getLOG().info("MethodB");
        getLOG().info("STEP 1. - MB - Open browser window");
        getLOG().info("STEP 2. - MB - Close browser window");
    }

    @org.testng.annotations.Test(testName = "MethodC")
    public void testMethodC() {
        getLOG().info("MethodC");
        getLOG().info("STEP 1. - MC - Open browser window");
        getLOG().info("STEP 2. - MC - Close browser window");
    }

    @org.testng.annotations.Test(testName = "MethodD")
    public void testMethodD() {
        getLOG().info("MethodD");
        getLOG().info("STEP 1. - MD - Open browser window");
        getLOG().info("STEP 2. - MD - Close browser window");
    }

    /**
     * Test of superMethod method, of class Utils.
     */
    @org.testng.annotations.Test(testName = "superMethod")
    public void testSuperMethod() {
        getLOG().info("superMethod");
        getLOG().info("STEP 1. - Open browser window");
        getLOG().info("STEP 2. - Close browser window");
        Utils2.doOperationB();
        Utils.doOperationA();
    }

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {}

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {}
}


//~ Formatted by Jindent --- http://www.jindent.com
