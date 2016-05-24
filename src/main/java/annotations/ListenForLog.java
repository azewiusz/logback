/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 *
 * @author Thomasz
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ListenForLog {
    
    public Class [] classes();
    
}
