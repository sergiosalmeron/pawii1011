<%-- 
    Document   : VisorUsuarios
    Created on : 05-feb-2011, 12:20:28
    Author     : sergio
--%>

<%@page import="utilesInterfaz.DetectorMovil"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.Context"%>
<%@page import="entidades.UsuarioFacadeLocal"%>
<%@page import="java.util.List"%>
<%@page import="entidades.Usuario"%>
<%@page import="utiles.Rol"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<jsp:useBean id="usuarios" scope="session" class="entidades.UsuarioFacadeLocal" />--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<h1>Usuarios</h1>
        <%
        UsuarioFacadeLocal usuarios=null;
        try {
            Context con = new InitialContext();
            usuarios= (UsuarioFacadeLocal) con.lookup("java:global/Tabique2/Tabique2-ejb/UsuarioFacade!entidades.UsuarioFacadeLocal");
        } catch (Exception ne) {
            /*Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);*/
        }
            List<Usuario> lista=null;
            lista=usuarios.getAllUsuarios();
            for (Usuario u:lista){
                String nombre=u.getNombre();
                Rol rol=u.getRol();
                if(!DetectorMovil.esMovil(request)){%>
                    <img src="imgs/cua.jpg" heigh="15px" width="15px" class="userPapelera" id="<%=nombre%>" />
                                                <%}%>
                <%=nombre%>: es <%=rol.toString()%>. Convertir en:
                <br />
                <FORM action="FuncionesUsuarios" method="post">
                <input type="hidden" name="victima" value="<%=nombre%>" />
                <%if (!rol.equals(Rol.Administrador))
                    out.print("<input type=\"submit\" name=\"nuevoRol\" value=\"Administrador\">");
                if (!rol.equals(Rol.Autorizado))
                    out.print("<input type=\"submit\" name=\"nuevoRol\" value=\"Autorizado\">");
                if (!rol.equals(Rol.Invitado))
                    out.print("<input type=\"submit\" name=\"nuevoRol\" value=\"Invitado\">");

                %>
                <br />
                </FORM>
                

        <%
            }
        %>