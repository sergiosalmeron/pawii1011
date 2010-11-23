/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package clases;

import java.util.ArrayList;

/**
 *
 * @author usuario_local
 */
public class Wall {

private static Wall insWall = null;
private ArrayList<Mensaje> wall;
private static String monitor="Monitor";

    private Wall(){
        wall=new ArrayList<Mensaje>();
    }

    public static Wall getInstance() {
        synchronized (monitor) {
            if (insWall == null) {
                insWall = new Wall();
            }
        }
        return insWall;
    }

}
