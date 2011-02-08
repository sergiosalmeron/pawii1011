/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.util.List;
import javax.ejb.Local;
import utiles.Rol;

/**
 *
 * @author usuario_local
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    Usuario getUsuario(String nombre);
    boolean altaUsuario(Usuario usu);
    public boolean altaUsuario(String rol, String nombre);
    boolean setRolUsuario(Rol rol, String nombre);
    boolean setRolUsuario(String rol, String nombre);
    boolean login(String nombre, Rol rol);
    boolean login(String nombre, String rol);
    boolean eliminaUsuario(String nombre);
    public List<Usuario> getAllUsuarios();

    int count();

}
