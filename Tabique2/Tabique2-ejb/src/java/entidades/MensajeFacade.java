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

/**
 *
 * @author usuario_local
 */
@Stateless
public class MensajeFacade extends AbstractFacade<Mensaje> implements MensajeFacadeLocal, MensajeFacadeRemote {
    @PersistenceContext(unitName = "Tabique2-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajeFacade() {
        super(Mensaje.class);
    }

      public List<Mensaje> findLOQUESEA() {
     /*
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
     CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
     Root<Employee> employee = criteriaQuery.from(Employee.class);
     Predicate condition = criteriaBuilder.gt(employee.get(Employee_.age), 24);
     criteriaQuery.where(condition);
     TypedQuery<Employee> typedQuery = em.createQuery(criteriaQuery);
     List<Employee> result = typedQuery.getResultList();
     */
     //Corresponding SQL: SELECT * FROM employee WHERE age > 24

        javax.persistence.criteria.CriteriaQuery cq =
                getEntityManager().getCriteriaBuilder().createQuery(Mensaje.class);
        Root<Mensaje> messageinstances = cq.from(Mensaje.class);
        javax.persistence.criteria.Predicate condition = getEntityManager().getCriteriaBuilder().equal(
                messageinstances.get( "mensaje"),"uola2");
        cq.where(condition);
        /* EJQL
        String query="SELECT * from WHERE ";
        return getEntityManager().createQuery(query).getResultList();
*/
        return getEntityManager().createQuery(cq).getResultList();
    }

      public int getLong(){
          return this.count();
      }

      public List<Mensaje> getAllMenssages(){
        return this.findAll();
      }

    @Override
    public void eliminaMensajesDe(Usuario usu){
          List<Mensaje> lista= getMensajesDe(usu);
          for (Mensaje mensa:lista)
             this.remove(mensa);
    }


    private List<Mensaje> getMensajesDe(Usuario usu) {
        javax.persistence.criteria.CriteriaQuery cq =
                getEntityManager().getCriteriaBuilder().createQuery(Mensaje.class);
        Root<Mensaje> messageinstances = cq.from(Mensaje.class);
        javax.persistence.criteria.Predicate condition = getEntityManager().getCriteriaBuilder().equal(
                messageinstances.get( "autor"),usu);
        cq.where(condition);

        List<Mensaje> lista= getEntityManager().createQuery(cq).getResultList();
        return lista;


    }

}
