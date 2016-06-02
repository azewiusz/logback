/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import skeletons.AbstractMultiLoggerSkeleton;

/**
 *
 * @author Thomasz
 */
public class Utils extends AbstractMultiLoggerSkeleton {
    
    
    public static void doOperationA()
    {
        getLOG().info("Operation A");
    }
    
    
}
