/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import clases.MensajePantalla;
import clases.Rol;
import clases.Usuario;
import clases.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aleix
 */
public class FuncionesUsuarios extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println(request.getParameter("victima"));
            out.println(request.getParameter("nuevoRol"));
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FuncionesUsuarios</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FuncionesUsuarios at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
            */
        } finally { 
            out.close();
        }
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
        CambiaRol(request, us);
        RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Usuario.jsp");
        reqDispatcher.forward(request, response);
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


    private void CambiaRol(HttpServletRequest request, Usuario us){
        if (us!=null && us.getRol().equals(Rol.Administrador)){
            String victima=request.getParameter("victima");
            String rol= request.getParameter("nuevoRol");

            if ((victima!=null) && (rol!=null)){
                if (Usuarios.getInstance().setRolUsuario(victima, Usuarios.dameRol(rol)))
                    request.getSession(false).setAttribute("MensajeStatus", new MensajePantalla("El usuario \""+ victima+ "\" es ahora "+rol, false));
                else
                    request.getSession(false).setAttribute("MensajeStatus", new MensajePantalla("No se ha podido cambiar el rol del usuario \""+ victima+ ".", false));
            }
        }
        
    }
}
