/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import skeletons.AbstractMultiLoggerSkeleton;
import static skeletons.AbstractMultiLoggerSkeleton.getLOG;

/**
 *
 * @author Tomasz Kosiński <azewiusz@gmail.com>
 */
public class Utils2 extends AbstractMultiLoggerSkeleton {
     
    
    public static void doOperationB()
    {
        getLOG().info("Operation B");
    }
    
    
}
