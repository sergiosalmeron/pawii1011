/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


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

            out.println("<FORM action=\"Inicio\" method=\"get\">");
            out.println("<P>");
            out.println("Nombre: <INPUT type=\"text\" name=\"nombre\"><BR>");
            out.println("<INPUT type=\"radio\" name=\"tipo\" value=\"Invitado\"> Invitado<BR>");
            out.println("<INPUT type=\"radio\" name=\"tipo\" value=\"Autorizado\"> Autorizado<BR>");
            out.println("<INPUT type=\"radio\" name=\"tipo\" value=\"Administrador\"> Administrador<BR>");
            out.println("<INPUT type=\"submit\" value=\"Enviar\">");
            out.println("</P>");
            out.println("</FORM>");
            if (loginErroneo){
                out.println("Pillo");
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
        String tipo=request.getParameter("tipo");
        String nombre=request.getParameter("nombre");
        procesaLogin(tipo, nombre, request, response);
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

    private void procesaLogin(String tipo, String nombre, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ((tipo!=null)&&(nombre!=null)){
            if (Usuarios.getInstance().validaUsuario(tipo, nombre)){
                    if (tipo.equals("Invitado")){
                        RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Invitado.jsp");
                        reqDispatcher.forward(request,response);
                    }
                    if (tipo.equals("Autorizado")){
                        RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Autorizado.jsp");
                        reqDispatcher.forward(request,response);
                    }
                    if (tipo.equals("Administrador")){
                        RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Administrador.jsp");
                        reqDispatcher.forward(request,response);
                    }
            }
            else{
                loginErroneo=true;
            }
        }
    }

}