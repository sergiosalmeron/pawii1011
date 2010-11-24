<%-- 
    Document   : VisorUsuarios
    Created on : 24-nov-2010, 20:50:33
    Author     : sergio
--%>

<%@page import="clases.Rol"%>
<%@page import="clases.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<h1>Usuarios</h1>
        <%
            for (int i=0;i<Usuarios.getInstance().size();i++){
                String nombre=Usuarios.getInstance().getNombreUsuario(i);
                Rol rol=Usuarios.getInstance().getRolUsuario(i);
        %>
                <%=nombre%>: es <%=rol.toString()%>. Convertir en:
                <br />
                <FORM action="EditorMensajes.jsp" method="post">
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
