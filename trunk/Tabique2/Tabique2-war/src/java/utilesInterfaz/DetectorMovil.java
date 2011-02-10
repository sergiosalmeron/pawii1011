/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilesInterfaz;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sergio
 */
public class DetectorMovil {

    public static boolean esMovil(HttpServletRequest request){
        String usrAgent=request.getHeader("User-Agent");
        return (usrAgent.toLowerCase().contains("iphone"))||(usrAgent.toLowerCase().contains("android"))||(usrAgent.toLowerCase().contains("symbian"));
    }
}
