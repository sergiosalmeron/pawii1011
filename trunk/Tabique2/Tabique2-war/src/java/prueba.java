/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import entidades.Mensaje;
import entidades.MensajeFacadeLocal;
import entidades.Usuario;
import entidades.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utiles.Rol;

/**
 *
 * @author usuario_local
 */
@WebServlet(name="prueba", urlPatterns={"/prueba"})
public class prueba extends HttpServlet {
    @EJB
    private UsuarioFacadeLocal uf;
    @EJB
    private MensajeFacadeLocal mensajeFacade;
   
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
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet prueba</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet prueba at " + request.getContextPath () + "</h1>");
            Usuario usurero=new Usuario(Rol.Invitado, "Pepe");
            Mensaje m=new Mensaje(usurero, "probandooo");
           /*List<Mensaje> lista= mf.findLOQUESEA();
            for (Mensaje mensa : lista ){
                            out.println("uoo"+mensa.getMensaje()+mensa.getNumero());

            }*/
            uf.create(usurero);
            mensajeFacade.create(m);

            out.println("<h2>todo ok</h2>");
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

}
