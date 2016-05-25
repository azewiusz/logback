
package com.handler;

//~--- non-JDK imports --------------------------------------------------------

import handlers.GlobalListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.testng.Assert.*;
import org.testng.annotations.Listeners;

/**
 * Class description
 *
 *
 * @version        1.0, 16/05/23
 * @author         Tomasz Kosiński <azewiusz@gmail.com>    
 */
@Listeners(GlobalListener.class)
public class UtilsNGTest
  {
    
    private static final Logger LOG =  LoggerFactory.getLogger(UtilsNGTest.class);

    /**
     * @return the LOG
     */
    public static Logger getLOG() {
        return LOG;
    }
    
    public UtilsNGTest() {}

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {}

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {}

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {}

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {}

    /**
     * Test of superMethod method, of class Utils.
     */
    @org.testng.annotations.Test
    public void testSuperMethod() {
        getLOG().info("superMethod");

        getLOG().info("STEP 1. - Open browser window");
        getLOG().info("STEP 2. - Close browser window");
    }
    /**
     * Test of superMethod method, of class Utils.
     */
    @org.testng.annotations.Test
    public void testMethodA() {
        getLOG().info("MethodA");
        Utils.doOperationA();
        getLOG().info("STEP 1. - MA - Open browser window");
        getLOG().info("STEP 2. - MA - Close browser window");
    }
  }
