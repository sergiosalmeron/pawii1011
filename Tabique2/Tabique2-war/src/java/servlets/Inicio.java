/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import entidades.Usuario;
import entidades.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utiles.Rol;
import utilesInterfaz.DetectorMovil;

/**
 *
 * @author sergio
 */
@WebServlet(name="Inicio", urlPatterns={"/Inicio"})
public class Inicio extends HttpServlet {
    @EJB
    private UsuarioFacadeLocal usuarios;
   
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
            String usrAgent=request.getHeader("User-Agent");
            boolean esMovil=(usrAgent.toLowerCase().contains("iphone"))||(usrAgent.toLowerCase().contains("android"))||(usrAgent.toLowerCase().contains("symbian"));
            out.println("<html>");
            out.println("<head>");
            if (esMovil)
                out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/cssMob/style.css\">");
            else
                out.println(muestraCSS(request));
                //out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/css1/style.css\">");
            out.println("<title>El Tabique</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div id=\"page\">");
                out.println("<div id=\"header\">");
                out.println("<h1>El tabique</h1>");

                if (!DetectorMovil.esMovil(request)){
                        out.println("<div id=\"menulinks\">");
                               out.println("<form action=\"Inicio\" method=\"post\">");
                                    out.println("<select name=\"css\" onchange=\"this.form.submit()\">");
                                        out.println("<option value=\"0\">estilos</option>");
                                        out.println("<option value=\"1\">Estilo 1</option>");
                                        out.println("<option value=\"2\">Estilo 2</option>");
                                    out.println("</select>");
                                out.println("</form>");
                        out.println("</div>");//<div id="menulinks">
                }
                
                
                out.println("</div>");//header

                
                
                out.println("<div id=\"contentarea\">");
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
                out.println("</div>");
                out.println("<div id=\"footer\">");
                    out.println("Creado por Sergio Salmerón y Aleix Garrido");
                out.println("</div>");
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();
        }
    }

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
        //processRequest(request, response);
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
        procesaEstilo(request);
        procesaSalir(request, response);
        procesaLogin(request, response);
        redirige(request, response);
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void procesaLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int valoresNull=0;
        String tipo=request.getParameter("tipo");
        if (tipo==null)
            valoresNull++;
        String nombre=request.getParameter("nombre");
        if (nombre==null)
            valoresNull++;
        if (valoresNull==0){
            if (usuarios.login(nombre,tipo)){
                    Usuario usuario=usuarios.getUsuario(nombre);
                    request.getSession(true).setAttribute("usuario", usuario);
                    Cookie c1 = new Cookie("usuario", usuario.getNombre());
                    Cookie c2 = new Cookie("tipo", usuario.getRol().toString());
                    response.addCookie(c1);
                    response.addCookie(c2);

            }
            loginErroneo=true;
        }
        else{
            if (valoresNull==1)
                loginErroneo=true;
        }
    }

    private void redirige(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion=request.getSession(false);
        boolean redir=false;
        Usuario us=null;
        if (sesion!=null)
            us = (Usuario) request.getSession(false).getAttribute("usuario");
        if (us != null) {
            redir=true;
            //RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Usuario.jsp");
            //reqDispatcher.forward(request,response);
        }
        /*Cookie c1=getCookie(request, "usuario");
        Cookie c2=getCookie(request, "tipo");
        if (c1!=null && c2!=null){
            if (Usuarios.getInstance().validaUsuario(c2.getValue(), c1.getValue())){
                Usuario usuario=Usuarios.getInstance().getusuario(c1.getValue());
                request.getSession(true).setAttribute("usuario", usuario);
                redir=true;
                //RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Usuario.jsp");
                //reqDispatcher.forward(request,response);
            }
            else
                eliminaCookies(request);
         }*/

        if (!redir)
            processRequest(request, response);
        else{
            RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Usuario.jsp");
            reqDispatcher.forward(request,response);
        }
    }

    private void procesaSalir(HttpServletRequest request, HttpServletResponse response) {
        String salir=request.getParameter("salir");
        if (salir!=null){
            request.getSession().removeAttribute("usuario");
            eliminaCookies(request);
           // request.getSession().invalidate();
        }
    }

    private Cookie getCookie( HttpServletRequest request, String cookieName ) {
      Cookie[] cookies = request.getCookies();
      for(int i=0; cookies != null && i < cookies.length; i++) {
         Cookie cookie = cookies[i];
         if (cookieName.equals(cookie.getName()))
            return cookie;
      }
      return null;
    }

    private void eliminaCookies(HttpServletRequest request){
        Cookie c1=getCookie(request, "usuario");
        if (c1!=null)
            c1.setMaxAge(0);
        Cookie c2=getCookie(request, "tipo");
        if (c2!=null)
            c2.setMaxAge(0);
    }

    private void procesaEstilo(HttpServletRequest request){
        String valorCss=request.getParameter("css");
        if (valorCss!=null){
            int css=Integer.parseInt(valorCss);
            int valor=2;
            if ((css>0)&&(css<3))
                valor=css;
            request.getSession().setAttribute("css", valor);
        }
    }

    private String muestraCSS(HttpServletRequest request){
        String resultado1="<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/css";
        String resultado2="/style.css\">";
        int css=2;
        try{
            css=Integer.parseInt( request.getSession().getAttribute("css").toString() );
        } catch(Exception e){

        }


        return resultado1+css+resultado2;
    }

}
