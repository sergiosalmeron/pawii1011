/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class Inicio extends HttpServlet {

    private boolean loginErroneo=false;
   
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
            // TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>El Tabique</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<FORM action=\"Inicio\" method=\"post\">");
            out.println("<P>");
            out.println("Nombre: <INPUT type=\"text\" name=\"nombre\"><BR>");
            out.println("<INPUT type=\"radio\" name=\"tipo\" value=\"Invitado\"> Invitado<BR>");
            out.println("<INPUT type=\"radio\" name=\"tipo\" value=\"Autorizado\"> Autorizado<BR>");
            out.println("<INPUT type=\"radio\" name=\"tipo\" value=\"Administrador\"> Administrador<BR>");
            out.println("<INPUT type=\"submit\" value=\"Enviar\">");
            out.println("</P>");
            out.println("</FORM>");
            if (loginErroneo){
                out.println("Debes introducir un nombre de usuario y rol válidos.");
                loginErroneo=false;
            }

            out.println("</body>");
            out.println("</html>");
            
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
        redirige(request, response);
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
        procesaSalir(request, response);
        procesaLogin(request);
        redirige(request, response);
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

    private void procesaLogin(HttpServletRequest request) throws ServletException, IOException {
        int valoresNull=0;
        String tipo=request.getParameter("tipo");
        if (tipo==null)
            valoresNull++;
        String nombre=request.getParameter("nombre");
        if (nombre==null)
            valoresNull++;
        if (valoresNull==0){
            if (Usuarios.getInstance().validaUsuario(tipo, nombre)){
                    request.getSession(true).setAttribute("usuario", Usuarios.getInstance().getusuario(nombre));
            }
            loginErroneo=true;
        }
        else{
            if (valoresNull==1)
                loginErroneo=true;
        }
    }

    private void redirige(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario us = (Usuario) request.getSession(false).getAttribute("usuario");
        if (us != null) {
            RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Usuario.jsp");
            reqDispatcher.forward(request,response);
            /* if (us.getRol().equals(Rol.Invitado)){
            RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Invitado.jsp");
            reqDispatcher.forward(request,response);
            }
            if (us.getRol().equals(Rol.Autorizado)){
            RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Autorizado.jsp");
            reqDispatcher.forward(request,response);
            }
            if (us.getRol().equals(Rol.Administrador)){
            RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Administrador.jsp");
            reqDispatcher.forward(request,response);
            }*/
        }
    }

    private void procesaSalir(HttpServletRequest request, HttpServletResponse response) {
        String salir=request.getParameter("salir");
        if (salir!=null){
            request.getSession().removeAttribute("usuario");
           // request.getSession().invalidate();
        }
    }

}
