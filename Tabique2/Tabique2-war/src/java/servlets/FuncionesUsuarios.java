/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import entidades.Usuario;
import entidades.UsuarioFacadeLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utiles.Rol;
import utilesInterfaz.MensajePantalla;

/**
 *
 * @author sergio
 */
@WebServlet(name="FuncionesUsuarios", urlPatterns={"/FuncionesUsuarios"})
public class FuncionesUsuarios extends HttpServlet {
    @EJB
    private UsuarioFacadeLocal usuarios;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("/Tabique2-war/Inicio");
        //RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/Inicio");
        //reqDispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Usuario us = (Usuario) request.getSession(false).getAttribute("usuario");
        cambiaRol(request, us);
        creaUsuario(request, us);
        eliminaUsuario(request, us);
        //RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Usuario.jsp");
        //reqDispatcher.forward(request, response);
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


    private void cambiaRol(HttpServletRequest request, Usuario us){
        if (validaAdmin(us)){
            String victima=request.getParameter("victima");
            String rol= request.getParameter("nuevoRol");

            if ((victima!=null) && (rol!=null)){
                if (usuarios.setRolUsuario(rol,victima))
                    request.getSession(false).setAttribute("MensajeStatus", new MensajePantalla("El usuario \""+ victima+ "\" es ahora "+rol, false));
                else
                    request.getSession(false).setAttribute("MensajeStatus", new MensajePantalla("No se ha podido cambiar el rol del usuario \""+ victima+ ".", true));
            }
        }
    }

    private void creaUsuario(HttpServletRequest request, Usuario us){
        if (validaAdmin(us)){
            String nuevo=request.getParameter("nuevoUsuario");
            String rol= request.getParameter("nuevoRol");

            if ((nuevo!=null) && (rol!=null)){
                if (usuarios.altaUsuario(rol, nuevo))
                    request.getSession(false).setAttribute("MensajeStatus", new MensajePantalla("El usuario \""+ nuevo+ "\" se ha creado con el rol de "+rol, false));
                else
                    request.getSession(false).setAttribute("MensajeStatus", new MensajePantalla("No se ha podido crear el usuario \""+ nuevo+ "\".", true));
            }
        }
    }

    private void eliminaUsuario(HttpServletRequest request, Usuario us){
        if (validaAdmin(us)){
            String viejo=request.getParameter("viejoUsuario");

            if ((viejo!=null)){
                if (usuarios.eliminaUsuario(viejo)){
                    request.getSession(false).setAttribute("MensajeStatus", new MensajePantalla("El usuario \""+ viejo+ "\" se ha eliminado del sistema", false));
                    if (viejo.equals(us.getNombre())){
                        request.getSession(false).removeAttribute("usuario");
                    }
                }
                else
                    request.getSession(false).setAttribute("MensajeStatus", new MensajePantalla("No se ha podido eliminar al usuario \""+ viejo+ "\".", true));
            }
        }
    }

    private boolean validaAdmin(Usuario us){
        if (us!=null){
            return us.getRol().equals(Rol.Administrador);
        }
        else
            return false;
    }


}

