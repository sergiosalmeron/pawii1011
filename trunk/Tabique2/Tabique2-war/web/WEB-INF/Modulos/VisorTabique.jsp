<%-- 
    Document   : VisorTabique
    Created on : 05-feb-2011, 12:02:55
    Author     : sergio
--%>


<%@page import="entidades.Mensaje"%>
<%@page import="java.util.List"%>
<%@page import="javax.ejb.EJB"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="tabique" scope="application" class="entidades.MensajeFacadeLocal" />
        <h1>Tabique</h1>
        <%
        /*
            if (Wall.getInstance().size()==0)
                               Wall.getInstance().meteMensaje(new Mensaje(new Usuario(Rol.Invitado, "escritor1"), "El primer mensaje de todos!"));
            for (int i=0;i<Wall.getInstance().size();i++){
                Mensaje m=Wall.getInstance().dameMensaje(i);*/

          //List<Mensaje> lista=
            List<Mensaje> lista=tabique.getAllMenssages();
            for (Mensaje m: lista){
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