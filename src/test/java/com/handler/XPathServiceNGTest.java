package com.handler;

import handlers.GlobalListener;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import skeletons.AbstractMultiLoggerSkeleton;
import utils.Utils2;

@Listeners(GlobalListener.class)
public class XPathServiceNGTest extends AbstractMultiLoggerSkeleton {

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
        getLOG().info("Step 1. XPATH 1");
        getLOG().info("Step 2. XPATH 2");
        Utils.doOperationA();
       // fail("Test Failed");
    }
     @Test
    public void xpath2() {
        getLOG().info("xpath2");
        getLOG().info("Step 1. XP2 XPATH 1");
        getLOG().info("Step 2. XP2 XPATH 2");
        Utils.doOperationA();
        Utils.doOperationA();
        Utils2.doOperationB();
       // fail("Test Failed");
    }
       @Test
    public void xpath3() {
        getLOG().info("xpath2");
        Utils.doOperationA();
        Utils.doOperationA();
        getLOG().info("Step 1. XP3 XPATH 1");
        getLOG().info("Step 2. XP3 XPATH 2");
        Utils2.doOperationB();
       // fail("Test Failed");
    }

}
