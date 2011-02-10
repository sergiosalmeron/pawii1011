/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.util.List;
import javax.ejb.EJB;
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
    @EJB
    private MensajeFacadeLocal mensajeFacade;
    @PersistenceContext(unitName = "Tabique2-ejbPU")
    private EntityManager em;

    @Override
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

    @Override
    public Usuario getUsuario(String nombre) {

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
    public boolean altaUsuario(String rol, String nombre){
        Rol rol2=dameRol(rol);
        if (rol2!=null)
            return altaUsuario(new Usuario(rol2, nombre));
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
    public boolean setRolUsuario(String rol, String nombre){

        Usuario usu=this.getUsuario(nombre);
        Rol rolNuevo=dameRol(rol);

        if ((usu!=null)&&(rolNuevo!=null)){
            usu.setRol(rolNuevo);
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
    public boolean login(String nombre, String rol){
        Rol rolBD= this.getRolUsuario(nombre);
        if (rolBD!=null){
            if (rolBD.toString().equalsIgnoreCase(rol))
                return true;
        }
        return false;
    }

    @Override
    public boolean eliminaUsuario(String nombre){
        Usuario usu=getUsuario(nombre);
        if (usu!=null){
            mensajeFacade.eliminaMensajesDe(usu);
            this.remove(usu);
            return true;
        }
        return false;
    }

    @Override
    public List<Usuario> getAllUsuarios(){
        return findAll();
    }

    private Rol dameRol(String rol){
        if (rol.equalsIgnoreCase("Administrador"))
            return Rol.Administrador;
        if (rol.equalsIgnoreCase("Autorizado"))
            return Rol.Autorizado;
        if (rol.equalsIgnoreCase("Invitado"))
            return Rol.Invitado;
        return null;
    }


}
