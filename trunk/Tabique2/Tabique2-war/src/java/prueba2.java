
import entidades.MensajeFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sergio
 */
public class prueba2 {
    MensajeFacadeLocal mensajeFacade = lookupMensajeFacadeLocal();

    private MensajeFacadeLocal lookupMensajeFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (MensajeFacadeLocal) c.lookup("java:global/Tabique2/Tabique2-ejb/MensajeFacade!entidades.MensajeFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


}
