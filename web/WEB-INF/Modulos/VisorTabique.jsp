<%-- 
    Document   : VisorTabique
    Created on : 24-nov-2010, 13:37:43
    Author     : usuario_local
--%>

<%@page import="clases.Usuario"%>
<%@page import="clases.Rol"%>
<%@page import="java.util.Date"%>
<%@page import="clases.Mensaje"%>
<%@page import="clases.Wall"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Tabique</h1>
        <%
            if (Wall.getInstance().size()==0)
                               Wall.getInstance().meteMensaje(new Mensaje(new Usuario(Rol.Invitado, "escritor1"), "El primer mensaje de todos!"));
            for (int i=0;i<Wall.getInstance().size();i++){
                Mensaje m=Wall.getInstance().dameMensaje(i);
        %>
<%--         Mensaje número <%=(i+1)%><br />
        -Fecha: <%=m.getFecha()%><br />
        -Autor: <%=m.getAutor().getNombre()%><br />
        -Texto: <%=m.getTexto()%><br />
--%>
<strong><%=m.getAutor().getNombre()%>:</strong> <%=m.getTexto()%> [<%=m.dameFecha()%>]<br />
        <%
            }
        %>
