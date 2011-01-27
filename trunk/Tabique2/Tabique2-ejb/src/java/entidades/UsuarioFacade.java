/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Root;
import utiles.Rol;

/**
 *
 * @author usuario_local
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal, UsuarioFacadeRemote {
    @PersistenceContext(unitName = "Tabique2-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    private Rol getRolUsuario(String nombre) {

        javax.persistence.criteria.CriteriaQuery cq =
                getEntityManager().getCriteriaBuilder().createQuery(Usuario.class);
        Root<Usuario> messageinstances = cq.from(Usuario.class);
        javax.persistence.criteria.Predicate condition = getEntityManager().getCriteriaBuilder().equal(
                messageinstances.get( "nombre"),nombre);
        cq.where(condition);

        List<Usuario> lista= getEntityManager().createQuery(cq).getResultList();
        
        if (lista.size()!=1){
            return null;
        }
        else
            return lista.get(0).getRol();

    }

    private Usuario getUsuario(String nombre) {

        javax.persistence.criteria.CriteriaQuery cq =
                getEntityManager().getCriteriaBuilder().createQuery(Usuario.class);
        Root<Usuario> messageinstances = cq.from(Usuario.class);
        javax.persistence.criteria.Predicate condition = getEntityManager().getCriteriaBuilder().equal(
                messageinstances.get( "nombre"),nombre);
        cq.where(condition);

        List<Usuario> lista= getEntityManager().createQuery(cq).getResultList();

        if (lista.size()!=1){
            return null;
        }
        else
            return lista.get(0);

    }

    @Override
    public boolean altaUsuario(Usuario usu){
        if (getRolUsuario(usu.getNombre())==null){
            this.create(usu);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean setRolUsuario(Rol rol, String nombre){

        Usuario usu=this.getUsuario(nombre);
        if (usu!=null){
            usu.setRol(rol);
            this.edit(usu);
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean login(String nombre, Rol rol){
        Rol rolBD= this.getRolUsuario(nombre);
        if (rolBD!=null){
            if (rolBD==rol)
                return true;
        }
        return false;
    }

    @Override
    public boolean eliminaUsuario(String nombre){
        Usuario usu=getUsuario(nombre);
        if (usu!=null){
            this.remove(usu);
            return true;
        }
        return false;
    }

}
