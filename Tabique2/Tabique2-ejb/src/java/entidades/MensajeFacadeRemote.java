/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author usuario_local
 */
@Remote
public interface MensajeFacadeRemote {

    void create(Mensaje mensaje);

    void edit(Mensaje mensaje);

    void remove(Mensaje mensaje);

    Mensaje find(Object id);

    List<Mensaje> findAll();

    List<Mensaje> findRange(int[] range);

    int count();

}
